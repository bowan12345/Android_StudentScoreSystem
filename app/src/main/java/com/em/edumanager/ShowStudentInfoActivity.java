package com.em.edumanager;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.em.edumanager.bean.StudentInfo;
import com.em.edumanager.dao.StudentInfoDao;

import java.util.ArrayList;
import java.util.List;

public class ShowStudentInfoActivity extends Activity {
    //declare fields
    Button butAll,butQuery;
    TextView stuIDEdit;
    ListView studentListView;
    List<StudentInfo> studentInfoList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_student_info);
        this.init();

        //query all button listener
        this.butAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowAllStudentInfo();
            }
        });

        //single query button listener
        this.butQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num=stuIDEdit.getText().toString();
                ShowStudentByID(num);
            }
        });

        //list view item click listener
        studentListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //get student id
                StudentInfo studentInfo=studentInfoList.get(i);
                String studentID=studentInfo.getStudentID();
                //show student info
                ShowStudentDetailsByID(studentID);
            }
        });


    }

    private void ShowStudentDetailsByID(String studentID) {
        StudentInfoDao studentInfoDao=new StudentInfoDao(this);
        List<StudentInfo> studentInfos = studentInfoDao.GetStudentByStudentID(studentID);
        if(studentInfos.isEmpty()){
            Toast.makeText(this, "Student Info Not Found", Toast.LENGTH_SHORT).show();
            return;
        }
        StudentInfo studentInfo=studentInfos.get(0);
        // forward to new page
        Intent intent=new Intent(this,StudentDetailsActivity.class);
        intent.putExtra("studentInfo",studentInfo);
        startActivity(intent);
    }

    /*
     * intialize fields
     */
    private void init(){
        this.butAll=findViewById(R.id.showAllButton);
        this.butQuery=findViewById(R.id.showQuery);
        this.stuIDEdit=findViewById(R.id.studentIDQuery);
        this.studentListView=findViewById(R.id.studentListView);
    }
    /**
     *query all students info method
     *
     */
    private void ShowAllStudentInfo(){
        //query from database
        StudentInfoDao studentDao=new StudentInfoDao(this);
        studentInfoList=studentDao.GetAllStudentInfo();
        //get student info and return ArrayAdapter
        assignStudentInfo();
    }
    /**
     *single query by student id
     */
    public void ShowStudentByID(String studentID){
        //query from database
        StudentInfoDao studentDao=new StudentInfoDao(this);
        studentInfoList=studentDao.GetStudentByStudentID(studentID);
        //get student info and return ArrayAdapter
        assignStudentInfo();
    }

    private void assignStudentInfo() {
        //declare list
        List<String> students=new ArrayList<>();
        for (StudentInfo stu : studentInfoList) {
            //append student info
            //String str = stu.getStudentID() + "\t\t" + stu.getFirstname() + "\t\t" + stu.getLastname() + "\t\t" + stu.getGender() + "\t\t" + stu.getAge();
            String str = "Student ID: " + stu.getStudentID() +"\t\t\t\tFull Name: " + stu.getFirstname() +"\t\t" + stu.getLastname() ;
            students.add(str);
        }
        //create array adapter
        ArrayAdapter adapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1, students);
        // set up height of  ListView
        this.studentListView.setAdapter(adapter);
        setListViewHeightBasedOnItems(studentListView);
    }


    private void setListViewHeightBasedOnItems(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        //  Increase the height of the divider to increase the line spacing
        int dividerHeight = listView.getDividerHeight() + 30; // / Increase the line spacing by 20 pixels
        int desiredHeight = totalHeight + (dividerHeight * (listAdapter.getCount() - 1));
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = Math.max(desiredHeight, 200); //
        listView.setLayoutParams(params);
        listView.requestLayout();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.show_student_info, menu);
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