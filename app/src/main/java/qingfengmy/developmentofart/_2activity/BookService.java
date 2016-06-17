package qingfengmy.developmentofart._2activity;

import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

import qingfengmy.developmentofart._2activity.aidl.Book;
import qingfengmy.developmentofart._2activity.aidl.IBookManager;
import qingfengmy.developmentofart._2activity.aidl.IOnNewBookArrivedListener;

/**
 * Created by Administrator on 2016/6/15.
 */
public class BookService extends Service {

    // 原子性boolean值，防多线程读写操作
    private AtomicBoolean mIsServiceDestoryed = new AtomicBoolean();
    // 放多线程读写List
    private CopyOnWriteArrayList<Book> mBookList = new CopyOnWriteArrayList<>();
    // 注册的Listener的List
    private RemoteCallbackList<IOnNewBookArrivedListener> mListenerList = new RemoteCallbackList<>();

    private Binder mBinder = new IBookManager.Stub() {
        @Override
        public List<Book> getBookList() throws RemoteException {
            return mBookList;
        }

        @Override
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            int check = checkCallingOrSelfPermission("qingfengmy.developmentofart.book");
            if (check == PackageManager.PERMISSION_DENIED) {
                // 权限拒绝
                return false;
            }
            String packageName = null;
            String[] packages = getPackageManager().getPackagesForUid(getCallingUid());
            if (packages != null && packages.length > 0) {
                packageName = packages[0];
            }
            if (!packageName.startsWith("qingfengmy")) {
                return false;
            }
            return super.onTransact(code, data, reply, flags);
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            mBookList.add(book);
            // begin和finish是成对出现的，即使只查个size，也要配对出现。
            int N = mListenerList.beginBroadcast();
            for (int i = 0; i < N; i++) {
                IOnNewBookArrivedListener listener = mListenerList.getBroadcastItem(i);
                if (listener != null) {
                    listener.onNewBookArrived(book);
                }
            }
            mListenerList.finishBroadcast();
        }

        @Override
        public void registerListener(IOnNewBookArrivedListener listener) throws RemoteException {
            mListenerList.register(listener);
        }

        @Override
        public void unRegisterListener(IOnNewBookArrivedListener listener) throws RemoteException {
            mListenerList.unregister(listener);
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        mBookList.add(new Book(1, "古文观止"));
        mBookList.add(new Book(2, "天下小品"));
    }

    @Override
    public IBinder onBind(Intent intent) {
        int check = checkCallingOrSelfPermission("qingfengmy.developmentofart.book");
        if (check == PackageManager.PERMISSION_DENIED) {
            // 权限拒绝
            Log.e("aaa", "拒绝");
            return null;
        } else
            Log.e("aaa", "通过");
        return mBinder;
    }

    @Override
    public void onDestroy() {
        mIsServiceDestoryed.set(true);
        super.onDestroy();
    }
}
