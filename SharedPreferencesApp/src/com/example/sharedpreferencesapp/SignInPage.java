package com.example.sharedpreferencesapp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignInPage extends Activity {
	EditText uId,uName,uPassword;
	TextView go;
	Button b;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_in_page);
		uId=(EditText) findViewById(R.id.editText1);
        uName=(EditText) findViewById(R.id.editText2);
        uPassword=(EditText) findViewById(R.id.editText3);
        //et2=(EditText) findViewById(R.id.editText2);
        b=(Button) findViewById(R.id.button1);
        go=(TextView) findViewById(R.id.textView3);
        go.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				startActivity(new Intent(getApplicationContext(),SharedPrerfActivity.class));
				finish();
			}
		}) ;
        final SQLiteDatabase mydatabase= openOrCreateDatabase("mydb.dat",MODE_PRIVATE,null);
        b.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String userId=uId.getText().toString().trim();
				String userName=uName.getText().toString().trim();
				String userPass=uPassword.getText().toString().trim();
				if(userId.length()==0||userName.length()==0||userPass.length()==0)
				{
					Toast.makeText(getApplicationContext(), "Please fill all fields",Toast.LENGTH_LONG).show();
					return;
				}
				String sqlFetch="SELECT * FROM Mytable WHERE uId = '"+uId+"'";
				Cursor c = mydatabase.rawQuery(sqlFetch, null);
				if(c.getCount()>0){
					Toast.makeText(getApplicationContext(), "User Id already Exists", Toast.LENGTH_LONG).show();
					
				}
				else{
					String sql="INSERT INTO Mytable VALUES('"+userId+"','"+userName+"','"+userPass+"');";
					mydatabase.execSQL(sql);
					Toast.makeText(getApplicationContext(), "Registered successfully", Toast.LENGTH_LONG).show();
				}
				uId.setText("");
				uName.setText("");
				uPassword.setText("");
				
			}
		});


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sign_in_page, menu);
		return true;
	}

}
