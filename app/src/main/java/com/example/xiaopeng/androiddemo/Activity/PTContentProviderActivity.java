package com.example.xiaopeng.androiddemo.Activity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.provider.Contacts;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.xiaopeng.androiddemo.Bean.MyUsers;
import com.example.xiaopeng.androiddemo.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.provider.Contacts.*;

public class PTContentProviderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ptcontent_provider);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.queryContact)
    public void OnQueryClick(){
        DisplayContacts();
    }

    @OnClick(R.id.addContact)
    public void OnAddContactClick(){
        AddContact("测试添加", "18000000000");
    }

    @OnClick(R.id.add_record)
    public void OnAddRecordClick(){
        ContentValues values = new ContentValues();
        values.put(MyUsers.User.USER_NAME, "测试纪录");
        getContentResolver().insert(MyUsers.User.CONTENT_URI, values);
    }

    @SuppressWarnings("deprecation")
    @OnClick(R.id.query_record)
    public void OnAQueryRecordClick(){
        String columns[] = new String[] { MyUsers.User._ID, MyUsers.User.USER_NAME };
        Uri myUri = MyUsers.User.CONTENT_URI;
        Cursor cur = managedQuery(myUri, columns,null, null, null );
        if (cur.moveToFirst()) {
            String id = null;
            String userName = null;
            do {
                id = cur.getString(cur.getColumnIndex(MyUsers.User._ID));
                userName = cur.getString(cur.getColumnIndex(MyUsers.User.USER_NAME));
                Toast.makeText(this, id + " " + userName, Toast.LENGTH_SHORT).show();
            } while (cur.moveToNext());
        }
    }

    @SuppressWarnings("deprecation")
    private void DisplayContacts(){
        //该数组中包含了所有要返回的字段
        String columns[] = new String[] { People.NAME, People.NUMBER };
        Uri mContacts = People.CONTENT_URI;
        Cursor cur = managedQuery(
                    mContacts,
                    columns, null,null,null);

        if (cur.moveToFirst()) {
            String name = null;
            String phoneNo = null;
            do {
                // 获取字段的值
                name = cur.getString(cur.getColumnIndex(People.NAME));
                phoneNo = cur.getString(cur.getColumnIndex(People.NUMBER));
                Toast.makeText(this, name + " " + phoneNo, Toast.LENGTH_LONG).show();
            } while (cur.moveToNext());
        }

    }

    @SuppressWarnings("deprecation")
    private void AddContact(String name, String phoneNo){
        ContentValues values = new ContentValues();
        values.put(People.NAME, name);
        Uri uri = getContentResolver().insert(People.CONTENT_URI, values);


        Uri numberUri = Uri.withAppendedPath(uri, People.Phones.CONTENT_DIRECTORY);
        values.clear();
        values.put(Contacts.Phones.TYPE, People.Phones.TYPE_MOBILE);
        values.put(People.NUMBER, phoneNo);
        getContentResolver().insert(numberUri, values);
    }
}
