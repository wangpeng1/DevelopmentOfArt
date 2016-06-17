package qingfengmy.developmentofart._2activity.Manual;

import android.os.IInterface;

/**
 * Created by Administrator on 2016/6/16.
 */
public interface IBookManager extends IInterface {
    static final java.lang.String DESCRIPTOR = "qingfengmy.developmentofart._2activity.Manual.IBookManager";

    static final int TRANSACTION_getBookList = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
    static final int TRANSACTION_addBook = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);

    public java.util.List<qingfengmy.developmentofart._2activity.aidl.Book> getBookList() throws android.os.RemoteException;

    public void addBook(qingfengmy.developmentofart._2activity.aidl.Book book) throws android.os.RemoteException;
}
