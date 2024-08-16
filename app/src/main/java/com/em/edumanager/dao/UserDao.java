package com.em.edumanager.dao;

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
		} catch (Exception e) {
			//Open in read-only mode when there is insufficient memory
			this.st=this.myhelper.getReadableDatabase();
		}
		//create a SQL
		String sql="create table if not exists "+TB_NAME+"("+COL1+"    integer primary key autoincrement , "+
		COL2+"  vachar(20),"+COL3+"  varchar(20),"+COL4+"  varchar(20),"+COL5+"   varchar(20))";
		try {
			//execute
			this.st.execSQL(sql);
		} catch (Exception e) {
			Log.e("Exception in creating user table",e.toString());
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
        return this.st.insert(TB_NAME,null,values);
	}
	/**
	 * validate user by username and password
	 */
	public UserInfo CheckUser(String firstname,String lastname,String password){
		UserInfo userLogin=null;
		//create and execute a query SQL
		String sql="select * from "+TB_NAME+" where "+COL2+"=?  and "+COL3+"=? and "+COL5+"=?";
		Cursor cursor=this.st.rawQuery(sql, new String[]{firstname,lastname,password});
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
		}
		cursor.close();
		return userLogin;
	}

}
























































