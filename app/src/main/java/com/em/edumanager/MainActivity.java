package com.em.edumanager;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.em.edumanager.R.*;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
	//declare fields
	GridView gridView;
	Toolbar toolbar ;
	DrawerLayout drawerLayout;
	NavigationView navigationView;
	//declare menus
	String[] title=new String[]{"ADD STUDENT ","MAINTAIN STUDENT","ADD GRADES","MAINTAIN GRADES","SYSTEM","HELP GUIDE","EXIT"};
	//menu images
	int[] image=new int[]{R.drawable.addinfo,R.drawable.showinfo,R.drawable.addscore,
			R.drawable.showscore,R.drawable.userpass,R.drawable.help,R.drawable.exit};
	ArrayList<Map<String,Object>> menuList;
	@SuppressLint("MissingInflatedId")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// create datasets of main page
		this.menuList=new ArrayList<>();
		for(int i=0;i<title.length;i++){
			//made menu options by titles and images
			Map<String,Object> tem=new HashMap<>();
			tem.put("mainImage",image[i]);
			tem.put("mainTitle",title[i]);
			menuList.add(tem);
		}
		//set GridView
		SimpleAdapter adapter=new SimpleAdapter(this, menuList, R.layout.home_grid_view,
				new String[]{"mainImage","mainTitle"}, new int[]{R.id.gridImage,R.id.gridTitle});
		this.gridView=(GridView) findViewById(R.id.gridView1);
		this.gridView.setAdapter(adapter);

		//gridView listener
		this.gridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
									long id) {
				//click menu action
				MainAction(position);
				Log.d("MainActivity", "Menu item clicked: " + title[position]);
			}
		});
		Log.d("MainActivity", "Page initialized");

		//navigation drawer
		toolbar = findViewById(R.id.topAppBar);
		drawerLayout = findViewById(R.id.drawerLayout);
		navigationView = findViewById(R.id.navView);
		setSupportActionBar(toolbar);
		ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_navigation_drawer, R.string.close_navigation_drawer);
		drawerLayout.addDrawerListener(drawerToggle);
		drawerToggle.syncState();
		navigationView.setNavigationItemSelectedListener(this);

	}
	/**
	 *menu action
	 */
	public void MainAction(int id){
		switch(id){
			case 0:
				//add student info
				Intent studentInfoIntent=new Intent(this,AddStudentInfoActivity.class);
				startActivity(studentInfoIntent);
				Log.d("MainActivity", "Add Student Info menu clicked");
				break;
			case 1:
				//maintain student info
				Intent queryIntent=new Intent(this,ShowStudentInfoActivity.class);
				startActivity(queryIntent);
				Log.d("MainActivity", "Maintain Student Info menu clicked");
				break;
			case 2:
				//add student grades
				Intent addGradeIntent=new Intent(this,AddStudentGradeActivity.class);
				startActivity(addGradeIntent);
      			break;
      		case 3:
				//maintain student grades
				Intent maintainGradeIntent=new Intent(this,ShowStudentGradeActivity.class);
				startActivity(maintainGradeIntent);
      			break;
      		case 4:
				//system management
				Intent modifyPasswordIntent=new Intent(this,ModifyPasswordActivity.class);
				startActivity(modifyPasswordIntent);
      			break;
      		case 5:
				//help
				AlertDialog.Builder builder0=new AlertDialog.Builder(this);
				builder0.setTitle("User Guide");
				builder0.setMessage("The software has 7 features: including adding student, " +
						"maintain student, add grades, " +
						"maintain grades, maintain password, help guide, and exiting the software");
				builder0.setPositiveButton("I see", new OnClickListener() {
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						Log.d("MainActivity", "Help dialog acknowledged");
					}
				});
				builder0.create().show();
				Log.d("MainActivity", "Help menu clicked");
				break;
			case 6:
				//exit
				ExitAction();
				Log.d("MainActivity", "Exit menu clicked");
				break;
			default:
				break;
		}
	}

	//exit method
	private void ExitAction() {
		AlertDialog.Builder builder=new AlertDialog.Builder(this);
		builder.setTitle("Tips");
		builder.setMessage("Are you sure you want to exit?");
		builder.setPositiveButton("Yes", new OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				finish();
				Log.d("MainActivity", "Exit confirmed");
			}
		});
		//Cancel button
		builder.setNegativeButton("Cancel", new OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				Log.d("MainActivity", "Exit canceled");
			}
		});
		builder.create().show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_exit) {
			//chose exit action
			AlertDialog.Builder buidler=new AlertDialog.Builder(this);
			buidler.setTitle("Tips");
			buidler.setMessage("Are you sure you want to exit?");
			//yes button
			buidler.setPositiveButton("Yes", new OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					finish();
					Log.d("MainActivity", "Exit confirmed from action bar");
				}
			});
			//cancel button
			buidler.setNegativeButton("Cancel", new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					Log.d("MainActivity", "Exit canceled from action bar");
					return;
				}
			});
			buidler.create().show();
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem menuItem) {
		int id = menuItem.getItemId();
		if (id == R.id.add_info_menu_item) {
			//add student info
			Intent studentInfoIntent=new Intent(this,AddStudentInfoActivity.class);
			startActivity(studentInfoIntent);
		} else if (id == R.id.add_score_menu_item) {
			//add student grades
			Intent addGradeIntent=new Intent(this,AddStudentGradeActivity.class);
			startActivity(addGradeIntent);
		}else if (id == R.id.map_menu_item) {
			//map fragment view
			Intent mapIntent=new Intent(this,MapsActivity.class);
			startActivity(mapIntent);

		} else if (id == R.id.exit_menu_item) {
			ExitAction();
		}else {
			Toast.makeText(this, " See You Soon~~", Toast.LENGTH_SHORT).show();
		}
		drawerLayout.closeDrawer(GravityCompat.START);
		return true;
	}
}

