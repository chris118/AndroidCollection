package com.example.xiaopeng.androiddemo.DAO;

import android.util.Log;
import android.widget.Toast;

import com.example.xiaopeng.androiddemo.Bean.User;
import com.example.xiaopeng.androiddemo.gen.DaoSession;
import com.example.xiaopeng.androiddemo.gen.UserDao;

import java.util.List;

/**
 * Created by xiaopeng on 2016/10/28.
 */

public class UserDAOProxy {
    private final String TAG = UserDAOProxy.class.getSimpleName();

    private UserDao userDao;

    public UserDAOProxy(DaoSession daoSession) {

        this.userDao = daoSession.getUserDao();
    }

    public void add(User user) {
        this.userDao.insert(user);
    }

    public void delete(User user) {
        try{
            User findUser = userDao.queryBuilder().where(UserDao.Properties.Name.eq(user.getName())).build().unique();
            if(findUser != null){
                this.userDao.delete(findUser);
            }
        }catch (Exception e) {
            Log.d(TAG, e.getLocalizedMessage());
        }
    }

    public void deleteAll() {
        this.userDao.deleteAll();
    }

    public List<User> query() {
        List<User> userList = userDao.queryBuilder().list();

        return userList;
    }

    public void update(User usr){
        try{
            User findUser = userDao.queryBuilder().where(UserDao.Properties.Name.eq(usr.getName())).build().unique();
            if(findUser != null){
                findUser.setAge(usr.getAge());
                userDao.update(findUser);
            }
        }catch (Exception e){
            Log.d(TAG, e.getLocalizedMessage());
        }
    }
}
