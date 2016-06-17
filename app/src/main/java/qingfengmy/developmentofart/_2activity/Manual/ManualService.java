package qingfengmy.developmentofart._2activity.Manual;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class ManualService extends Service {
    // 服务中的binder是private
    private BookManagerImpl bookManager;

    public ManualService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        bookManager = new BookManagerImpl();
        Log.e("aaa","---onCreate-----");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return bookManager;
    }

}
