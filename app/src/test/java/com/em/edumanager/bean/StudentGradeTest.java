package com.em.edumanager.bean;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StudentGradeTest {

    // Happy path test case
    @Test
    public void testStudentGradeCreation() {
        StudentGrade student = new StudentGrade("123", "John", "Doe", "A", "B", "C");
        assertEquals("123", student.getStudentId());
        assertEquals("John", student.getFirstname());
        assertEquals("Doe", student.getLastname());
        assertEquals("A", student.getAndroid());
        assertEquals("B", student.getJava());
        assertEquals("C", student.getHtml());
    }

    // Edge case: Test student creation with empty values
    @Test
    public void testStudentGradeCreationWithEmptyValues() {
        StudentGrade student = new StudentGrade("", "", "", "", "", "");
        assertEquals("", student.getStudentId());
        assertEquals("", student.getFirstname());
        assertEquals("", student.getLastname());
        assertEquals("", student.getAndroid());
        assertEquals("", student.getJava());
        assertEquals("", student.getHtml());
    }

    // Edge case: Test student creation with null values
    @Test
    public void testStudentGradeCreationWithNullValues() {
        StudentGrade student = new StudentGrade(null, null, null, null, null, null);
        assertNull(student.getStudentId());
        assertNull(student.getFirstname());
        assertNull(student.getLastname());
        assertNull(student.getAndroid());
        assertNull(student.getJava());
        assertNull(student.getHtml());
    }

    // Edge case: Test setting and getting ID
    @Test
    public void testSetAndGetId() {
        StudentGrade student = new StudentGrade();
        student.setId(1);
        assertEquals(1, student.getId());
    }

    // Edge case: Test updating student information
    @Test
    public void testUpdateStudentInformation() {
        StudentGrade student = new StudentGrade("124", "Jane", "Smith", "A+", "B+", "A-");
        student.setFirstname("Janet");
        student.setLastname("Doe");
        assertEquals("Janet", student.getFirstname());
        assertEquals("Doe", student.getLastname());
    }
}