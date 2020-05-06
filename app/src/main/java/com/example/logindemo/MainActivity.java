package com.example.logindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText usernameEditText;
    EditText passwordEditText;
    Button LoginButton;
    DatabaseHelper db;



    public void jumpToRegisterPage(View view){
        Toast.makeText(this, "register for new account", Toast.LENGTH_SHORT).show();
        Intent registerIntent=new Intent(MainActivity.this,Main2Activity.class);
        startActivity(registerIntent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db=new DatabaseHelper(this);
        usernameEditText=(EditText)findViewById(R.id.editText2);
        passwordEditText=(EditText)findViewById(R.id.confirmPassword);
        LoginButton=(Button)findViewById(R.id.buttonLogin);

        LoginButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String user=usernameEditText.getText().toString().trim();
                String pwd=passwordEditText.getText().toString().trim();
                if(pwd.equals("")||user.equals("")){
                    Toast.makeText(MainActivity.this, "Fill up the Details", Toast.LENGTH_SHORT).show();
                }else{
                    Boolean res=db.checkUserAndPwd(user,pwd);
                    if(res){

                            Toast.makeText(MainActivity.this, "successfully logged in!", Toast.LENGTH_SHORT).show();
                            Intent successIntent=new Intent(MainActivity.this,Main3Activity.class);
                            startActivity(successIntent);



                    }else{
                        Toast.makeText(MainActivity.this, "login error", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}
