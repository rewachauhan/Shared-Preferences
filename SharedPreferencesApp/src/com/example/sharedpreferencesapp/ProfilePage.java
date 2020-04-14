package com.example.sharedpreferencesapp;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class ProfilePage extends Activity {
	SharedPreferences spf;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile_page);
		spf=getApplicationContext().getSharedPreferences("MyPref", Context.MODE_PRIVATE);
		if(spf.getString("KEY_USERNAME",null)==null)
		{
			finish();
			Toast.makeText(getApplicationContext(), "you have to login first", Toast.LENGTH_LONG).show();
			startActivity(new Intent(getApplicationContext(),SharedPrerfActivity.class));
			return;
		}
		
	}

	@SuppressLint("NewApi") @Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		if(item.getItemId() == R.id.menuLogout)
		{
			SharedPreferences shrp= getSharedPreferences("MyPref", Context.MODE_PRIVATE);
			Editor editor= shrp.edit();
			editor.clear();
			editor.apply();
			finish();
			Toast.makeText(getApplicationContext(), "logout successful", Toast.LENGTH_LONG).show();
			startActivity(new Intent(getApplicationContext(),SharedPrerfActivity.class));
			
			
		}
		return true;
		//return super.onOptionsItemSelected(item);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.profile_page, menu);
		return true;
	}

}
