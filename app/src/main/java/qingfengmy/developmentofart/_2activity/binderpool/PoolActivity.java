package qingfengmy.developmentofart._2activity.binderpool;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import qingfengmy.developmentofart.R;
import qingfengmy.developmentofart._2activity.binderpool.aidl.ICompute;
import qingfengmy.developmentofart._2activity.binderpool.aidl.ISecurityCenter;
import qingfengmy.developmentofart._2activity.binderpool.impl.BinderPool;
import qingfengmy.developmentofart._2activity.binderpool.impl.ComputerImpl;
import qingfengmy.developmentofart._2activity.binderpool.impl.SecurityCenterImpl;

public class PoolActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pool);

        new Thread() {
            @Override
            public void run() {
                super.run();
                doWork();
            }
        }.start();
    }

    private void doWork() {
        BinderPool binderPool = BinderPool.getsInstance(this);
        IBinder computeBinder = binderPool.queryBinder(BinderPool.BINDER_COMPUTE);
        ICompute compute = ComputerImpl.asInterface(computeBinder);
        try {
            int result = compute.add(1, 2);
            Log.e("aaa", "1+2=" + result);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        IBinder securityCenterBinder = binderPool.queryBinder(BinderPool.BINDER_SECURITY_CENTER);
        ISecurityCenter iSecurityCenter = SecurityCenterImpl.asInterface(securityCenterBinder);
        try {
            String content = "i love this book";
            Log.e("aaa", "加密前：" + content);
            content = iSecurityCenter.encrypt(content);
            Log.e("aaa", "加密后：" + content);
            content = iSecurityCenter.decrypt(content);
            Log.e("aaa", "解密后：" + content);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }
}
