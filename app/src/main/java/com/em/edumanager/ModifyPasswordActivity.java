package com.em.edumanager;

import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Bundle;
import com.em.edumanager.bean.CommonData;
import com.em.edumanager.dao.UserDao;

public class ModifyPasswordActivity extends Activity {
    //declare fields
    EditText oldPassword,newPassword,confirmPassword;
    Button butModify,butClear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_password);
        init();

        //save button listener
        butModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                saveAction();
            }
        });

        //clear button listener
        butClear.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                clearAction();
            }
        });

    }

    private void init(){
        oldPassword=findViewById(R.id.oldPasswordEdit);
        newPassword=findViewById(R.id.newPasswordEdit);
        confirmPassword=findViewById(R.id.confirmPasswordEdit);
        butModify=findViewById(R.id.butModify);
        butClear=findViewById(R.id.butClear);
    }
    /**
     *save method
     */
    public void saveAction(){
        //get user info by inputs
        String old=oldPassword.getText().toString();
        String newPass=newPassword.getText().toString();
        String confirmPass=confirmPassword.getText().toString();
        //if the old password is incorrect
        String loginPassword= CommonData.userLogin.getPassword();
        if(!old.equals(loginPassword)){
            Toast.makeText(this, "The current password is incorrect", Toast.LENGTH_LONG).show();
            return ;
        }
        //if passwords do not match
        if(!newPass.equals(confirmPass)){
            Toast.makeText(this, "The passwords do not match", Toast.LENGTH_LONG).show();
            return ;
        }
        //4.update passwords from database
        UserDao uDao=new UserDao(this);
        int id=CommonData.userLogin.getId();
        long n=uDao.UpdatePassById(id, newPass);
        if(n>0){
            //Password changed successfully
            Toast.makeText(this, "Password changed successfully", Toast.LENGTH_LONG).show();
            CommonData.userLogin.setPassword(newPass);
            finish();
        }else{
            //Password change failed
            Toast.makeText(this, "Password change failed", Toast.LENGTH_LONG).show();
        }
    }
    /**
     *clear method
     */
    public void clearAction(){
        oldPassword.setText("");
        newPassword.setText("");
        confirmPassword.setText("");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.system_setting, menu);
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