package com.example.projectgame;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectgame.adapter.UserAdapter;
import com.example.projectgame.db.AppDatabase;
import com.example.projectgame.model.User;
import com.example.projectgame.util.AppExecutors;

//คลาสแสดงหน้าจอหลัก
public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();
    private RecyclerView mRecycleView;

    //เมธอดกำหนดการทำงานเมื่อใช้งานหน้าจอหลัก
    @Override
    protected void onResume() {
        super.onResume();

        //กำหนดการแสดงผลบนหน้าจอหลักให้มีการปรับปรุงให้ตรงกับฐานข้อมูลในปัจจุบันทุกครั้งเมื่อมีการเข้าถึง
        final AppExecutors executors = new AppExecutors();
        executors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                AppDatabase db = AppDatabase.getInstance(MainActivity.this);
                final User[] users = db.userDao().getAllUsers();
                //แบ่งเธรดในการเชื่อมต่อข้อมูล
                executors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        UserAdapter adapter = new UserAdapter(MainActivity.this, users);
                        mRecycleView.setAdapter(adapter);
                    }
                });
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecycleView = findViewById(R.id.user_recycler_view);
        mRecycleView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        //เมื่อกดปุ่ม add info
        Button addButton = findViewById(R.id.add_info_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //เมื่อกดปุ่ม add info จะแสดงหน้้าถัดไป
                Intent i = new Intent(MainActivity.this, AddUserActivity.class);
                startActivity(i);
            }
        });

        //เมื่อกดปุ่ม delete
        Button clearButton = findViewById(R.id.delete_button);
        clearButton.setOnClickListener(new View.OnClickListener() {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

            //เมื่อกดปุ่ม delete แล้วจะมีข้อความขึ้นมาถามว่าแน่จะที่จะลบหรือไม่
            public void onClick(View view) {
                builder.setMessage("Do you want to delete?");builder.setNegativeButton("NO", null);
                builder.setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        AppExecutors executors = new AppExecutors();
                        executors.diskIO().execute(new Runnable() {
                            @Override
                            public void run() {
                                AppDatabase db = AppDatabase.getInstance(MainActivity.this);
                                db.userDao().deleteAllUser();
                            }
                        });

                        Intent intent = new Intent(MainActivity.this,MainActivity.class);
                        startActivity(intent);
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
            }
        });
    }
}

