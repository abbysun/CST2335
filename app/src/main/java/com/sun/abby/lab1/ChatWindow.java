package com.sun.abby.lab1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ChatWindow extends AppCompatActivity {
    private Button send;
    private EditText msg;
    private ListView listView;
    private ArrayList<String> msgData = new ArrayList<>();
    private ChatAdapter messageAdapter;
    private ChatDatabaseHelper helper;


    private static final String ACTIVITY_NAME = "Chat Window Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);

        send = (Button)findViewById(R.id.sendButton);
        msg = (EditText) findViewById(R.id.typeMsg);
        listView = (ListView) findViewById(R.id.viewMsg);
        //create database using helper
        helper = new ChatDatabaseHelper(this);
        Cursor cursor = helper.getData();

        while(cursor.moveToNext()){
            msgData.add( cursor.getString( cursor.getColumnIndex(helper.COLUMN2) ) );
            Log.i(ACTIVITY_NAME, "SQL MESSAGE: " + cursor.getString( cursor.getColumnIndex(helper.COLUMN2) ) );
        }

        messageAdapter = new ChatAdapter(this);
        listView.setAdapter(messageAdapter);

        send.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String newMsg = msg.getText().toString();
                msgData.add( newMsg );
                helper.insertData(newMsg);
                messageAdapter.notifyDataSetChanged();
                msg.setText("");
            }
        });
    }

    public void onDestroy(){
        super.onDestroy();
        helper.close();
    }

    private class ChatAdapter extends ArrayAdapter<String>{
        public ChatAdapter(Context ctx){
            super(ctx, 0);
        }

        public int getCount(){
            return msgData.size();
        }

        public String getItem(int position){
            return msgData.get(position);
        }

        public View getView(int position, View convertView, ViewGroup parent){
            LayoutInflater inflater = ChatWindow.this.getLayoutInflater();
            View result = null;
            if(position%2 == 0){
                result = inflater.inflate(R.layout.chat_row_incoming, null);
            } else{
                result = inflater.inflate(R.layout.chat_row_outgoing, null);
            }
            TextView message = (TextView)result.findViewById(R.id.messageText);
            message.setText( getItem(position) );
            return result;
        }
    }
}
