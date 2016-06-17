package qingfengmy.developmentofart;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.util.Log;

/**
 * Created by Administrator on 2016/6/15.
 */
public class APP extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        String processName = getCurProcessName(this);
        Log.e("aaa",processName);
    }
    String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager mActivityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager
                .getRunningAppProcesses()) {
            if (appProcess.pid == pid) {

                return appProcess.processName;
            }
        }
        return null;
    }
}
