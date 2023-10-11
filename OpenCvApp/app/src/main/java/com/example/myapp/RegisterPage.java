package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapp.Util.DatabaseHelper;

public class RegisterPage extends AppCompatActivity {
    EditText name;
    EditText username;
    EditText password;
    Button register;
    TextView login;
    TextView guest;
DatabaseHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        name=findViewById(R.id.name);
        username=findViewById(R.id.username);
        password=findViewById(R.id.pass);
        register=findViewById(R.id.reg);
        login=findViewById(R.id.logintxt);
        guest=findViewById(R.id.guesttxt1);
        DB = new DatabaseHelper(this);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                //String repass = repassword.getText().toString();

                if(user.equals("")||pass.equals(""))
                    Toast.makeText(RegisterPage.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                        Boolean checkuser = DB.checkusername(user);
                        if(checkuser==false){
                            Boolean insert = DB.insertData(user, pass);
                            if(insert==true){
                                Toast.makeText(RegisterPage.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(RegisterPage.this,MainActivity.class);
                                startActivity(i);
                            }else{
                                Toast.makeText(RegisterPage.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(RegisterPage.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
                        }
                    }
            }

        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(RegisterPage.this,LoginPage.class);
                startActivity(i);
            }
        });
        guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}