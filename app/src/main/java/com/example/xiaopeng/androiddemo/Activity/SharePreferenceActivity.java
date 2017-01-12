package com.example.xiaopeng.androiddemo.Activity;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.xiaopeng.androiddemo.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SharePreferenceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_preference);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.sp_save)
    public void OnSPSaveClick(){
        SharedPreferences sp = this.getSharedPreferences("config", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("content", "hello world");
        editor.commit();
    }

    @OnClick(R.id.sp_read)
    public void OnSPReadClick(){
        SharedPreferences sp = this.getSharedPreferences("config", MODE_PRIVATE);
        Toast.makeText(this, sp.getString("content", "").toString(), 0).show();
    }
}
