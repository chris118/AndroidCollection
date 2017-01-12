package com.example.xiaopeng.androiddemo.Activity;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.Toast;

import com.example.xiaopeng.androiddemo.Application.MainApplication;
import com.example.xiaopeng.androiddemo.Base.BaseActivity;
import com.example.xiaopeng.androiddemo.Bean.User;
import com.example.xiaopeng.androiddemo.DAO.UserDAOProxy;
import com.example.xiaopeng.androiddemo.R;
import com.example.xiaopeng.androiddemo.gen.DaoSession;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class GreenDaoActivity extends BaseActivity {

    private UserDAOProxy userDAOProxy;

    @BindView(R.id.id_name)
    EditText nameEditText;

    @BindView(R.id.id_age)
    EditText ageEditText;

    @Override
    public int getContentViewId() {
        return R.layout.activity_green_dao;
    }

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        DaoSession daoSession = ((MainApplication)getApplication()).getDaoSession();
        userDAOProxy = new UserDAOProxy(daoSession);
    }

    @OnClick(R.id.btn_add)
    public void addButtonClicked(){
        if(this.nameEditText.getText() == null || this.ageEditText.getText() == null){
            return;
        }

        User user = new User();
        user.setName(this.nameEditText.getText().toString());
        user.setAge(Integer.parseInt(this.ageEditText.getText().toString()));
        userDAOProxy.add(user);
    }

    @OnClick(R.id.btn_delete)
    public void deleteButtonClicked(){
        User user = new User();
        user.setName("Chris");
        userDAOProxy.delete(user);
    }

    @OnClick(R.id.btn_query)
    public void queryButtonClicked(){
        List<User> userList = userDAOProxy.query();
        String result = "";
        for (User usr:userList) {
            result += usr.getName() + " " + usr.getAge() + "\n";
        }
        Toast toast = Toast.makeText(getApplicationContext(),
                result, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    @OnClick(R.id.btn_update)
    public void updateButtonClicked(){
        User user = new User();
        user.setName("Chris");
        user.setAge(18);
        userDAOProxy.update(user);
    }
}
