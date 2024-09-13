package com.em.edumanager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import com.em.edumanager.bean.UserInfo;
import com.em.edumanager.dao.CommonData;
import com.em.edumanager.dao.UserDao;


public class LoginActivity extends Activity {
	//declare fields
	EditText username,password;
	Button loginButton,registerButton, contactButton;
	CheckBox infoCheckedBox;
	private final String phoneNumber = "2231490";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		this.init();
		//loading user information
		this.ReadLoginInfo();
		//sign up button listener
		registerButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				//forward to sign up page
				Intent abc=new Intent(LoginActivity.this,RegisterActivity.class);
				startActivity(abc);
				Log.d("LoginActivity", "Register button clicked");
			}
		});
		//sign in button listener
		loginButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				//login method
				LoginAction();
				Log.d("LoginActivity", "Login button clicked");
			}
		});

		//contact us(phone call) button listener
		contactButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//Phone Call method
				Intent dialIntent =  new Intent(Intent.ACTION_DIAL,Uri.parse("tel:" + phoneNumber));
				startActivity(dialIntent);
				Log.d("LoginActivity", "Contact button clicked");
			}
		});

	}


	/**
	 * initial page
	 */
	public void init(){
		this.username= findViewById(R.id.fullName);
		this.password= findViewById(R.id.loginPassword);
		this.loginButton= findViewById(R.id.loginButton);
		this.registerButton= findViewById(R.id.registerButton);
		this.infoCheckedBox= findViewById(R.id.infoCheckedBox);
		this.contactButton= findViewById(R.id.contactUs);
		Log.d("LoginActivity", "Page initialized");
	}

	/**
	 * login
	 */
	public void LoginAction(){
		//get username and password
		String fullName=this.username.getText().toString();
		String password=this.password.getText().toString();
		//if it null
		if(fullName.isEmpty() || password.isEmpty()){
			Toast.makeText(this, "please enter username and password", Toast.LENGTH_LONG).show();
			Log.d("LoginActivity", "Username or password is empty");
			return;
		}
		String[] split = fullName.split("\\.");
		String firstname=split[0].toLowerCase();
		String lastname=split[1].toLowerCase();
		//3.query user info from database
		UserDao uDao=new UserDao(this);
		UserInfo user=uDao.CheckUser(firstname,lastname, password);
		//result
		if(user==null){
			//no matching user
			Toast.makeText(this, "Incorrect username or password", Toast.LENGTH_LONG).show();
			Log.d("LoginActivity", "Incorrect username or password");
		}else{
			//matching user
			Toast.makeText(this, "User login successful", Toast.LENGTH_LONG).show();
			Log.d("LoginActivity", "User login successful: " + fullName);
			SharedPreferences shareSave=getSharedPreferences("loginInfo",Context.MODE_PRIVATE);
			Editor editor=shareSave.edit();
			if(this.infoCheckedBox.isChecked()){
				//User chose to save user information. Display username and password.
				editor.putString("username", fullName);
				editor.putString("password", password);
				editor.commit();
				Log.d("LoginActivity", "User info saved: " + fullName);
			}else{
				//User did not choose to save. Clear --- or clear to save content.
				editor.clear();
				//Display only the usernames of logged-in users without showing passwords.
				editor.putString("username", fullName);
				editor.commit();
				Log.d("LoginActivity", "User info cleared: " + fullName);
			}
			//login successful and forward to main page
			CommonData.userLogin=user;
			Intent intent=new Intent(this,MainActivity.class);
			startActivity(intent);
			this.finish();
			Log.d("LoginActivity", "Forward to main page");
		}
	}
	/**
	 * reading login user info
	 */
	public void ReadLoginInfo(){
		SharedPreferences shareLogin=getSharedPreferences("loginInfo",Context.MODE_PRIVATE);
		// get login values by keys
		String username=shareLogin.getString("username", "");
		String password=shareLogin.getString("password", "");
		//display user info
		this.username.setText(username);
		this.password.setText(password);
		Log.d("LoginActivity", "Login info read: " + username);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_exit) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}









