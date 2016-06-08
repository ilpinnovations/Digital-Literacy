package com.tcs.innovations.digitalliteracy.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.tcs.innovations.digitalliteracy.Activities.IRCTC.IRCTCLogin;
import com.tcs.innovations.digitalliteracy.Adapters.RecyclerAdapter;
import com.tcs.innovations.digitalliteracy.Beans.CourseBean;
import com.tcs.innovations.digitalliteracy.Interfaces.Communicator;
import com.tcs.innovations.digitalliteracy.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Communicator {

    RecyclerView recyclerView;
    ArrayList<CourseBean> courseBeanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.courseList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);


        CourseBean cb;
        courseBeanList = new ArrayList<CourseBean>();
        cb = new CourseBean("1", "IRCTC");
        courseBeanList.add(cb);
        cb = new CourseBean("2", "Google");
        courseBeanList.add(cb);
        cb = new CourseBean("3", "BSNL");
        courseBeanList.add(cb);
        cb = new CourseBean("4", "KSRTC");
        courseBeanList.add(cb);

        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(MainActivity.this, courseBeanList);
        recyclerView.setAdapter(recyclerAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void callBack(CourseBean courseBean) {

        Intent i = new Intent(this, IRCTCLogin.class);
        startActivity(i);
    }
}
