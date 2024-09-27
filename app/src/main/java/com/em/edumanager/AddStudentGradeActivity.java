package com.em.edumanager;

import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.em.edumanager.bean.StudentGrade;
import com.em.edumanager.bean.StudentInfo;
import com.em.edumanager.dao.StudentGradeDao;
import com.em.edumanager.dao.StudentInfoDao;

import java.io.Console;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddStudentGradeActivity extends Activity {
    //declare fields
    TextView studentId,firstname,lastname;
    EditText studentIDText,androidText,javaText,htmlText;
    Button butQuery,butAdd,butClear;
    List<StudentInfo> studentInfos;//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student_grade);
        init();
        System.out.println("AddStudentGradeActivity");
        //query button listener
        butQuery.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String studentID=studentIDText.getText().toString();
                GetStudentIDByStudentID(studentID);
            }
        });
        //add button listener
        butAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View V) {
                AddGradeAction();
            }
        });
        //clear button listener
        butClear.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                CLearScoreAction();
            }
        });
    }



    //initial all inputs and textViews
    public void init(){
        studentIDText=findViewById(R.id.studentIDGradeEdit);
        androidText=findViewById(R.id.androidGradeEdit);
        javaText=findViewById(R.id.javaGradeEdit);
        htmlText=findViewById(R.id.htmlGradeEdit);
        studentId=findViewById(R.id.studentIDText);
        firstname=findViewById(R.id.firstnameGradeText);
        lastname=findViewById(R.id.lastnameGradeText);
        butQuery=findViewById(R.id.butQueryButton);
        butAdd=findViewById(R.id.butAddButton);
        butClear=findViewById(R.id.butClearButton);

    }
    /**
     *get query results by student id(single query)
     *
     */
    public void GetStudentIDByStudentID(String studentID){
        //1.query from database
        StudentInfoDao studentInfoDao=new StudentInfoDao(this);
        studentInfos=studentInfoDao.GetStudentByStudentID(studentID);
        //2.get first item
        if(studentInfos.size()==0){
            Toast.makeText(this, "No student found", Toast.LENGTH_LONG).show();
            return;
        }
        studentId.setText(studentInfos.get(0).getStudentID());
        firstname.setText(studentInfos.get(0).getFirstname());
        lastname.setText(studentInfos.get(0).getLastname());
    }

    //add button
    public void AddGradeAction(){
        //get user info by inputs
        String stuId=studentId.getText().toString();
        String firstName=firstname.getText().toString();
        String lastName=lastname.getText().toString();
        String androidGrade=androidText.getText().toString();
        String javaGrade=javaText.getText().toString();
        String htmlGrade=htmlText.getText().toString();

        //storage info into database
        StudentGrade grade=new StudentGrade();
        grade.setStudentId(stuId);
        grade.setFirstname(firstName);
        grade.setLastname(lastName);
        grade.setAndroid(androidGrade);
        grade.setJava(javaGrade);
        grade.setHtml(htmlGrade);
        //add to database
        StudentGradeDao gradeDao=new StudentGradeDao(this);
        long n=gradeDao.addStudentGrade(grade);
        //return results
        if(n>0){
            Toast.makeText(this, "Student grades added successfully", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Failed to add student grades", Toast.LENGTH_LONG).show();
        }

    }
    //clear button
    public void CLearScoreAction(){
        studentId.setText("");
        studentIDText.setText("");
        firstname.setText("");
        lastname.setText("");
        androidText.setText("");
        javaText.setText("");
        htmlText.setText("");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add_student_grade, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}