package com.example.logindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    EditText usernameEditText;
    EditText passwordEditText;
    EditText cnfPasswordEditText;
    Button RegisterButton;
    DatabaseHelper db;

    public void jumpToLoginPage(View view){
        Intent loginIntent=new Intent(Main2Activity.this,MainActivity.class);
        startActivity(loginIntent);
    }

    public void onRegister(View view){
        Toast.makeText(Main2Activity.this, "successfully registered", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        db = new DatabaseHelper(this);
        usernameEditText=(EditText)findViewById(R.id.editText2);
        passwordEditText=(EditText)findViewById(R.id.password);
        cnfPasswordEditText=(EditText)findViewById(R.id.confirmPassword);
        RegisterButton=(Button)findViewById(R.id.buttonLogin);

        RegisterButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String user=usernameEditText.getText().toString().trim();
                String pwd=passwordEditText.getText().toString().trim();
                String cPwd=cnfPasswordEditText.getText().toString().trim();
                if(user.equals("")|pwd.equals("")||cPwd.equals("")){
                    Toast.makeText(Main2Activity.this, "fill up details", Toast.LENGTH_SHORT).show();
                }else{
                    if(pwd.equals(cPwd)){
                        boolean val=db.insert(user,pwd);
                            if (val){
                                 Toast.makeText(Main2Activity.this, "You have Registered :)", Toast.LENGTH_SHORT).show();
                                 Intent moveToLogin=new Intent(Main2Activity.this,MainActivity.class);
                                 startActivity(moveToLogin);

                            }else{
                                Toast.makeText(Main2Activity.this, "registeration error", Toast.LENGTH_SHORT).show();
                             }

                    }else{
                        Toast.makeText(Main2Activity.this, "Password is not matching!", Toast.LENGTH_SHORT).show();
                     }
                }
            }

        });


    }
}
