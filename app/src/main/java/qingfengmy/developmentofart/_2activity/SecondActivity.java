package qingfengmy.developmentofart._2activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import qingfengmy.developmentofart.R;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("aaa","sUserId="+UserManager.sUserId);
        setContentView(R.layout.activity_second);
        startActivity(new Intent(this, ThirdActivity.class));

        try {
            File cache = new File(getCacheDir(), "cache.txt");
            if (!cache.exists()) {
                cache.createNewFile();
            }
            // 反序列化
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(cache.getAbsoluteFile()));
            User newUser = (User) in.readObject();
            in.close();
            Log.e("aaa","---------"+newUser.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
