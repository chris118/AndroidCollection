package com.example.xiaopeng.androiddemo.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.xiaopeng.androiddemo.JNIClass.NdkJniUtils;
import com.example.xiaopeng.androiddemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class JNIActivity extends AppCompatActivity {

    @BindView(R.id.output)
    public TextView output_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jni);
        ButterKnife.bind(this);

        NdkJniUtils jniUtils = new NdkJniUtils();
        String text = jniUtils.getCLanguageString();
        output_tv.setText(text);
    }
}
