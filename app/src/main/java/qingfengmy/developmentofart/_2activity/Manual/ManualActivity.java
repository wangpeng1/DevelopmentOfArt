package qingfengmy.developmentofart._2activity.Manual;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import qingfengmy.developmentofart.R;

public class ManualActivity extends AppCompatActivity {

    ServiceConnection serviceConnection;
    // 接口是暴露出来的
    IBookManager bookManager;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual);

        // 定义死亡代理
        final IBinder.DeathRecipient mDeathRecipient = new IBinder.DeathRecipient() {
            @Override
            public void binderDied() {
                // binder死亡时，会回调这个方法
                if (bookManager == null){
                    return;
                }
                bookManager.asBinder().unlinkToDeath(this,0);
                bookManager = null;
                // 重连
                bindService(intent,serviceConnection,BIND_AUTO_CREATE);
            }
        };
        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                bookManager = BookManagerImpl.asInterface(service);
                try {
                    service.linkToDeath(mDeathRecipient,0);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };

        intent = new Intent(this, ManualService.class);
        bindService(intent, serviceConnection, BIND_AUTO_CREATE);

        findViewById(R.id.click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    bookManager.getBookList();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            unbindService(serviceConnection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
