package com.example.myproxy;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class GroupActivity extends AppCompatActivity {
    RecyclerView listView;
    WebsiteListAdaptor adaptor;
    final String titleKey = "TITLE_KEY";

    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);
        Toolbar toolbar = findViewById(R.id.toolbar_group);
        String title = getIntent().getStringExtra(titleKey);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        listView = findViewById(R.id.websiteList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        listView.setLayoutManager(layoutManager);
        adaptor = new WebsiteListAdaptor(new ArrayList<Website>(), GroupActivity.this);
        listView.setAdapter(adaptor);
        FloatingActionButton fab = findViewById(R.id.fab_group);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //       .setAction("Action", null).show();
                new AddWebsiteDialog(GroupActivity.this).show();


            }
        });

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

    private class AddWebsiteDialog extends Dialog {
        Button add, cancel;
        EditText websiteName, hostName;

        public AddWebsiteDialog(@NonNull Context context) {
            super(context);
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.add_website_dialog);
            add = findViewById(R.id.addButtonWebsite);
            cancel = findViewById(R.id.cancelButtonWebsite);
            websiteName = findViewById(R.id.enterWebsiteName);
            hostName = findViewById(R.id.enterHostName);
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("kimiya", "add clicked");
                    String name = String.valueOf(websiteName.getText()).trim();
                    String host = String.valueOf(hostName.getText()).trim();
                    ArrayList<Website> websites = adaptor.getWebsites();

                    if (name.equals(""))
                        Toast.makeText(getContext(), "Please enter group name!", Toast.LENGTH_SHORT).show();
                    else if (host.equals("")) {
                        Toast.makeText(getContext(), "Please enter host!", Toast.LENGTH_SHORT).show();
                    } else {
                        boolean exists = false;
                        if (websites.size() > 0) {
                            for (int i = 0; i < websites.size() ; i++) {

                                if (websites.get(i).getName().equals(name)) {
                                    Toast.makeText(getContext(), "Name taken", Toast.LENGTH_SHORT).show();
                                    exists = true;
                                    break;
                                }
                                if (websites.get(i).getHost().equals(host)) {
                                    Toast.makeText(getContext(), "Host already added", Toast.LENGTH_SHORT).show();
                                    exists = true;
                                    break;
                                }

                            }

                        }
                        if (!exists) {
                            adaptor.addWebsite(new Website(name, host));
                             websiteName.setText("");
                             hostName.setText("");

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
