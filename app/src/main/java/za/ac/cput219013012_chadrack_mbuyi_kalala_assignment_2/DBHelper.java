package za.ac.cput219013012_chadrack_mbuyi_kalala_assignment_2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "Login.db";

    public DBHelper(Context context) {
        super(context,"Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDatabase) {

        MyDatabase.execSQL("create Table students(username TEXT primary key, password TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDatabase, int oldVersion, int newVersion) {

        MyDatabase.execSQL("drop Table if exists students");

    }

    public Boolean insertData(String username, String password){

        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues con = new ContentValues();
        con.put("username", username);
        con.put("password", password);
        long outing = MyDatabase.insert("students", null, con);
        if (outing==-1)
            return false;
        else
            return true;
    }

    public Boolean checkusername(String username){

        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("select * from students where username = ?", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkusernamepassword(String username, String password){

        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("select * from students where username = ? and password = ?", new String[] {username,password});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }
}
