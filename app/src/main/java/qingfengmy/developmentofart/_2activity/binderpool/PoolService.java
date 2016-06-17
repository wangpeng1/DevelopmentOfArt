package qingfengmy.developmentofart._2activity.binderpool;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import qingfengmy.developmentofart._2activity.binderpool.impl.BinderPool;

public class PoolService extends Service {
    private Binder mBinderPool = new BinderPool.BinderPoolImpl();

    public PoolService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinderPool;
    }

}
