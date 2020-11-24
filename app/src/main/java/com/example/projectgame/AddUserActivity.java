package com.example.projectgame;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectgame.db.AppDatabase;
import com.example.projectgame.model.User;
import com.example.projectgame.util.AppExecutors;

//คลาสเก็บข้อมูล
public class AddUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        //กำหนดเหตุการณ์เมื่อกดปุ่ม save info
        Button scoreButton = findViewById(R.id.save_button);
        final EditText nameEditText = findViewById(R.id.add_name);
        final EditText bbeEditText = findViewById(R.id.bbe_edit_text);
        final EditText mfgEditText = findViewById(R.id.mfg_edit_text);
        final EditText expEditText = findViewById(R.id.exp_edit_text);

        scoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //เงื่อนไข้เมื่อพิมข้อมูลไม่ครบถ้วน ถ้ากดปุ่ม save info จะขึ้นเตือนว่าเราไม่ได้กรอกข้อมูลส่วนไหน
                if(nameEditText.getText().toString().length()==0) {
                    Toast.makeText(AddUserActivity.this,"Name is null",Toast.LENGTH_SHORT).show();
                }
                else if(bbeEditText.getText().toString().length()==0) {
                    Toast.makeText(AddUserActivity.this,"BBE is null",Toast.LENGTH_SHORT).show();
                }
                else if(mfgEditText.getText().toString().length()==0) {
                    Toast.makeText(AddUserActivity.this,"MFG is null",Toast.LENGTH_SHORT).show();
                }
                else if(expEditText.getText().toString().length()==0) {
                    Toast.makeText(AddUserActivity.this,"EXP is null",Toast.LENGTH_SHORT).show();
                }

                //เงื่อนไขเมื่อกรอกข้อมูลทุกอย่างสมบูรณ์
                else {
                    //แปลงค่าจากตัวอักษรให้เป็น String
                    //ที่กำหนดให้วันที่ควรบริโภคก่อน วันผลิต และวันหมดอายุเป็น String เพราะวันหมดอายุหรือวันผลิตอาจจะมีแค่เดือนเลยกำหนดให้ String เพื่อพิมเป็นชื่อเดือนได้
                    String name = nameEditText.getText().toString();
                    String bbe = bbeEditText.getText().toString();
                    String mfg = mfgEditText.getText().toString();
                    String exp = expEditText.getText().toString();

                    //สร้าง User ที่ต้องการจัดเก็บโดยกำหนดพารามิเตอร์ตามที่ผู้ใช้กรอก
                    final User user = new User(0, name,bbe,mfg,exp);

                    //แยกเธรดในการเชื่อมต่อฐานข้อมูล เพื่อเพิ่มข้อมูลลงฐานข้อมูล
                    AppExecutors executors = new AppExecutors();
                    executors.diskIO().execute(new Runnable() {
                        @Override
                        public void run() { // worker thread
                            AppDatabase db = AppDatabase.getInstance(AddUserActivity.this);
                            db.userDao().addUser(user);
                            finish();
                        }
                    });

                }

            }
        });


    }
}


