package com.example.myproxy;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView listView;
    GroupListAdaptor adaptor;
   // List<Group> groups;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = findViewById(R.id.group_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        listView.setLayoutManager(layoutManager);
        adaptor = new GroupListAdaptor(new ArrayList<Group>(), MainActivity.this);
        listView.setAdapter(adaptor);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                 //       .setAction("Action", null).show();
                new AddGroupDialog(MainActivity.this).show();


            }
        });
        Intent listener = new Intent(this, BrowserListener.class);
        startService(listener);


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private class AddGroupDialog extends Dialog {
        Button add, cancel;
        EditText groupName;

        public AddGroupDialog(@NonNull Context context) {
            super(context);
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.add_group_dialog);
            add =  findViewById(R.id.addButtonGroup);
            cancel =  findViewById(R.id.cancelButtonGroup);
            groupName = findViewById(R.id.enterGroupName);
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String name = String.valueOf(groupName.getText()).trim();
                    List<Group> groups = adaptor.getGroups();

                    if( name.equals(""))
                        Toast.makeText(getContext(), "Please enter group name!", Toast.LENGTH_SHORT).show();
                    else{
                        boolean exists = false;

                        for (int i = 0; i < groups.size(); i++){
                            if (groups.get(i).getName().equals(name)){
                                Toast.makeText(getContext(), "group already exists", Toast.LENGTH_SHORT).show();
                                exists = true;
                                break;
                            }
                        }
                        if(!exists) {
                            adaptor.addGroup(new Group(name));
                            groupName.setText("");
                        }

                    }
                }
            });
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dismiss();
                }
            });

        }
    }

}
