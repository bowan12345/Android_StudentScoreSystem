package com.em.edumanager.bean;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.*;

public class StudentInfoTest {
    private StudentInfo student;

    @Test
    public void testDefaultConstructor() {
        // Test default constructor
        StudentInfo defaultStudent = new StudentInfo();
        assertNull(defaultStudent.getStudentID());
        assertNull(defaultStudent.getFirstname());
        assertNull(defaultStudent.getLastname());
        assertNull(defaultStudent.getAge());
        assertNull(defaultStudent.getGender());
        assertNull(defaultStudent.getMajor());
        assertNull(defaultStudent.getRemark());
    }

    @Test
    public void testParameterizedConstructor() {
        // Initialize StudentInfo object for this test
        student = new StudentInfo("S12345", "John", "Doe", "20", "Male", "Computer Science", "Excellent student");

        assertEquals("S12345", student.getStudentID());
        assertEquals("John", student.getFirstname());
        assertEquals("Doe", student.getLastname());
        assertEquals("20", student.getAge());
        assertEquals("Male", student.getGender());
        assertEquals("Computer Science", student.getMajor());
        assertEquals("Excellent student", student.getRemark());
    }

    @Test
    public void testSetAndGetId() {
        // Initialize StudentInfo object for this test
        student = new StudentInfo("S12345", "John", "Doe", "20", "Male", "Computer Science", "Excellent student");

        // Test setting and getting ID
        student.setId(1);
        assertEquals(1, student.getId());
    }

    @Test
    public void testSetAndGetFirstname() {
        // Initialize StudentInfo object for this test
        student = new StudentInfo("S12345", "John", "Doe", "20", "Male", "Computer Science", "Excellent student");

        // Test setting and getting first name
        student.setFirstname("Jane");
        assertEquals("Jane", student.getFirstname());
    }

    @Test
    public void testSetAndGetLastname() {
        // Initialize StudentInfo object for this test
        student = new StudentInfo("S12345", "John", "Doe", "20", "Male", "Computer Science", "Excellent student");

        // Test setting and getting last name
        student.setLastname("Smith");
        assertEquals("Smith", student.getLastname());
    }

    @Test
    public void testSetAndGetAge() {
        // Initialize StudentInfo object for this test
        student = new StudentInfo("S12345", "John", "Doe", "20", "Male", "Computer Science", "Excellent student");

        // Test setting and getting age
        student.setAge("21");
        assertEquals("21", student.getAge());
    }

    @Test
    public void testSetAndGetGender() {
        // Initialize StudentInfo object for this test
        student = new StudentInfo("S12345", "John", "Doe", "20", "Male", "Computer Science", "Excellent student");

        // Test setting and getting gender
        student.setGender("Female");
        assertEquals("Female", student.getGender());
    }

    @Test
    public void testSetAndGetMajor() {
        // Initialize StudentInfo object for this test
        student = new StudentInfo("S12345", "John", "Doe", "20", "Male", "Computer Science", "Excellent student");

        // Test setting and getting major
        student.setMajor("Mathematics");
        assertEquals("Mathematics", student.getMajor());
    }

    @Test
    public void testSetAndGetRemark() {
        // Initialize StudentInfo object for this test
        student = new StudentInfo("S12345", "John", "Doe", "20", "Male", "Computer Science", "Excellent student");

        // Test setting and getting remark
        student.setRemark("Good performance");
        assertEquals("Good performance", student.getRemark());
    }

    // Edge case tests
    @Test
    public void testSetStudentIDNull() {
        // Initialize StudentInfo object for this test
        student = new StudentInfo("S12345", "John", "Doe", "20", "Male", "Computer Science", "Excellent student");

        // Test setting studentID to null
        student.setStudentID(null);
        assertNull(student.getStudentID());
    }

    @Test
    public void testSetFirstnameNull() {
        // Initialize StudentInfo object for this test
        student = new StudentInfo("S12345", "John", "Doe", "20", "Male", "Computer Science", "Excellent student");

        // Test setting firstname to null
        student.setFirstname(null);
        assertNull(student.getFirstname());
    }

    @Test
    public void testSetLastnameNull() {
        // Initialize StudentInfo object for this test
        student = new StudentInfo("S12345", "John", "Doe", "20", "Male", "Computer Science", "Excellent student");

        // Test setting lastname to null
        student.setLastname(null);
        assertNull(student.getLastname());
    }

    @Test
    public void testSetAgeNull() {
        // Initialize StudentInfo object for this test
        student = new StudentInfo("S12345", "John", "Doe", "20", "Male", "Computer Science", "Excellent student");

        // Test setting age to null
        student.setAge(null);
        assertNull(student.getAge());
    }

    @Test
    public void testSetGenderNull() {
        // Initialize StudentInfo object for this test
        student = new StudentInfo("S12345", "John", "Doe", "20", "Male", "Computer Science", "Excellent student");

        // Test setting gender to null
        student.setGender(null);
        assertNull(student.getGender());
    }

    @Test
    public void testSetMajorNull() {
        // Initialize StudentInfo object for this test
        student = new StudentInfo("S12345", "John", "Doe", "20", "Male", "Computer Science", "Excellent student");

        // Test setting major to null
        student.setMajor(null);
        assertNull(student.getMajor());
    }

    @Test
    public void testSetRemarkNull() {
        // Initialize StudentInfo object for this test
        student = new StudentInfo("S12345", "John", "Doe", "20", "Male", "Computer Science", "Excellent student");

        // Test setting remark to null
        student.setRemark(null);
        assertNull(student.getRemark());
    }

    @Test
    public void testSetRemarkWhenStudentIsNull() {
        // Set student to null
        student = null;
        assertThrows(NullPointerException.class, () -> {
            student.setRemark("Remark");
        });
    }
}