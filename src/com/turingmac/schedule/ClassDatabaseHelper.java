package com.turingmac.schedule;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ClassDatabaseHelper extends SQLiteOpenHelper {
	private String CREATE_TABLE_SQL;

	public ClassDatabaseHelper(Context context, String name, int version) {
		super(context, name, null, version);
		CREATE_TABLE_SQL = "create table Class(_id integer primary key autoincrement , classname , fromclassnum, classnumlen , weekday , classroom , teacher )";
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE_SQL);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		System.out.println("--------onUpdate Called--------" + oldVersion
				+ "--->" + newVersion);
	}

}
