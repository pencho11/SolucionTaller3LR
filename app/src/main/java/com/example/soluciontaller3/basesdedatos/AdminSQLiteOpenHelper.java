package com.example.soluciontaller3.basesdedatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase basesDeDatos) {
        basesDeDatos.execSQL("create table notas(codigo int primary key, nombre text, nota1 int, nota2 int, nota3 int, nota4 int, total int)");
        basesDeDatos.execSQL("create table notas1(codigo int primary key, nombre text, nota1 int, nota2 int, nota3 int, total int)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
