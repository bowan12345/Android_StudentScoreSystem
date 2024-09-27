package com.em.edumanager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.em.edumanager.bean.StudentInfo;
import com.em.edumanager.dao.StudentGradeDao;
import com.em.edumanager.dao.StudentInfoDao;

public class StudentDetailsActivity extends Activity {
    //declare fields
    TextView studentID,firstname,lastname;
    EditText age,remark;
    RadioButton radioMale,radioFemale;
    ArrayAdapter<String> majorAdapter;
    String[] majorName={"Computer Applications","Computer Networks","Mobile Internet Development","Web Front-End Development"};
    Spinner major;
    Button butSave,butDel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);
        this.init();
        //loading all data
        this.ShowStudentDetails();

        //save button listener
        this.butSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                updateAction();
                Log.d("StudentDetailsActivity", "Save button clicked");
            }
        });

        //delete button listener
        this.butDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // Create a confirmation dialog
                new AlertDialog.Builder(StudentDetailsActivity.this)
                        .setTitle("Confirm Delete")
                        .setMessage("Are you sure you want to delete this student information?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Execute delete action after user confirms
                                deleteAction();
                                Log.d("StudentDetailsActivity", "Delete confirmed");
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Do nothing if user cancels
                                Log.d("StudentDetailsActivity", "Delete canceled");
                                dialog.dismiss();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
                Log.d("StudentDetailsActivity", "Delete button clicked");
            }
        });
        Log.d("StudentDetailsActivity", "Page initialized");
    }

    //initial all textboxes
    private void init(){
        studentID=findViewById(R.id.updateStudentID);
        firstname=findViewById(R.id.firstNameTextView);
        lastname=findViewById(R.id.lastNameTextView);
        age=findViewById(R.id.updateAge);
        remark=findViewById(R.id.updateRemark);
        radioMale=findViewById(R.id.radioMale);
        radioFemale=findViewById(R.id.radioFeMale);
        major=findViewById(R.id.spinner1);
        majorAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,majorName);
        majorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        major.setAdapter(majorAdapter);
        butSave=findViewById(R.id.saveButton);
        butDel=findViewById(R.id.deleteButton);
        Log.d("StudentDetailsActivity", "Fields initialized");
    }

    /**
     * display all student data method
     *
     */
    private void ShowStudentDetails(){
        //get student info
        Intent intent = getIntent();
        StudentInfo studentInfo = (StudentInfo) intent.getSerializableExtra("studentInfo");
        //display
        studentID.setText(studentInfo.getStudentID());
        firstname.setText(studentInfo.getFirstname());
        lastname.setText(studentInfo.getLastname());
        age.setText(studentInfo.getAge());
        remark.setText(studentInfo.getRemark());
        if(studentInfo.getGender().equals("Male")){
            radioMale.setChecked(true);
        }
        else{
            radioFemale.setChecked(true);
        }
        int n=0;
        String majorIndex=studentInfo.getMajor();
        switch (majorIndex) {
            case "Computer Applications":
                break;
            case "Computer Networks":
                n = 1;
                break;
            case "Mobile Internet Development":
                n = 2;
                break;
            case "Web Front-End Development":
                n = 3;
                break;
            default:
                n = 4;
                break;
        }
        major.setSelection(n);
        Log.d("StudentDetailsActivity", "Student details displayed: " + studentInfo.getStudentID());
    }


    /**
     * delete student info
     */
    private void deleteAction(){

        //get student info
        Intent intent = getIntent();
        StudentInfo studentInfo = (StudentInfo) intent.getSerializableExtra("studentInfo");
        String stuID=studentInfo.getStudentID();
        //delete from database
        StudentInfoDao studentInfoDao=new StudentInfoDao(this);
        long n=studentInfoDao.DeleteById(stuID);
        if (n < 1) {
            Toast.makeText(this, "Failed to delete student information", Toast.LENGTH_SHORT).show();
            Log.d("StudentDetailsActivity", "Failed to delete student information: " + stuID);
            return;
        }
        //delete grade from database
        StudentGradeDao gradeDao=new StudentGradeDao(this);
        long deleted=gradeDao.DeleteById(stuID);
        if(deleted>0){
            Toast.makeText(this, "Student information deleted successfully", Toast.LENGTH_LONG).show();
            finish();
        }else {
            Toast.makeText(this, "Failed to delete student grades", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * update student info method
     */
    private void updateAction(){
        //get user infos by inputs
        String stuID=studentID.getText().toString();
        String firName=firstname.getText().toString();
        String lastName=lastname.getText().toString();
        if(firName.isEmpty()){
            Toast.makeText(this, "Please input first name", Toast.LENGTH_LONG).show();
            Log.d("StudentDetailsActivity", "First name is empty");
            return;
        }
        if(lastName.isEmpty()){
            Toast.makeText(this, "Please input last name", Toast.LENGTH_LONG).show();
            Log.d("StudentDetailsActivity", "Last name is empty");
            return;
        }
        String age=this.age.getText().toString();
        String remark=this.remark.getText().toString();
        String gender="Male";
        if(this.radioFemale.isChecked()){
            gender="Female";
        }
        String majorName=major.getSelectedItem().toString();
        //create student info object
        StudentInfo studentInfo=new StudentInfo(stuID,firName,lastName,age,gender,majorName,remark);
        //save to database
        StudentInfoDao studentInfoDao=new StudentInfoDao(this);
        long n=studentInfoDao.UpdateById(studentInfo);
        //return result
        if (n > 0) {
            Toast.makeText(this, "Student information updated successfully", Toast.LENGTH_SHORT).show();
            Log.d("StudentDetailsActivity", "Student information updated: " + stuID);
            finish();
        } else {
            Toast.makeText(this, "Failed to update student information", Toast.LENGTH_SHORT).show();
            Log.d("StudentDetailsActivity", "Failed to update student information: " + stuID);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.student_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_back) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
