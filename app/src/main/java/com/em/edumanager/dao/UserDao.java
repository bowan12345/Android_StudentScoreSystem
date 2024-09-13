package com.em.edumanager.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.em.edumanager.bean.UserInfo;

/**
 * 
 * user data operations
 *
 */
@SuppressLint("Range")
public class UserDao {
	//declare fields
	MySqitHelper myhelper;
	SQLiteDatabase st;//
	String TB_NAME="user_info";
	String COL1="id";
	String COL2="firstname";
	String COL3="lastname";
	String COL4="email";
	String COL5="password";


	public UserDao(Context context){
		//connecting the database
		this.myhelper=new MySqitHelper(context);
		try {
			//Open in read-write mode when there is sufficient memory available
			this.st=this.myhelper.getWritableDatabase();
			Log.d("UserDao", "Database opened in read-write mode");
		} catch (Exception e) {
			//Open in read-only mode when there is insufficient memory
			this.st=this.myhelper.getReadableDatabase();
			Log.d("UserDao", "Database opened in read-only mode");
		}
		//create a SQL
		String sql="create table if not exists "+TB_NAME+"("+COL1+"    integer primary key autoincrement , "+
				COL2+"  vachar(20),"+COL3+"  varchar(20),"+COL4+"  varchar(20),"+COL5+"   varchar(20))";
		try {
			//execute
			this.st.execSQL(sql);
			Log.d("UserDao", "User table created");
		} catch (Exception e) {
			Log.e("UserDao", "Exception in creating user table: " + e.toString());
			throw new RuntimeException(e);
		}
	}
	/**
	 * add new user
	 */
	public long AddUser(UserInfo userInfo){
		ContentValues values=new ContentValues();
		values.put(COL2,userInfo.getFirstname());
		values.put(COL3,userInfo.getLastname());
		values.put(COL4,userInfo.getEmail());
		values.put(COL5,userInfo.getPassword());
		//create and execute a insert SQL
		long n = this.st.insert(TB_NAME,null,values);
		Log.d("UserDao", "New user added: " + userInfo.getFirstname() + " " + userInfo.getLastname());
		return n;
	}
	/**
	 * validate user by username and password
	 */
	public UserInfo CheckUser(String firstname,String lastname,String password){
		UserInfo userLogin=null;
		//create and execute a query SQL
		String sql="select * from "+TB_NAME+" where "+COL2+"=?  and "+COL3+"=? and "+COL5+"=?";
		Cursor cursor=this.st.rawQuery(sql, new String[]{firstname,lastname,password});
		Log.d("UserDao", "Checking user: " + firstname + " " + lastname);
		//validate results
		if(cursor.moveToNext()){
			//loading vaild data
			int _id=cursor.getInt(cursor.getColumnIndex(COL1));
			//initial userLogin onject
			userLogin=new UserInfo();
			//set properties
			userLogin.setId(_id);
			userLogin.setFirstname(firstname);
			userLogin.setLastname(lastname);
			userLogin.setPassword(password);
			Log.d("UserDao", "User found: " + firstname + " " + lastname);
		} else {
			Log.d("UserDao", "User not found: " + firstname + " " + lastname);
		}
		cursor.close();
		return userLogin;
	}

}

























































