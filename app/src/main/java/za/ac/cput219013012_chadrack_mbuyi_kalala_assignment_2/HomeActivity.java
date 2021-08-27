package za.ac.cput219013012_chadrack_mbuyi_kalala_assignment_2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    EditText studentNumber, firstName, surName, cellPhone, dob, termMark;
    Button insertData, updateData, deleteData, viewData;
    DBHelper2 db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        studentNumber = findViewById(R.id.studentnumber);
        firstName = findViewById(R.id.firstname);
        surName = findViewById(R.id.surname);
        cellPhone = findViewById(R.id.cellphone);
        dob = findViewById(R.id.dob);
        termMark = findViewById(R.id.mark);

        insertData = findViewById(R.id.btnInsert);
        updateData = findViewById(R.id.btnUpdate);
        deleteData = findViewById(R.id.btnDelete);
        viewData = findViewById(R.id.btnView);
        db = new DBHelper2(this);

        insertData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String studentnumberTXT = studentNumber.getText().toString();
                String firstnameTXT = firstName.getText().toString();
                String surnameTXT = surName.getText().toString();
                String cellphoneTXT = cellPhone.getText().toString();
                String dobTXT = dob.getText().toString();
                String markTXT = termMark.getText().toString();

                Boolean checkinsertdata = db.insertdata(studentnumberTXT, firstnameTXT, surnameTXT, cellphoneTXT, dobTXT, markTXT);
                if(checkinsertdata == true)
                    Toast.makeText(HomeActivity.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(HomeActivity.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
            }
        });

        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String studentnumberTXT = studentNumber.getText().toString();
                String firstnameTXT = firstName.getText().toString();
                String surnameTXT = surName.getText().toString();
                String cellphoneTXT = cellPhone.getText().toString();
                String dobTXT = dob.getText().toString();
                String markTXT = termMark.getText().toString();

                Boolean checkupdatedata = db.updatedata(studentnumberTXT, firstnameTXT, surnameTXT, cellphoneTXT, dobTXT, markTXT);
                if(checkupdatedata == true)
                    Toast.makeText(HomeActivity.this, "New Entry Updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(HomeActivity.this, "New Entry Not Updated", Toast.LENGTH_SHORT).show();
            }
        });

        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String studentnumberTXT = studentNumber.getText().toString();

                Boolean checkdeletedata = db.deletedata(studentnumberTXT);
                if(checkdeletedata == true)
                    Toast.makeText(HomeActivity.this, "Entry Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(HomeActivity.this, "Entry Not Deleted", Toast.LENGTH_SHORT).show();
            }
        });

        viewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = db.getdata();
                if (res.getCount()==0){

                    Toast.makeText(HomeActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){

                    buffer.append("Student ID :     "+ res.getString(0) + "\n");
                    buffer.append("First Name :     "+ res.getString(1) + "\n");
                    buffer.append("Surname :        "+ res.getString(2) + "\n");
                    buffer.append("Telephone Num :  "+ res.getString(3) + "\n");
                    buffer.append("Date of Birth :  "+ res.getString(4) + "\n");
                    buffer.append("Term 2 mark :    "+ res.getString(5) + "%" + "\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
                builder.setCancelable(true);
                builder.setTitle("STUDENT RECORDS");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });




    }
}