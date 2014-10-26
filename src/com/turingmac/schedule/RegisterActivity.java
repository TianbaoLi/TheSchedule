package com.turingmac.schedule;

import com.turingmac.schedule.R;

import android.os.Bundle;
import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends Activity {

	UserDatabaseHelper dbHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		dbHelper = new UserDatabaseHelper(this, "UserInfo.db3", 1);
		setContentView(R.layout.activity_register);

		Button register = (Button) findViewById(R.id.Register);
		register.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				EditText editText_1, editText_2, editText_3;
				while (true) {
					editText_1 = (EditText) findViewById(R.id.editText_RegisterNickName);
					editText_2 = (EditText) findViewById(R.id.editText_RegisterPassword);
					editText_3 = (EditText) findViewById(R.id.editText_RegisterClassNumber);
					if (editText_1.getText().toString().equals("")) {
						Toast.makeText(RegisterActivity.this, "昵称不能为空",
								Toast.LENGTH_SHORT).show();
						return;
					} else if (editText_2.getText().toString().equals("")) {
						Toast.makeText(RegisterActivity.this, "密码不能为空",
								Toast.LENGTH_SHORT).show();
						return;
					} else if (editText_3.getText().toString().equals("")) {
						Toast.makeText(RegisterActivity.this, "班号不能为空",
								Toast.LENGTH_SHORT).show();
						return;
					} else
						break;
				}
				UserInfo user = new UserInfo(editText_1.getText().toString(),
						editText_2.getText().toString(), editText_3.getText()
								.toString());

				insertData(dbHelper.getReadableDatabase(), user);
				Toast.makeText(RegisterActivity.this, "添加用户成功！",
						Toast.LENGTH_SHORT).show();
				dbHelper.close();
				finish();
			}
		});
	}

	private void insertData(SQLiteDatabase db, UserInfo user) {
		db.execSQL(
				"insert into UserInfo values(null  , ? , ? , ? , ?)",
				new String[] { user.getNickName(), user.getpassword(),
						user.getClassNumber(), user.getMyClass().toString() });
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (dbHelper != null) {
			dbHelper.close();
		}
	}
}
