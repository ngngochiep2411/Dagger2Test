package com.example.dagger2test.data.model;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.dagger2test.di.ApplicationContext;
import com.example.dagger2test.di.DatabaseInfo;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DBHelper extends SQLiteOpenHelper {

    public static final String USER_TABLE_NAME = "hotgirls";
    public static final String USER_COLUMN_USER_ID = "id";
    public static final String USER_COLUMN_USER_NAME = "girl_name";
    public static final String USER_COLUMN_USER_AVATAR = "girl_avt";


    @Inject
    public DBHelper(@ApplicationContext Context context,
                    @DatabaseInfo String dbName,
                    @DatabaseInfo Integer version) {
        super(context, dbName, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        tableCreateStatements(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + USER_TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    private void tableCreateStatements(SQLiteDatabase sqLiteDatabase) {
        try {
            sqLiteDatabase.execSQL(
                    "CREATE TABLE IF NOT EXISTS "
                            + USER_TABLE_NAME + "("
                            + USER_COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                            + USER_COLUMN_USER_NAME + " VARCHAR(20), "
                            + USER_COLUMN_USER_AVATAR + " VARCHAR(50))"
            );

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected long insertUser(HotGirl hotGirl) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(USER_COLUMN_USER_NAME, hotGirl.getName());
            contentValues.put(USER_COLUMN_USER_AVATAR, hotGirl.getImage());
            return db.insert(USER_TABLE_NAME, null, contentValues);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    protected List<HotGirl> getAllGirl() throws Resources.NotFoundException, NullPointerException {
        Cursor cursor = null;
        List<HotGirl> listGirl = new ArrayList<>();
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            cursor = db.rawQuery("select * from " + USER_TABLE_NAME, null);

            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                do {
                    String name = cursor.getString(cursor.getColumnIndexOrThrow(USER_COLUMN_USER_NAME));
                    String image = cursor.getString(cursor.getColumnIndexOrThrow(USER_COLUMN_USER_AVATAR));
                    HotGirl hotGirl = new HotGirl(name, image);
                    listGirl.add(hotGirl);
                } while (cursor.moveToNext());
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            return listGirl;
        }


    }

    protected void clearDatabase() {
        getWritableDatabase().execSQL("delete from " + USER_TABLE_NAME);
    }
}


