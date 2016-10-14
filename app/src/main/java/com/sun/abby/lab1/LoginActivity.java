package com.sun.abby.lab1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    protected static final  String ACTIVITY_NAME = "LoginActivity";
    protected Toolbar toolbar;
    protected FloatingActionButton fab;
    private Button login;
    private EditText loginEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(ACTIVITY_NAME, "In onCreate");
        setContentView(R.layout.activity_login);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        login = (Button) findViewById(R.id.login_button);
        loginEdit = (EditText) findViewById(R.id.loginEditText);

        setSupportActionBar(toolbar);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //implements button function
        login.setOnClickListener( new View.OnClickListener(){
           @Override
            public void onClick(View view){
               saveLoginName();
               Intent startIntent = new Intent(getApplicationContext(),StartActivity.class);
               startActivity(startIntent);
            }
        });
        //load strings into EditText: login
        loginEdit.setText(getLoginName());
    }

    private String getLoginName(){
        SharedPreferences sharedPref = getSharedPreferences("login names", MODE_PRIVATE);
        return sharedPref.getString("loginName", "email@domain.com");
    }

    private void saveLoginName(){
        SharedPreferences sharedPref = getSharedPreferences("login names", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("loginName", loginEdit.getText().toString());
        editor.commit();
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.i(ACTIVITY_NAME, "In onResume");
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.i(ACTIVITY_NAME, "In onStart");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.i(ACTIVITY_NAME, "In onPause");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.i(ACTIVITY_NAME, "In onStop");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.i(ACTIVITY_NAME, "In onDestroy");
    }
}
