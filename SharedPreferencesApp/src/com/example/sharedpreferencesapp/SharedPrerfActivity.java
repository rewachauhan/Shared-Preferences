package com.example.sharedpreferencesapp;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SharedPrerfActivity extends Activity {
	EditText et1,et2;
	Button b1;
	TextView reg,pro;
	SharedPreferences pref;

    @SuppressLint("NewApi") @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_prerf);
        getActionBar().hide();
        pref=getApplicationContext().getSharedPreferences("MyPref",Context.MODE_PRIVATE );
        if(pref.getString("KEY_USERNAME", null)!=null){
        	finish();
        	startActivity(new Intent(getApplicationContext(),ProfilePage.class));
        	return;
        }
        et1=(EditText) findViewById(R.id.editText1);
        et2=(EditText) findViewById(R.id.editText2);
        b1=(Button) findViewById(R.id.button1);
        reg=(TextView) findViewById(R.id.textView3);
        reg.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				startActivity(new Intent(getApplicationContext(),SignInPage.class));
				finish();
			}
		});
        final SQLiteDatabase mydatabase=openOrCreateDatabase("mydb.dat",MODE_PRIVATE,null);
        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS Mytable(uID VARCHAR Primary key ,uName VARCHAR,uPassword VARCHAR);");
        b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String uname=et1.getText().toString().trim();
				String upass=et2.getText().toString().trim();
				if(uname.length()==0||upass.length()==0)
				{
					Toast.makeText(getApplicationContext(), "please fill all fields", Toast.LENGTH_LONG).show();
					return;
				}
				String sql="SELECT * FROM Mytable WHERE uName = '"+uname+"' AND uPassword='"+upass+"';";
				Cursor c = mydatabase.rawQuery(sql,null);
				if(!(c.moveToFirst())){
					Toast.makeText(getApplicationContext(), "login successful", Toast.LENGTH_LONG).show();
					Editor editor=pref.edit();
					editor.putString("KEY_USERNAME", uname);
					editor.commit();
					startActivity(new Intent(getApplicationContext(),ProfilePage.class));
					finish();
				}
				else{
					Toast.makeText(getApplicationContext(), "Login Failed",Toast.LENGTH_LONG).show();
				}
			}
		});
        pro=(TextView) findViewById(R.id.textView4);
        pro.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				startActivity(new Intent(getApplicationContext(),ProfilePage.class));
				finish();
			}
		});
       }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
