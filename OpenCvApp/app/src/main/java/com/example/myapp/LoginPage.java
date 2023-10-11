package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapp.Util.DatabaseHelper;

public class LoginPage extends AppCompatActivity {

    EditText username;
    EditText password;
    Button login;
    TextView register;
    TextView guest;
    DatabaseHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        username=findViewById(R.id.username2);
        password=findViewById(R.id.pass2);
        login=findViewById(R.id.loginbtn);
        register=findViewById(R.id.regtxt);
        guest=findViewById(R.id.guesttxt2);
        DB = new DatabaseHelper(this);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(LoginPage.this,RegisterPage.class);
                startActivity(intent);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if(user.equals("")||pass.equals(""))
                    Toast.makeText(LoginPage.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass = DB.checkusernamepassword(user, pass);
                    if(checkuserpass==true){
                        Toast.makeText(LoginPage.this, "Sign in successfull", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginPage.this,StartRide.class);
                        Intent i=new Intent(LoginPage.this,Setting.class);
                        i.putExtra("user",user);
                        startActivity(intent);
                    }else{
                        Toast.makeText(LoginPage.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });
        guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginPage.this,MainActivity.class);
                startActivity(i);
            }
        });
    }
}