package com.example.cse489_2019262035;/*
	- Change the class name and DBname, table name, and the attributes name according to the Lecture Summary app
	- Complete all functions (insert, update, ...) considering all attributes
*/


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ClassSummaryDB extends SQLiteOpenHelper {

	public ClassSummaryDB(Context context) {
		super(context, "ClassSummaryDB.db", null, 2);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		System.out.println("DB@OnCreate");
		String sql = "CREATE TABLE lectures  ("
								+ "ID TEXT PRIMARY KEY,"
								+ "course TEXT,"
								+ "type TEXT,"
								+ "datetime TEXT,"
								+ "lecture TEXT,"
								+ "topic TEXT,"
								+ "summary TEXT"
								+ ")";
		db.execSQL(sql);
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		System.out.println("Write code to modify database schema here");
		// db.execSQL("ALTER table my_table  ......");
		// db.execSQL("CREATE TABLE  ......");
	}
	public void insertLecture(String ID, String course, String type, String date, String lecture, String topic, String summary) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cols = new ContentValues();
		cols.put("ID", ID);
		cols.put("course", course);
		cols.put("type", type);
		cols.put("datetime", date);
		cols.put("lecture", lecture);
		cols.put("topic", topic);
		cols.put("summary", summary);
		db.insert("lectures", null ,  cols);
		//db.close();
	}
	public void updateLecture(String ID, String course, String type, String date, String lecture, String topic, String summary) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cols = new ContentValues();
		cols.put("course", course);
		cols.put("type", type);
		cols.put("datetime", date);
		cols.put("lecture", lecture);
		cols.put("topic", topic);
		cols.put("summary", summary);
  		db.update("lectures", cols, "ID=?", new String[] {ID} );
		db.close();
	}
	public void deleteLecture(String ID) {
		SQLiteDatabase db = this.getWritableDatabase();
  		db.delete("lectures", "ID=?", new String[ ] {ID} );
		db.close();
	}
	public Cursor selectLectures(String query) {
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cur = null;
		try {
			cur = db.rawQuery(query, null);
		} catch (Exception e){
			e.printStackTrace();
		}
		return cur;
	}
}