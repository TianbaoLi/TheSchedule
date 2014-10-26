package com.turingmac.schedule;

import java.util.ArrayList;

import com.turingmac.schedule.R;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

	UserDatabaseHelper dbHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		dbHelper = new UserDatabaseHelper(this, "UserInfo.db3", 1);
		setContentView(R.layout.activity_login);

		Button login = (Button) findViewById(R.id.Login);
		login.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				EditText editText_1, editText_2;
				while (true) {
					editText_1 = (EditText) findViewById(R.id.editText_LoginNickName);
					editText_2 = (EditText) findViewById(R.id.editText_LoginPassword);
					if (editText_1.getText().toString().equals("")) {
						Toast.makeText(LoginActivity.this, "êÇ³Æ²»ÄÜÎª¿Õ",
								Toast.LENGTH_SHORT).show();
						return;
					} else if (editText_2.getText().toString().equals("")) {
						Toast.makeText(LoginActivity.this, "ÃÜÂë²»ÄÜÎª¿Õ",
								Toast.LENGTH_SHORT).show();
						return;
					} else
						break;
				}

				Cursor cursor = dbHelper.getReadableDatabase().rawQuery(
						"select * from UserInfo where nickName like ?",
						new String[] { "%" + editText_1.getText().toString()
								+ "%" });
				while (cursor.moveToNext()) {
					String targetPassowrd = cursor.getString(2);

					if (editText_2.getText().toString().equals(targetPassowrd)) {
						Toast.makeText(LoginActivity.this,
								"»¶Ó­ " + editText_1.getText().toString(),
								Toast.LENGTH_SHORT).show();
						MainActivity.currentUser = new UserInfo(cursor
								.getString(1), cursor.getString(2), cursor
								.getString(3));
						dbHelper.close();

						ClassDatabaseHelper DBHelper = new ClassDatabaseHelper(
								LoginActivity.this, MainActivity.currentUser
										.getNickName() + ".db3", 1);
						cursor = DBHelper.getReadableDatabase().rawQuery(
								"select * from Class", null);
						ArrayList<ClassInfo> classList = new ArrayList<ClassInfo>();
						while (cursor.moveToNext()) {
							classList.add(new ClassInfo(cursor.getString(1),
									Integer.parseInt(cursor.getString(2)),
									Integer.parseInt(cursor.getString(3)),
									Integer.parseInt(cursor.getString(4)),
									cursor.getString(5), cursor.getString(6)));
						}
						DBHelper.close();

						ScheduleView scheduleView;
						scheduleView = (ScheduleView) MainActivity.scheduleView;
						scheduleView.setClassList(classList);
						finish();
						return;
					} else {
						Toast.makeText(LoginActivity.this, "ÃÜÂë´íÎó",
								Toast.LENGTH_SHORT).show();
						dbHelper.close();
						return;
					}
				}
				Toast.makeText(LoginActivity.this, "êÇ³Æ´íÎó", Toast.LENGTH_SHORT)
						.show();
				dbHelper.close();
			}
		});
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (dbHelper != null) {
			dbHelper.close();
		}
	}
}
