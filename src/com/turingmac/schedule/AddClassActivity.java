package com.turingmac.schedule;

import java.util.ArrayList;

import com.turingmac.schedule.R;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddClassActivity extends Activity {

	ClassDatabaseHelper dbHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (MainActivity.currentUser.getNickName().equals("")) {
			Toast.makeText(AddClassActivity.this, "用户未登陆", Toast.LENGTH_SHORT)
					.show();
			finish();
			return;
		}
		setContentView(R.layout.activity_addclass);
		dbHelper = new ClassDatabaseHelper(this,
				MainActivity.currentUser.getNickName() + ".db3", 1);

		Button addclass = (Button) findViewById(R.id.AddClass);
		addclass.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				EditText editText_1, editText_2, editText_3, editText_4, editText_5, editText_6;
				while (true) {
					editText_1 = (EditText) findViewById(R.id.editText_AddClassClassName);
					editText_2 = (EditText) findViewById(R.id.editText_AddClassFromClassNum);
					editText_3 = (EditText) findViewById(R.id.editText_AddClassClassNumLen);
					editText_4 = (EditText) findViewById(R.id.editText_AddClassWeekDay);
					editText_5 = (EditText) findViewById(R.id.editText_AddClassClassRoom);
					editText_6 = (EditText) findViewById(R.id.editText_AddClassTeacher);
					if (editText_1.getText().toString().equals("")) {
						Toast.makeText(AddClassActivity.this, "课程名称不能为空",
								Toast.LENGTH_SHORT).show();
						return;
					} else if (editText_2.getText().toString().equals("")) {
						Toast.makeText(AddClassActivity.this, "起始节数不能为空",
								Toast.LENGTH_SHORT).show();
						return;
					} else if (editText_3.getText().toString().equals("")) {
						Toast.makeText(AddClassActivity.this, "持续节数不能为空",
								Toast.LENGTH_SHORT).show();
						return;
					} else if (editText_3.getText().toString().equals("")) {
						Toast.makeText(AddClassActivity.this, "星期不能为空",
								Toast.LENGTH_SHORT).show();
						return;
					} else if (editText_3.getText().toString().equals("")) {
						Toast.makeText(AddClassActivity.this, "教室不能为空",
								Toast.LENGTH_SHORT).show();
						return;
					} else if (editText_3.getText().toString().equals("")) {
						Toast.makeText(AddClassActivity.this, "教师不能为空",
								Toast.LENGTH_SHORT).show();
						return;
					} else
						break;
				}

				ClassInfo classinfo = new ClassInfo(editText_1.getText()
						.toString(), Integer.parseInt(editText_2.getText()
						.toString()), Integer.parseInt(editText_3.getText()
						.toString()), Integer.parseInt(editText_4.getText()
						.toString()), editText_5.getText().toString(),
						editText_6.getText().toString());
				insertData(dbHelper.getReadableDatabase(), classinfo);

				Toast.makeText(AddClassActivity.this, "添加课程成功",
						Toast.LENGTH_SHORT).show();

				finish();
			}
		});

	}

	private void insertData(SQLiteDatabase db, ClassInfo classinfo) {

		db.execSQL(
				"insert into Class values(null  , ? , ? , ? , ? , ? , ? )",
				new String[] { classinfo.getClassName(),
						classinfo.getFromClassNum() + "",
						classinfo.getClassNumLen() + "",
						classinfo.getWeekDay() + "", classinfo.getClassRoom(),
						classinfo.getTeacher() });
		ArrayList<ClassInfo> classList = new ArrayList<ClassInfo>();

		Cursor cursor = dbHelper.getReadableDatabase().rawQuery(
				"select * from Class", null);

		while (cursor.moveToNext()) {
			classList.add(new ClassInfo(cursor.getString(1), Integer
					.parseInt(cursor.getString(2)), Integer.parseInt(cursor
					.getString(3)), Integer.parseInt(cursor.getString(4)),
					cursor.getString(5), cursor.getString(6)));
		}

		ScheduleView scheduleView;
		scheduleView = (ScheduleView) MainActivity.scheduleView;
		scheduleView.setClassList(classList);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (dbHelper != null) {
			dbHelper.close();
		}
	}
}
