package com.em.edumanager.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.em.edumanager.bean.StudentInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * student info table
 *
 */
@SuppressLint("Range")
public class StudentInfoDao {
	//declare fields
	MySqitHelper myHelper;//database connector
	SQLiteDatabase st;//
	String TB_NAME="StudentInfotb";
	String COL1="_id";
	String COL2="student_id";
	String COL3="firstname";
	String COL4="lastname";
	String COL5="gender";
	String COL6="age";
	String COL7="major";
	String COL8="remark";

	public StudentInfoDao(Context context){
		//connecting the database
		this.myHelper=new MySqitHelper(context);
		try {
			//Open in read-write mode when there is sufficient memory available
			this.st=this.myHelper.getWritableDatabase();
			Log.d("StudentInfoDao", "Database opened in read-write mode");
		} catch (Exception e) {
			//Open in read-only mode when there is insufficient memory
			this.st=this.myHelper.getReadableDatabase();
			Log.d("StudentInfoDao", "Database opened in read-only mode");
		}
		//create student info table
		try {
			String sql="create table if not exists "+TB_NAME+"("+COL1+"   integer primary key autoincrement , "+
					COL2+"  varchar(10),"+COL3+"  varchar(10),"+COL4+"  varchar(10),"+COL5+"  varchar(10)," +COL6+
					"  varchar(10),"+COL7+"  varchar(100) ,"+COL8+"  varchar(200) )";
			//execute
			this.st.execSQL(sql);
			Log.d("StudentInfoDao", "Student info table created");
		} catch (Exception e) {
			Log.e("StudentInfoDao", "Exception in creating student information table: " + e.toString());
			throw new RuntimeException(e);
		}
	}
	/**
	 * add student info
	 */
	public long addStudentInfo(StudentInfo tem){
		ContentValues values=new ContentValues();
		values.put(COL2, tem.getStudentID());
		values.put(COL3, tem.getFirstname());
		values.put(COL4, tem.getLastname());
		values.put(COL5, tem.getGender());
		values.put(COL6, tem.getAge());
		values.put(COL7, tem.getMajor());
		values.put(COL8, tem.getRemark());
		//execute
		long n=this.st.insert(TB_NAME, null, values);
		Log.d("StudentInfoDao", "Student info added: " + tem.getStudentID());
		return n;
	}

	/**
	 * query all student infos
	 */

	public List<StudentInfo> GetAllStudentInfo(){
		//declare a list to receive
		List<StudentInfo> studentInfos=new ArrayList<>();
		//create query SQL
		String sql="select * from  "+TB_NAME;
		//execute
		Cursor cursor=this.st.rawQuery(sql, null);
		Log.d("StudentInfoDao", "Querying all student infos");
		//results
		while(cursor.moveToNext()){
			//load student details
			try {
				int id = cursor.getInt(cursor.getColumnIndex(COL1) == -1 ? 0 : cursor.getColumnIndex(COL1));
				String studentID = cursor.getString(cursor.getColumnIndex(COL2) == -1 ? 0 : cursor.getColumnIndex(COL2));
				String firstname = cursor.getString(cursor.getColumnIndex(COL3) == -1 ? 0 : cursor.getColumnIndex(COL3));
				String lastname = cursor.getString(cursor.getColumnIndex(COL4) == -1 ? 0 : cursor.getColumnIndex(COL4));
				String gender = cursor.getString(cursor.getColumnIndex(COL5) == -1 ? 0 : cursor.getColumnIndex(COL5));
				String age = cursor.getString(cursor.getColumnIndex(COL6) == -1 ? 0 : cursor.getColumnIndex(COL6));
				String major = cursor.getString(cursor.getColumnIndex(COL7) == -1 ? 0 : cursor.getColumnIndex(COL7));
				String remark = cursor.getString(cursor.getColumnIndex(COL8) == -1 ? 0 : cursor.getColumnIndex(COL8));

				//declare a student object to receive info
				StudentInfo student = new StudentInfo();
				student.setId(id);
				student.setStudentID(studentID);
				student.setFirstname(firstname);
				student.setLastname(lastname);
				student.setGender(gender);
				student.setAge(age);
				student.setMajor(major);
				student.setRemark(remark);
				//add to list
				studentInfos.add(student);
			} catch (Exception e) {
				Log.e("StudentInfoDao", "Exception in loading student information: " + e.toString());
				throw new RuntimeException(e);
			}
		}
		//close and release resources
		cursor.close();
		Log.d("StudentInfoDao", "All student infos queried");
		return studentInfos;
	}

