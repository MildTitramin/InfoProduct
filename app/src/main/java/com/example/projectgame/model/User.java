package com.example.projectgame.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "info")

//คลาสต้นแบบในการออกแบบฐานข้อมูล
public class User {

    //กำหนดคอลัมภ์ชื่อ primaryKey
    @PrimaryKey(autoGenerate = true)
    public final int id;

    //กำหนดคอลัมภ์ชื่อ name เก็บชื่อ
    @ColumnInfo(name = "name")
    public final String name;

    //กำหนดคอลัมภ์ชื่อ bbe เก็บวันที่ควรบริโภคก่อน
    @ColumnInfo(name = "bbe")
    public final String bbe;

    //กำหนดคอลัมภ์ชื่อ mfg เก็บวันผลิต
    @ColumnInfo(name = "mfg")
    public final String mfg;

    //กำหนดคอลัมภ์ชื่อ exp เก็บวันหมดอายุ
    @ColumnInfo(name = "exp")
    public final String exp;


    //เมธอดสร้างวัตถุ
    public User(int id, String name, String bbe, String mfg, String exp) {
        this.id = id;
        this.name = name;
        this.bbe = bbe;
        this.mfg = mfg;
        this.exp = exp;
    }
}

