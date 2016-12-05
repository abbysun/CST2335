package com.sun.abby.lab1;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.view.LayoutInflater;


public class TestToolbar extends AppCompatActivity {
    private String snackbarMSG = "Option camera selected";

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
                Snackbar.make(findViewById(R.id.menu_camera), snackbarMSG, Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.menu_back:
                Log.d("Toolbar", "Option back selected");
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.dialog_back_title);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){
                        finish();
                    }
                });
                builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){
                        //do nothing
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                break;
            case R.id.menu_music:
                Log.d("Toolbar", "Option music selected");
                newLayoutDialog().show();
                break;
            case R.id.menu_about:
                Log.d("Toolbar", "Option about selected");
                Toast.makeText(getApplicationContext(), "Version 1.0 by Abby Sun", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }


    public Dialog newLayoutDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();

        final View dialogView = inflater.inflate(R.layout.custom_dialog, null);
        builder.setView(dialogView);

        final EditText newMsg = (EditText) dialogView.findViewById(R.id.custom_dialog_text);

        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        snackbarMSG = newMsg.getText().toString();
                    }
                });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //do nothing
                    }
                });
        return builder.create();
    }
}
