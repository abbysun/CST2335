package com.sun.abby.lab1;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.Toast;

public class StartActivity extends AppCompatActivity {
    protected static final  String ACTIVITY_NAME = "StartActivity";
    private Button button;
    private Button startChatButton;
    private Button viewWeatherButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        button = (Button)findViewById(R.id.button);
        button.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent secondIntent = new Intent(getApplicationContext(),ListItemActivity.class);
                if(secondIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(secondIntent, 5);
                }
            }
        });

        startChatButton = (Button)findViewById(R.id.startChat);
        startChatButton.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent chatIntent = new Intent(getApplicationContext(),ChatWindow.class);
                startActivity(chatIntent);
                Log.i(ACTIVITY_NAME, "User clicked Start Chat");
            }
        });

        viewWeatherButton = (Button) findViewById(R.id.weatherButton);
        viewWeatherButton.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent weatherIntent = new Intent(getApplicationContext(), WeatherForecast.class);
                Log.i(ACTIVITY_NAME, "Go to weather forcast");
                startActivity(weatherIntent);
            }
        });
    }

    public void onActivityResult(int requestCode, int responseCode, Intent data){
        if(requestCode == 5 && responseCode == Activity.RESULT_OK){
            String messagePassed = data.getStringExtra("Response");
            Toast toast = Toast.makeText(getApplicationContext(), messagePassed, Toast.LENGTH_LONG);
            toast.show();
            Log.i(ACTIVITY_NAME, "Returned to StartActivity.onActivityResult");
        }
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
