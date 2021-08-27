package za.ac.cput219013012_chadrack_mbuyi_kalala_assignment_2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper2 extends SQLiteOpenHelper {


    public DBHelper2(Context context) {
        super(context, "Student.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table StudentRecord(studentnumber TEXT primary key, firstname TEXT, surname TEXT, cellphone TEXT, dob TEXT, mark TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("drop Table if exists StudentRecord");

    }
    public Boolean insertdata(String studentnumber, String firstname, String surname, String cellphone, String dob, String mark){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("studentnumber", studentnumber);
        contentValues.put("firstname", firstname);
        contentValues.put("surname", surname);
        contentValues.put("cellphone", cellphone);
        contentValues.put("dob", dob);
        contentValues.put("mark", mark);
        long result = db.insert("StudentRecord", null, contentValues);

        if(result==-1){
            return false;
        }else {
            return true;
        }
    }
    public Boolean updatedata(String studentnumber, String firstname, String surname, String cellphone, String dob, String mark){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("firstname", firstname);
        contentValues.put("surname", surname);
        contentValues.put("cellphone", cellphone);
        contentValues.put("dob", dob);
        contentValues.put("mark", mark);
        Cursor cursor = db.rawQuery("Select * from StudentRecord where studentnumber = ?", new String[]{studentnumber});
        if (cursor.getCount()>0)
        {
            long result = db.update("StudentRecord", contentValues, "studentnumber=?", new String[]{studentnumber});

            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else
            {
            return false;
            }

    }
    public Boolean deletedata(String studentnumber){

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("Select * from StudentRecord where studentnumber = ?", new String[]{studentnumber});
        if (cursor.getCount()>0)
        {
            long result = db.delete("StudentRecord","studentnumber=?", new String[]{studentnumber});

            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else
        {
            return false;
        }

    }

    public Cursor getdata(){

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("Select * from StudentRecord", null);
        return cursor;

    }

}
