package com.umeng.soexample.greendao;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.umeng.soexample.greendao.mydao.DaoMaster;
import com.umeng.soexample.greendao.mydao.DaoSession;

/**
 * Created by android_lhf：2018/12/26
 */
public class MyApp extends Application{
    private static DaoSession mDaoSession;
    @Override
    public void onCreate() {
        super.onCreate();
        initGreenDao();
    }

    private void initGreenDao() {
        //第一步创建OpenHelper类
        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(this, "lhf.db");
        //开启一个可写数据库类
        SQLiteDatabase writableDatabase = openHelper.getWritableDatabase();
        //通过DaoMaster封装
        DaoMaster master = new DaoMaster(writableDatabase);
        mDaoSession = master.newSession();
    }
    public static DaoSession getDaoSession() {
        return mDaoSession;
    }
}
