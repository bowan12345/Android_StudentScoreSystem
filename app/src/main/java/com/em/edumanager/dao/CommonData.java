package com.em.edumanager.dao;


import com.em.edumanager.bean.StudentInfo;
import com.em.edumanager.bean.StudentScore;
import com.em.edumanager.bean.UserInfo;

/**
 * Store temporary data
 *
 */
public class CommonData {
   //Information of successfully logged-in users
   public static UserInfo userLogin=null;
   //Information displayed when clicking on the list in student information maintenance
   static public StudentInfo studentInfo=null;
   //Information displayed when clicking on the list in student grades maintenance
   static public StudentScore score=null;
}