	/**
	 * query by student id
	 */
	public List<StudentInfo> GetStudentByStudentID(String num0){
		List<StudentInfo> studentInfos=new ArrayList<>();
		//create a query SQL
		String sql="select * from  "+TB_NAME+"  where "+COL2+"=?";
		//execute
		Cursor cursor=this.st.rawQuery(sql, new  String[]{num0});
		Log.d("StudentInfoDao", "Querying student info by ID: " + num0);
		//results
		if(cursor.moveToNext()){
			try {
				//load student details
				int id = cursor.getInt(cursor.getColumnIndex(COL1) == -1 ? 0 : cursor.getColumnIndex(COL1));
				String studentID = cursor.getString(cursor.getColumnIndex(COL2) == -1 ? 0 : cursor.getColumnIndex(COL2));
				String firstname = cursor.getString(cursor.getColumnIndex(COL3) == -1 ? 0 : cursor.getColumnIndex(COL3));
				String lastname = cursor.getString(cursor.getColumnIndex(COL4) == -1 ? 0 : cursor.getColumnIndex(COL4));
				String gender = cursor.getString(cursor.getColumnIndex(COL5) == -1 ? 0 : cursor.getColumnIndex(COL5));
				String age = cursor.getString(cursor.getColumnIndex(COL6) == -1 ? 0 : cursor.getColumnIndex(COL6));
				String major = cursor.getString(cursor.getColumnIndex(COL7) == -1 ? 0 : cursor.getColumnIndex(COL7));
				String remark = cursor.getString(cursor.getColumnIndex(COL8) == -1 ? 0 : cursor.getColumnIndex(COL8));

				//declare a student object to receive info
				StudentInfo student = new StudentInfo();
				student.setId(id);
				student.setStudentID(studentID);
				student.setFirstname(firstname);
				student.setLastname(lastname);
				student.setGender(gender);
				student.setAge(age);
				student.setMajor(major);
				student.setRemark(remark);
				//add to list
				studentInfos.add(student);
			} catch (Exception e) {
				Log.e("StudentInfoDao", "Exception in loading student information: " + e.toString());
				throw new RuntimeException(e);
			}
		}
		//close and release resources
		cursor.close();
		Log.d("StudentInfoDao", "Student info queried by ID: " + num0);
		return studentInfos;
	}
	/**
	 * delete student by id
	 */
	public long DeleteById(String studentID){
		//create and execute a query SQL
		long n=this.st.delete(TB_NAME, COL2+"=?", new String[]{studentID});
		Log.d("StudentInfoDao", "Student info deleted by ID: " + studentID);
		return n;
	}
	/**
	 * update student details by student id
	 */
	public long UpdateById(StudentInfo student){
		ContentValues values=new ContentValues();
		values.put(COL2, student.getStudentID());
		values.put(COL3, student.getFirstname());
		values.put(COL4, student.getLastname());
		values.put(COL5, student.getGender());
		values.put(COL6, student.getAge());
		values.put(COL7, student.getMajor());
		values.put(COL8, student.getRemark());
		//create and execute a query SQL
		long n=this.st.update(TB_NAME, values, COL2+"=?", new String[]{  String.valueOf(  student.getStudentID())  });
		Log.d("StudentInfoDao", "Student info updated by ID: " + student.getStudentID());
		return n;
	}


	public  static void main(String args[]){
//			AddStudentInfoDao adao=new AddStudentInfoDao();
	}
}

