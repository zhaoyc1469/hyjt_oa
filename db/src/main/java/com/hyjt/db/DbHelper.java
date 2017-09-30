package com.hyjt.db;

import android.database.sqlite.SQLiteDatabase;

import com.hyjt.db.gen.DaoMaster;
import com.hyjt.db.gen.DaoSession;
import com.hyjt.frame.base.BaseApplication;

/**
 * Created by Administrator on 2017/9/30.
 */

public class DbHelper {

    private static DbHelper dbHelper;
    private static SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    private DaoMaster.DevOpenHelper mHelper;

    private DbHelper(){
        mHelper = new DaoMaster.DevOpenHelper(BaseApplication.getContext(), "user-db", null);
        db = mHelper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    public static DbHelper getInstance(){
        if(dbHelper==null){
            synchronized (DbHelper.class){
                if(dbHelper==null){
                    dbHelper = new DbHelper();
                }
            }
        }
        return  dbHelper;
    }

}
