package com.example.demo.student;

import org.junit.jupiter.api.*;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StudentTest {

    public static final int EXPECTED_ID = 1 ;
    public static final String EXPECTED_NAME = "Moath";
    public static final int EXPECTED_AGE = 20;
    public static final double EXPECTED_GPA = 99.9;
    public static final String EXPECTED_MAJOR = "CS";

    private Student student;
    @BeforeEach
    public void setUp() throws Exception {
        student = new Student(1, "Moath", 20, 99.9, "CS");
    }

    @AfterEach
    public void tearDown() throws Exception {
        Logger logger = Logger.getLogger(StudentTest.class.getName());
        logger.info("Test Completed");

    }

    @Test
    @DisplayName("Check if the constructor is run successfully")
    @Tag("Constructor")
    public void testStudentDetails() throws Exception {
        assertEquals(EXPECTED_ID, student.getId());
        assertEquals(EXPECTED_NAME, student.getName());
        assertEquals(EXPECTED_AGE, student.getAge());
        assertEquals(EXPECTED_GPA, student.getGpa());
        assertEquals(EXPECTED_MAJOR, student.getMajor());

    }

}
