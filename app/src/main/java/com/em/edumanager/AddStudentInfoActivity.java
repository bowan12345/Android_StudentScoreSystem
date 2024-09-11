package com.em.edumanager;

import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import android.os.Bundle;
import com.em.edumanager.bean.StudentInfo;
import com.em.edumanager.dao.StudentInfoDao;

import java.util.List;

public class AddStudentInfoActivity extends Activity {
    //declare fileds
    EditText stuID,firstname,lastname,age,remarks;
    RadioButton radioMale,radioFemale;
    ArrayAdapter<String> majorAdapter;
    String[] majorNames={"Computer Applications","Computer Networks","Mobile Internet Development","Web Front-End Development"};
    Spinner major;
    Button butAdd,butClear;

    public StudentInfoDao studentInfoDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student_info);
        //call method init()
        this.init();
        //button listener
        this.butAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View V) {
                addAction();
            }
        });
        //button listener
        this.butClear.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                clearAction();
            }
        });
    }
    //init method
    public void init(){
        //student id
        this.stuID=findViewById(R.id.studentIDEdit);
        //student full name
        this.firstname= findViewById(R.id.firstNameEdit);
        this.lastname= findViewById(R.id.lastNameEdit);
        //gender, radiomen-male; radiowomen-female
        this.radioMale=findViewById(R.id.addRadioMale);
        this.radioFemale= findViewById(R.id.addRadioFemale);
        //age
        this.age=findViewById(R.id.addageedit);
        //major
        this.major=findViewById(R.id.spinner1);
        //create adapter
        this.majorAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,this.majorNames);
        //initial dataset of major
        this.major.setAdapter(majorAdapter);
        this.remarks=findViewById(R.id.remarksEdit);
        //add button
        this.butAdd=findViewById(R.id.addbutton);
        //clear button
        this.butClear=findViewById(R.id.resbutton);

    }
    //add button
    public void addAction(){
        //1.got user information by user input
        String stuID=this.stuID.getText().toString();
        if(stuID.isEmpty()){
            Toast.makeText(this, "Please input student ID", Toast.LENGTH_LONG).show();
            return;
        }
        //create dao object
        studentInfoDao=new StudentInfoDao(this);
        List<StudentInfo> studentInfos = studentInfoDao.GetStudentByStudentID(stuID);
        if(!studentInfos.isEmpty()){
            Toast.makeText(this, "Student ID already exists", Toast.LENGTH_LONG).show();
            return;
        }
        String firstname=this.firstname.getText().toString();
        String lastname=this.lastname.getText().toString();
        if(firstname.isEmpty()){
            Toast.makeText(this, "Please input first name", Toast.LENGTH_LONG).show();
            return;
        }
        if(lastname.isEmpty()){
            Toast.makeText(this, "Please input last name", Toast.LENGTH_LONG).show();
            return;
        }
        String gender="Male";
        if(this.radioFemale.isChecked()){
            gender="Female";
        }
        String age=this.age.getText().toString();
        String major=this.major.getSelectedItem().toString();
        String remark=this.remarks.getText().toString();

        //add data into database
        StudentInfo student=new StudentInfo();
        student.setStudentID(stuID);
        student.setFirstname(firstname);
        student.setLastname(lastname);
        student.setGender(gender);
        student.setAge(age);
        student.setMajor(major);
        student.setRemark(remark);
        //2.insert data into database
        long n=studentInfoDao.addStudentInfo(student);
        //3.return result
        String mes="Add Student Information Failed";
        if(n>0){
            mes="Add Student Information successfully";
        }
        Toast.makeText(this, mes, Toast.LENGTH_LONG).show();
    }
    //clear button
    public void clearAction(){
        this.stuID.setText("");
        this.firstname.setText("");
        //set gender default
        this.radioMale.setChecked(true);
        this.age.setText("");
        //set default dropbox
        this.major.setSelection(0);
        this.remarks.setText("");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add_info, menu);
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