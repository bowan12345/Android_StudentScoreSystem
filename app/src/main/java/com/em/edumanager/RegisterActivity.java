package com.em.edumanager;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.em.edumanager.bean.UserInfo;
import com.em.edumanager.dao.UserDao;

public class RegisterActivity extends Activity {
    //declare fields
	EditText firstname,lastname,email, password, rePassword;
	Button butsave;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		this.init();
		//save button listener
		this.butsave.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				SaveAction();
			}
		});
	}

	private void init(){
		firstname=findViewById(R.id.firstnameText);
		lastname=findViewById(R.id.lastnameText);
		email=findViewById(R.id.emailText);
		password=findViewById(R.id.passwordText);
		rePassword=findViewById(R.id.rePasswordText);
		butsave=findViewById(R.id.registerSave);
	}
	/**
	 * save method
	 */
	public void SaveAction(){
		        //get user info by inputs
				String firstname=this.firstname.getText().toString();
				String lastname=this.lastname.getText().toString();
				String email=this.email.getText().toString();
				String password=this.password.getText().toString();
				String rePassword=this.rePassword.getText().toString();
				//if name or password are empty
				if(firstname.isEmpty() || lastname.isEmpty() ||email.isEmpty() ||password.isEmpty() || rePassword.isEmpty()){
					Toast.makeText(this,"Please Enter Username and Password",Toast.LENGTH_LONG).show();
					return;
				}
				//The passwords do not match
				if(!password.equals(rePassword)){
					Toast.makeText(this,"The passwords do not match",Toast.LENGTH_LONG).show();
					return;
				}
				// add new user into database
				UserInfo userInfo = new UserInfo();
				userInfo.setFirstname(firstname.toLowerCase());
				userInfo.setLastname(lastname.toLowerCase());
				userInfo.setEmail(email);
				userInfo.setPassword(password);
				UserDao userDao=new UserDao(this);
				long n=userDao.AddUser(userInfo);
				if(n>0){
					//New user registration successful
					Toast.makeText(this,"New user registration successful",Toast.LENGTH_LONG).show();
		    		this.finish();
				}else{
					//New user registration failed
					Toast.makeText(this,"New user registration failed",Toast.LENGTH_LONG).show();
				}
	}
	/**
	 * clear method
	 */
	public void ResetAction(){
		this.firstname.setText("");
		this.lastname.setText("");
		this.email.setText("");
		this.password.setText("");
		this.rePassword.setText("");
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
