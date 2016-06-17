package qingfengmy.developmentofart._2activity.Messenger;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

public class MessengerService extends Service {
    private Handler messengerHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    String text = msg.getData().getString("msg");
                    Log.e("aaa", text);
                    // 服务端回应
                    Messenger clientMessenger = msg.replyTo;
                    Message message = Message.obtain();
                    Bundle bundle = new Bundle();
                    bundle.putString("reply","收到收到已收到");
                    message.setData(bundle);
                    try {
                        clientMessenger.send(message);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    };
    private Messenger messenger = new Messenger(messengerHandler);

    public MessengerService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }
}
