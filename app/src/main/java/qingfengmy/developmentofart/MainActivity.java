package qingfengmy.developmentofart;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import qingfengmy.developmentofart._2activity.binderpool.PoolActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Intent intent = new Intent(this, LaunchMode1Activity.class);
////        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        getApplication().startActivity(intent);

//        Intent intent = new Intent();
//        intent.setAction("qingfengmy.ali");
//        intent.setDataAndType(Uri.parse("file://qingfengmy"), "images/*");
//        startActivity(intent);
//
//        getPackageManager().resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
//        getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);

//        UserManager.sUserId = 2;
//        startActivity(new Intent(this, SecondActivity.class));

//
//        try {
//            User user = new User(110, "allen");
//            File cache = new File(getCacheDir(), "cache.txt");
//            if (!cache.exists()) {
//                cache.createNewFile();
//            }
//            // 序列化
//            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(cache.getAbsoluteFile()));
//            out.writeObject(user);
//            out.close();
//            Log.e("aaa","---------"+user.toString());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PoolActivity.class));
            }
        });
    }
}
