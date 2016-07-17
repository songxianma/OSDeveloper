package com.liteng.dev.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.j256.ormlite.android.AndroidConnectionSource;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.mapped.MappedPreparedStmt;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.support.DatabaseConnection;
import com.j256.ormlite.table.TableUtils;
import com.liteng.dev.base.App;

import java.io.File;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by liteng on 16/7/13.
 */
public class DBHelper extends OrmLiteSqliteOpenHelper {

    private static final String DB_NAME = "developer.db";
    private static final int DB_VERSION = 1;

    private DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    private static final class DBHelperInstance {
        private static DBHelper mDBHelper = new DBHelper(App.mContext);
    }

    public static DBHelper getDBHelper() {
        return DBHelperInstance.mDBHelper;
    }


    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTableIfNotExists(connectionSource, DataCache.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, DataCache.class, true);

            onCreate(database, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void saveCache(DataCache dataCache) {
        try {
            Dao<DataCache, Integer> dataCacheDao = getDao(DataCache.class);
            dataCacheDao.create(dataCache);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public DataCache getCache(String key) {
        try {
            Dao<DataCache, Integer> dataCacheDao = getDao(DataCache.class);
            List<DataCache> dataCacheList = dataCacheDao.queryBuilder().where().eq("key",key).query();
            return dataCacheList.get(0);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return  null;
    }


}
