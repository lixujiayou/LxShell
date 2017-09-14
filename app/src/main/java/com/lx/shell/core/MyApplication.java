package com.lx.shell.core;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;

import com.lx.shell.mvp.model.bean.greendao.DaoMaster;
import com.lx.shell.mvp.model.bean.greendao.DaoSession;

import es.dmoral.toasty.Toasty;


/**
 * Created by ${lupinghua} on 2017/4/25.
 */

public class MyApplication extends Application{
    private static MyApplication INSTANCE;
    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    public static MyApplication getApplication() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initToast();
        initGreenDao();
    }


    private void initToast(){
        Toasty.Config.getInstance()
                .setErrorColor(Color.parseColor("#CF0B17"))
                .setSuccessColor(Color.parseColor("#4F9750"))
                .setWarningColor(Color.parseColor("#FCA008"))
                .setTextSize(13)
             /*   .setInfoColor(@ColorInt int infoColor)

                .setWarningColor(@ColorInt int warningColor)
                .setTextColor(@ColorInt int textColor)
                .tintIcon(boolean tintIcon)
                .setToastTypeface(@NonNull Typeface typeface)
                .setTextSize(int sizeInSp)*/
                 .apply(); // required
    }



    private void initGreenDao(){
        INSTANCE = this;
        mHelper = new DaoMaster.DevOpenHelper(this, "notes-db", null);
        db = mHelper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }
    public SQLiteDatabase getDb() {
        return db;
    }
}
