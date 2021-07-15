package com.example.demo.request;

import com.example.demo.student.models.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RequestsAndResponsesTest {


    @Test
    @DisplayName("check if requestForCountMethod work successfully")
    public  void requestForCountTest() throws IOException {


        int numberOfStudents ;

        //MockedStatic<Integer> numberOfStudents = Mockito.mockStatic(Integer.class);
        assertNotNull(numberOfStudents);

        when(numberOfStudents.toString()).thenReturn("5");
        assertEquals(numberOfStudents.toString(), "5");

    }

    @Test
    @DisplayName("check if the Gson is convert a json to object successfully and the response have the true value for the gpa for the student mujahed")
    public  void requestForGpaTest() throws IOException {

        URL url = mock(URL.class);

        URLConnection urlConnection = mock(URLConnection.class);

        BufferedReader responseReader = mock(BufferedReader.class);

        StringBuilder jsonStringBuilder = new StringBuilder("{\"id\":2,\"name\":\"mujahed\",\"age\":21,\"gpa\":98.0,\"major\":\"ce\"}");

        Gson gson = mock(Gson.class);

        Student studentFromGson = gson.fromJson(jsonStringBuilder.toString(), Student.class);


        Student studentForTest = mock(Student.class);
        studentForTest.setId(2);
        studentForTest.setGpa(98.0);
        studentForTest.setAge(21);

        assertEquals(studentForTest.getId(), studentFromGson.getId(), "This test should return true");
        assertEquals(studentForTest.getGpa(), studentFromGson.getGpa(), "This test should return true");
        assertEquals(studentForTest.getAge(), studentFromGson.getAge(), "This test should return true");

    }

    @Test
    @DisplayName("check if the Gson is convert a json to objects successfully and the response have the true value of the students have more than 50 in gpa")
    public  void getStudentsThierMarkesAbove50Test() throws IOException {

        URL url = mock(URL.class);
        URLConnection urlConnection = mock(URLConnection.class);
        BufferedReader responseReader = mock(BufferedReader.class);

        StringBuilder jsonStringBuilder = new StringBuilder("[{\"id\":1,\"name\":\"moath\",\"age\":18,\"gpa\":99.0,\"major\":\"cs\"},{\"id\":2,\"name\":\"mujahed\",\"age\":21,\"gpa\":98.0,\"major\":\"ce\"},{\"id\":3,\"name\":\"layth\",\"age\":25,\"gpa\":97.0,\"major\":\"me\"}]");

        ObjectMapper objectMapper = mock(ObjectMapper.class);

        objectMapper.writerWithDefaultPrettyPrinter();

        List<Student> studentsAbove50 = null;

        try {
            studentsAbove50 = objectMapper.readValue(jsonStringBuilder.toString(), objectMapper.getTypeFactory().constructCollectionType(List.class, Student.class));
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertNotNull(studentsAbove50);

    }

    @Test
    @DisplayName("check if the Gson is convert a json to objects successfully and the response have the true value of the student that have the highest gpa")
    public  void requestForMaxGpaTest() throws IOException{

        URL url = mock(URL.class);
        URLConnection urlConnection = mock(URLConnection.class);
        BufferedReader responseReader = mock(BufferedReader.class);

        StringBuilder jsonStringBuilder = new StringBuilder("{\"id\":1,\"name\":\"moath\",\"age\":18,\"gpa\":99.0,\"major\":\"cs\"}");

        Gson gson = mock(Gson.class);

        Student studentHaveMaxGpa = mock(Student.class);
        studentHaveMaxGpa = gson.fromJson(jsonStringBuilder.toString(), Student.class);

        assertNotNull(studentHaveMaxGpa);

    }

}
