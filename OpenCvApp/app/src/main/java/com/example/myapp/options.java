package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class options extends AppCompatActivity {
SwitchCompat Holes,Pedistrains,Vehicle,Share;
Button savebtn;
ImageButton back;
    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        Holes=findViewById(R.id.hole);
        Pedistrains=findViewById(R.id.ped);
        Vehicle=findViewById(R.id.veh);
        Share=findViewById(R.id.share);
        savebtn=findViewById(R.id.save);
        back=findViewById(R.id.baack);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(options.this,Setting.class);
                startActivity(intent);
            }
        });


        SharedPreferences sharedPreferences=getSharedPreferences("save1",MODE_PRIVATE);
        final SharedPreferences.Editor editor= sharedPreferences.edit();
        Holes.setChecked(sharedPreferences.getBoolean("switch",true));


        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Holes.isChecked()){
                    editor.putBoolean("switch",true);
                    editor.apply();
                }
                else{
                    editor.putBoolean("switch",false);
                    editor.apply();
                }
                editor.commit();
                Toast.makeText(options.this, "Settings Saved", Toast.LENGTH_SHORT).show();
            }
        });
        /*Holes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Holes.isChecked()){
                    SharedPreferences.Editor editor=getSharedPreferences("save1",MODE_PRIVATE).edit();
                    editor.putBoolean("value",true);
                    editor.apply();
                    Holes.setChecked(true);
                    Intent i = new Intent(options.this,Setting.class);
                    i.putExtra("Allow",true);
                }
                else{
                    SharedPreferences.Editor editor=getSharedPreferences("save1",MODE_PRIVATE).edit();
                    editor.putBoolean("value",false);
                    editor.apply();
                    Holes.setChecked(false);

                }
            }
        });*/
        //SharedPreferences sharedPreferences2=getSharedPreferences("save2",MODE_PRIVATE);
        /*Pedistrains.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Pedistrains.isChecked()){
                    SharedPreferences.Editor editor=getSharedPreferences("save2",MODE_PRIVATE).edit();
                    editor.putBoolean("value",true);
                    editor.apply();
                    Pedistrains.setChecked(true);
                    Intent i = new Intent(options.this,Setting.class);
                    i.putExtra("Allow2",true);
                }
                else{
                    SharedPreferences.Editor editor2=getSharedPreferences("save2",MODE_PRIVATE).edit();
                    editor2.putBoolean("value",false);
                    editor2.apply();
                    Pedistrains.setChecked(false);

                }
            }
        });
        SharedPreferences sharedPreferences3=getSharedPreferences("save",MODE_PRIVATE);
        Vehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Vehicle.isChecked()){
                    SharedPreferences.Editor editor=getSharedPreferences("save",MODE_PRIVATE).edit();
                    editor.putBoolean("value",true);
                    editor.apply();
                    Vehicle.setChecked(true);
                    Intent i = new Intent(options.this,Setting.class);
                    i.putExtra("Allow3",true);
                }
                else{
                    SharedPreferences.Editor editor2=getSharedPreferences("save",MODE_PRIVATE).edit();
                    editor2.putBoolean("value",false);
                    editor2.apply();
                    Vehicle.setChecked(false);

                }
            }
        });
        SharedPreferences sharedPreferences4=getSharedPreferences("save",MODE_PRIVATE);
        Share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Share.isChecked()){
                    SharedPreferences.Editor editor=getSharedPreferences("save",MODE_PRIVATE).edit();
                    editor.putBoolean("value",true);
                    editor.apply();
                    Share.setChecked(true);
                    Intent i = new Intent(options.this,Setting.class);
                    i.putExtra("Allow4",true);
                }
                else{
                    SharedPreferences.Editor editor2=getSharedPreferences("save",MODE_PRIVATE).edit();
                    editor2.putBoolean("value",false);
                    editor2.apply();
                    Share.setChecked(false);

                }
            }
        });*/
    }
}