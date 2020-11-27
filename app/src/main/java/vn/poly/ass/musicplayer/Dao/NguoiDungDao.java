package vn.poly.ass.musicplayer.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import vn.poly.ass.musicplayer.DB.DatabaseHelper;
import vn.poly.ass.musicplayer.Model.Nguoidung;


public class NguoiDungDao {
    public DatabaseHelper dbHelper;
    public SQLiteDatabase db;

    public static final String SQL_NGUOI_DUNG ="CREATE TABLE Nguoidung (userName text primary key, " +
            "password text);";

    public static final String TABLE_NAME = "Nguoidung";
    public NguoiDungDao(Context context){
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public int inserNguoiDung(Nguoidung nd){
        ContentValues values = new ContentValues();
        values.put("userName",nd.getUserName());
        values.put("password",nd.getPassword());
        try {
            if(db.insert(TABLE_NAME,null,values)== -1){
                return -1;
            }
        }catch (Exception ex){
            Log.e("NguoiDungDao",ex.toString());
        }
        return 1;
    }

    //check login

    public Nguoidung getUser(String username) {
        Nguoidung user = null;

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        // Truyen vao Ten bang, array bao gom ten cot, ten cot khoa chinh, gia tri khoa chinh, cac tham so con lai la null
        Cursor cursor = db.query(TABLE_NAME, new String[]{"userName", "password"},   "userName=?", new String[]{username}, null, null, null, null);
        // moveToFirst : kiem tra xem cursor co chua du lieu khong, ham nay tra ve gia tri la true or false
        if (cursor != null && cursor.moveToFirst()) {

            String user_name = cursor.getString(cursor.getColumnIndex("userName"));
            String password = cursor.getString(cursor.getColumnIndex("password"));
            // khoi tao user voi cac gia tri lay duoc
            user = new Nguoidung(user_name, password);
        }
        cursor.close();
        return user;
    }
}
