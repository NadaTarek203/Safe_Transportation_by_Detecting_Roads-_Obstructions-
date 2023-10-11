package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.myapp.Util.DatabaseHelper;

public class Setting extends AppCompatActivity {
    TextView Usertxt,AllowH,AllowV,AllowP,AllowS;
    ImageButton back;
    DatabaseHelper DB;
    Button Edit;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Usertxt=findViewById(R.id.user);
        AllowH=findViewById(R.id.allowholes);
        AllowP=findViewById(R.id.allowpedistrain);
        AllowS=findViewById(R.id.allowshare);
        AllowV=findViewById(R.id.allowveh);
        DB = new DatabaseHelper(this);
        String user=getIntent().getStringExtra("user");
        String Aholes=getIntent().getStringExtra("switch");
        //String Aped=getIntent().getStringExtra("save2");
        AllowH.setText(Aholes);
        Usertxt.setText(user);
        back=findViewById(R.id.baack);
        Edit=findViewById(R.id.edit);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Setting.this,StartRide.class);
                startActivity(i);
            }
        });
        Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Setting.this,options.class);
                startActivity(i);
            }
        });
    }
}