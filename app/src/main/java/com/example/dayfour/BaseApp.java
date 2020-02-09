package com.example.dayfour;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.example.dayfour.dao.DaoMaster;
import com.example.dayfour.dao.DaoSession;

public class BaseApp extends Application {
    private static BaseApp sInstance;
    private DaoMaster.DevOpenHelper mHelper;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        setDatabase();
    }
    /**
     * 设置greenDao
     */
    private void setDatabase() {
        mHelper = new DaoMaster.DevOpenHelper(this, "MyDb", null);    // MyDb是数据库的名字，更具自己的情况修改
        SQLiteDatabase db = mHelper.getWritableDatabase();
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }
    public static BaseApp getInstance(){
        return sInstance;
    }
    public DaoSession getDaoSession(){
        return mDaoSession;
    }

}