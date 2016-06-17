package qingfengmy.developmentofart._2activity.ContentProvider;

import android.content.ContentValues;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import qingfengmy.developmentofart.R;
import qingfengmy.developmentofart._2activity.aidl.Book;

public class ContentProviderActivity extends AppCompatActivity {
    int id = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider);

        List<Book> bookList = new ArrayList<>();
        final Uri uri = Uri.parse("content://qingfengmy.developmentofart.bookprovider/book");
        Cursor bookCursor = (Cursor) getContentResolver().query(uri, null, null, null, null);
        while (bookCursor.moveToNext()) {
            int bookId = bookCursor.getInt(0);
            String bookName = bookCursor.getString(1);
            Book book = new Book(bookId, bookName);
            bookList.add(book);
        }
        Log.e("aaa", bookList.toString());

        getContentResolver().registerContentObserver(uri, true, new ContentObserver(new Handler()) {
            @Override
            public void onChange(boolean selfChange, Uri uri) {
                super.onChange(selfChange, uri);
                Log.e("aaa", "onChange:" + uri.toString());
            }
        });

        findViewById(R.id.click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                values.put("_id", id);
                values.put("name", "程序设计的艺术");
                getContentResolver().insert(uri, values);
                id=id+1;
            }
        });
    }
}
