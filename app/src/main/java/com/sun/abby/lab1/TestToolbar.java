package com.sun.abby.lab1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class TestToolbar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_toolbar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu m){
        getMenuInflater().inflate(R.menu.toolbar_menu, m);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menu_camera:
                Log.d("Toolbar", "Option camera selected");
                break;
            case R.id.menu_chat:
                Log.d("Toolbar", "Option chat selected");
                break;
            case R.id.menu_music:
                Log.d("Toolbar", "Option music selected");
                break;
            case R.id.menu_about:
                Log.d("Toolbar", "Option about selected");
                Toast.makeText(getApplicationContext(), "Version 1.0 by Abby Sun", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}
