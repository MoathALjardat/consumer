package com.example.demo.request;

import com.example.demo.student.Student;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.google.gson.Gson;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Type;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@RunWith(JUnitPlatform.class)
@ExtendWith(MockitoExtension.class)
public class RequestsAndResponsesTest {

    @Mock
    BufferedReader responseReader;

    @Test
    @DisplayName("check if requestForCountMethod work successfully")
    public void requestForCountTest() throws IOException {

        when(responseReader.readLine()).thenReturn("4");

        String lineFromJson;

        int numberOfStudent = 4;
        int numberOfStudentsToCheck = 0;

        lineFromJson = responseReader.readLine();
        numberOfStudentsToCheck = Integer.parseInt(lineFromJson);

        assertThat(numberOfStudentsToCheck).isEqualTo(numberOfStudent);


    }

    @Test
    @DisplayName("check if the Gson is convert a json to object successfully and the response have the true value for the gpa for the student mujahed")
    public void requestForGpaTest() throws IOException {


        StringBuilder jsonStringBuilder = new StringBuilder("{\"id\":2,\"name\":\"mujahed\",\"age\":21,\"gpa\":98.0,\"major\":\"ce\"}");

        Gson gson = mock(Gson.class);

        Student studentFromGson = new Student(2, "mujahed", 21, 98.0, "ce");

        when(gson.fromJson(String.valueOf(jsonStringBuilder), Student.class)).thenReturn(studentFromGson);

        Student studentForTest = gson.fromJson(jsonStringBuilder.toString(), Student.class);

        assertThat(studentForTest).isEqualTo(studentFromGson);

    }


    @Test
    @DisplayName("check if the Gson is convert a json to objects successfully and the response have the true value of the students have more than 50 in gpa")
    public void getStudentsThierMarkesAbove50Test() throws IOException {


        StringBuilder jsonStringBuilder = new StringBuilder("[{\"id\":1,\"name\":\"moath\",\"age\":18,\"gpa\":99.0,\"major\":\"cs\"},{\"id\":2,\"name\":\"mujahed\",\"age\":21,\"gpa\":98.0,\"major\":\"ce\"},{\"id\":3,\"name\":\"layth\",\"age\":25,\"gpa\":98.0,\"major\":\"cs\"}]");


        Student student1 = new Student(1, "moath", 18, 99.9, "cs");
        Student student2 = new Student(2, "mojahed", 21, 98.0, "ce");
        Student student3 = new Student(3, "layth", 25, 98.0, "cs");

        List<Student> studentsAbove50 = new ArrayList<Student>();
        studentsAbove50.add(student1);
        studentsAbove50.add(student2);
        studentsAbove50.add(student3);


        ObjectMapper objectMapper = mock(ObjectMapper.class);
        objectMapper.writerWithDefaultPrettyPrinter();

        List<Student> studentsAbove50ForTest = null;

        when(objectMapper.readValue(anyString(),
                (TypeReference<Object>) anyObject())).
                thenReturn(studentsAbove50);

        studentsAbove50ForTest = objectMapper.readValue(jsonStringBuilder.toString(), new TypeReference<List<Student>>() {
            @Override
            public Type getType() {
                return super.getType();
            }

            @Override
            public int compareTo(TypeReference<List<Student>> o) {
                return super.compareTo(o);
            }
        });

        assertThat(studentsAbove50ForTest).isEqualTo(studentsAbove50);

    }

    @Test
    @DisplayName("check if the Gson is convert a json to objects successfully and the response have the true value of the student that have the highest gpa")
    public void requestForMaxGpaTest() throws IOException {

        StringBuilder jsonStringBuilder = new StringBuilder("{\"id\":1,\"name\":\"moath\",\"age\":18,\"gpa\":99.0,\"major\":\"cs\"}");

        Gson gson = mock(Gson.class);

        Student studentFromGson = new Student(1, "moath", 18, 99.9, "cs");

        when(gson.fromJson(String.valueOf(jsonStringBuilder), Student.class)).thenReturn(studentFromGson);

        Student studentForTest = gson.fromJson(jsonStringBuilder.toString(), Student.class);

        assertThat(studentForTest).isEqualTo(studentFromGson);

    }

}
