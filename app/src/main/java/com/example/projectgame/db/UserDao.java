package com.example.projectgame.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.projectgame.model.User;

//interface สำหรับการกำหนดเมธอดในการใช้คำสั่ง sql กับฐานข้อมูล
@Dao
public interface UserDao {

    //เมธอดส่งคืนข้อมูลทั้งหมดที่เก็บในฐานข้อมูล
    @Query("SELECT * FROM info")
    User[] getAllUsers();

    //เมธอดเพิ่มข้อมูลลงฐานข้อมูล
    @Insert
    void addUser(User... users);

    //เมธอดลบข้อมูลที่มีอยู่ในฐานข้อมูล
    @Query("DELETE FROM info")
    void deleteAllUser();



}
