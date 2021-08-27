package za.ac.cput219013012_chadrack_mbuyi_kalala_assignment_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    SearchView mySearchView;
    ListView myList;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    EditText username, password, repassword;
    Button signup, signin;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mySearchView = (SearchView) findViewById(R.id.searchView);
        myList = (ListView) findViewById(R.id.myList);

        list = new ArrayList<String>();
        list.add("Chadrack");
        list.add("Cedric");
        list.add("Marleine");
        list.add("Daniel");
        list.add("Mechack");
        list.add("Isaac");
        list.add("Kalala");
        list.add("Nyembo");
        list.add("Ndongeni");
        list.add("Siyabonga");
        list.add("Fabrice");
        list.add("Tshibola");
        list.add("Marcel");

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        myList.setAdapter(adapter);

        mySearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                adapter.getFilter().filter(s);
                return false;
            }
        });
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repass);
        signup = (Button) findViewById(R.id.btnregister);
        signin = (Button) findViewById(R.id.btnlogin);
        DB = new DBHelper(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                if (user.equals("")||pass.equals("")||repass.equals(""))
                    Toast.makeText(MainActivity.this,"Please complete all fields", Toast.LENGTH_SHORT).show();
                else{
                    if (repass.equals(repass)){
                        Boolean checkuser = DB.checkusername(user);
                        if (checkuser==false){
                            Boolean insert = DB.insertData(user, pass);
                            if (insert==true){
                                Toast.makeText(MainActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(MainActivity.this, "The registration has failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(MainActivity.this, "The user already exists! Please Login", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(MainActivity.this, "No matching of password",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);

            }
        });
    }
}