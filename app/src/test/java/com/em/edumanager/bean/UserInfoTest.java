package com.em.edumanager.bean;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserInfoTest {

    private UserInfo userInfo;

    @Test
    public void testHappyPath() {
        // Initialize UserInfo object
        userInfo = new UserInfo();

        // Test the happy path for UserInfo
        userInfo.setId(1);
        userInfo.setFirstname("John");
        userInfo.setLastname("Doe");
        userInfo.setEmail("john.doe@example.com");
        userInfo.setPassword("password123");

        assertEquals(1, userInfo.getId());
        assertEquals("John", userInfo.getFirstname());
        assertEquals("Doe", userInfo.getLastname());
        assertEquals("john.doe@example.com", userInfo.getEmail());
        assertEquals("password123", userInfo.getPassword());
    }

    @Test
    public void testEmptyFields() {
        // Initialize UserInfo object
        userInfo = new UserInfo();

        // Test with empty fields
        userInfo.setId(0);
        userInfo.setFirstname("");
        userInfo.setLastname("");
        userInfo.setEmail("");
        userInfo.setPassword("");

        assertEquals(0, userInfo.getId());
        assertEquals("", userInfo.getFirstname());
        assertEquals("", userInfo.getLastname());
        assertEquals("", userInfo.getEmail());
        assertEquals("", userInfo.getPassword());
    }

    @Test
    public void testNullFields() {
        // Initialize UserInfo object
        userInfo = new UserInfo();

        // Test with null fields
        userInfo.setId(0);
        userInfo.setFirstname(null);
        userInfo.setLastname(null);
        userInfo.setEmail(null);
        userInfo.setPassword(null);

        assertEquals(0, userInfo.getId());
        assertNull(userInfo.getFirstname());
        assertNull(userInfo.getLastname());
        assertNull(userInfo.getEmail());
        assertNull(userInfo.getPassword());
    }

    @Test
    public void testSpecialCharacters() {
        // Initialize UserInfo object
        userInfo = new UserInfo();

        // Test with special characters in the fields
        userInfo.setId(1);
        userInfo.setFirstname("!@#$%");
        userInfo.setLastname("^&*()");
        userInfo.setEmail("john!@example.com");
        userInfo.setPassword("pass0rd!");

        assertEquals(1, userInfo.getId());
        assertEquals("!@#$%", userInfo.getFirstname());
        assertEquals("^&*()", userInfo.getLastname());
        assertEquals("john!@example.com", userInfo.getEmail());
        assertEquals("pass0rd!", userInfo.getPassword());
    }

}