package com.em.edumanager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

public class GradeDetailsActivity extends Activity {
    //declare fields
    TextView studentID,firstname,lastname;
    EditText androidText,javaText,htmlText;
    Button butSave,butDelete;
    StudentGrade studentGrade;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_details);
        init();
        ShowOldStudentData();
        //save button listener
        butSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                saveAction();
            }
        });
        //delete button listener
        butDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // Create a confirmation dialog
                new AlertDialog.Builder(GradeDetailsActivity.this)
                        .setTitle("Confirm Delete")
                        .setMessage("Are you sure you want to delete this student grade?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Execute delete action after user confirms
                                deleteAction();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Do nothing if user cancels
                                dialog.dismiss();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });
    }

    //initial all textboxes
    private void init(){
        studentID=findViewById(R.id.gradeStudentIDText);
        firstname=findViewById(R.id.gradeFirstnameText);
        lastname=findViewById(R.id.gradeLastnameText);
        androidText=findViewById(R.id.gradeAndroidText);
        javaText=findViewById(R.id.gradeJavaText);
        htmlText=findViewById(R.id.gradeHtmlText);
        butSave = findViewById(R.id.gradeSaveButton);
        butDelete = findViewById(R.id.gradeDeleteButton);
    }


    /**
     *get all infos of students
     *
     */
    private void ShowOldStudentData(){
        //loading student grades info
        Intent intent = getIntent();
        studentGrade = (StudentGrade) intent.getSerializableExtra("studentGrade");
        //display
        studentID.setText(studentGrade.getStudentId());
        firstname.setText(studentGrade.getFirstname());
        lastname.setText(studentGrade.getLastname());
        androidText.setText(studentGrade.getAndroid());
        javaText.setText(studentGrade.getJava());
        htmlText.setText(studentGrade.getHtml());
    }
    /**
     * delete method
     */
    private void deleteAction(){
        String  studentId=studentGrade.getStudentId();
        //delete from database
        StudentGradeDao gradeDao=new StudentGradeDao(this);
        long n=gradeDao.DeleteById(studentId);
        if(n>0){
            Toast.makeText(this, "Student grades deleted successfully", Toast.LENGTH_LONG).show();
            finish();
        }else {
            Toast.makeText(this, "Failed to delete student grades", Toast.LENGTH_LONG).show();
        }

    }
    /**
     * save method
     */
    private void saveAction(){
        //get grades by inputs
        String stuId=studentID.getText().toString();
        String firstName=firstname.getText().toString();
        String lastName=lastname.getText().toString();
        String android=androidText.getText().toString();
        String java=javaText.getText().toString();
        String html=htmlText.getText().toString();
        //update grades
        StudentGrade studentGrade=new StudentGrade(stuId,firstName,lastName,android,java,html);
        StudentGradeDao gradeDao=new StudentGradeDao(this);
        long n=gradeDao.UpdateById(studentGrade);
        //return result
        if(n>0){
            Toast.makeText(this, "Student grades updated successfully", Toast.LENGTH_LONG).show();
            finish();
        }else {
            Toast.makeText(this, "Failed to update student grades", Toast.LENGTH_LONG).show();
        }
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