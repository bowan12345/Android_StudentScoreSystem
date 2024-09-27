package com.em.edumanager.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.em.edumanager.bean.StudentGrade;

import java.util.ArrayList;
import java.util.List;

public class StudentGradeDao {
    //declare fields
    MySqitHelper myHelper;
    SQLiteDatabase st;//
    String TB_NAME="StudentGradetb";
    String COL1="_id";
    String COL2="student_id";
    String COL3="firstname";
    String COL4="lastname";
    String COL5="android";
    String COL6="java";
    String COL7="html";
    //构造方法
    public StudentGradeDao(Context context) {
        //connecting the database
        this.myHelper = new MySqitHelper(context);
        try {
            //Open in read-write mode when there is sufficient memory available
            this.st = this.myHelper.getWritableDatabase();
        } catch (Exception e) {
            //Open in read-only mode when there is insufficient memory
            this.st = this.myHelper.getReadableDatabase();
        }
        //create student info table
        try {
            String sql = "create table if not exists " + TB_NAME + "(" + COL1 + "   integer primary key autoincrement , " +
                    COL2 + "  varchar(10)," + COL3 + "  varchar(10)," + COL4 + "  varchar(10)," + COL5 + "  varchar(10)," + COL6 + "  varchar(10)," + COL7 + "  varchar(10))";
            //execute
            this.st.execSQL(sql);
        } catch (Exception e) {
            Log.e("Exception in creating student grades table", e.toString());
        }
    }
    /**
     * add student grade info
     */
    public long addStudentGrade(StudentGrade grade){
        ContentValues values=new ContentValues();
        values.put(COL2, grade.getStudentId());
        values.put(COL3, grade.getFirstname());
        values.put(COL4, grade.getLastname());
        values.put(COL5, grade.getAndroid());
        values.put(COL6, grade.getJava());
        values.put(COL7, grade.getHtml());
        //execute
        long n=this.st.insert(TB_NAME, null, values);
        return n;
    }

    /**
     * query all student grades
     */
    public List<StudentGrade> getAllStudentGrades(){
        //declare a list to receive
        List<StudentGrade> gradeList=new ArrayList<>();
        //create query SQL
        String sql="select * from  "+TB_NAME;
        //execute
        Cursor cursor=this.st.rawQuery(sql, null);
        //results
        assignGrade(cursor, gradeList);
        return gradeList;
    }

    private void assignGrade(Cursor cursor, List<StudentGrade> gradeList) {
        while(cursor.moveToNext()){
            //load student grade details
            @SuppressLint("Range")int id= cursor.getInt(cursor.getColumnIndex(COL1));
            @SuppressLint("Range")String num= cursor.getString(cursor.getColumnIndex(COL2));
            @SuppressLint("Range")String firstname= cursor.getString(cursor.getColumnIndex(COL3));
            @SuppressLint("Range")String lastname= cursor.getString(cursor.getColumnIndex(COL4));
            @SuppressLint("Range")String android= cursor.getString(cursor.getColumnIndex(COL5));
            @SuppressLint("Range")String java= cursor.getString(cursor.getColumnIndex(COL6));
            @SuppressLint("Range")String html= cursor.getString(cursor.getColumnIndex(COL7));
            //declare a student grade object to receive info
            StudentGrade grade=new StudentGrade();
            grade.setId(id);
            grade.setStudentId(num);
            grade.setFirstname(firstname);
            grade.setLastname(lastname);
            grade.setAndroid(android);
            grade.setJava(java);
            grade.setHtml(html);
            gradeList.add(grade);
        }
        //close and release resources
        cursor.close();
    }

    /**
     * query by student id
     */
    public List<StudentGrade> GetGradeByStudentID(String studentID){
        List<StudentGrade> gradeList=new ArrayList<StudentGrade>();
        //create a query SQL
        String sql="select * from  "+TB_NAME+"  where "+COL2+"=?";
        //execute
        Cursor cursor=this.st.rawQuery(sql, new  String[]{studentID});
        //results
        assignGrade(cursor, gradeList);
        return gradeList;
    }
    /**
     * delete student's grade by student id
     */
    public long DeleteById(String studentID){
        //create and execute a query SQL
        long n=this.st.delete(TB_NAME, COL2+"=?", new String[]{studentID});
        //close and release resources
        return n;
    }
    /**
     * update student's grades by student id
     */
    public long UpdateById(StudentGrade grade){
        ContentValues values=new ContentValues();
        //set values to update
        values.put(COL2, grade.getStudentId());
        values.put(COL3, grade.getFirstname());
        values.put(COL4, grade.getLastname());
        values.put(COL5, grade.getAndroid());
        values.put(COL6, grade.getJava());
        values.put(COL7, grade.getHtml());
        //create and execute a query SQL
        long n=this.st.update(TB_NAME, values, COL2+"=?", new String[]{  String.valueOf(  grade.getStudentId())  });
        return n;
    }


}
