package vn.poly.ass.musicplayer.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import vn.poly.ass.musicplayer.Dao.NguoiDungDao;


public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "duan1";
    public static final int VERSION = 1;


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(NguoiDungDao.SQL_NGUOI_DUNG);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL(NguoiDungDao.TABLE_NAME);
    onCreate(db);
    }
}
