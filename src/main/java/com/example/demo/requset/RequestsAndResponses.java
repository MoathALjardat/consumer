package com.example.demo.requset;

import com.example.demo.student.models.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class RequestsAndResponses {

    public static int requestForCount() throws IOException {

        URL url = new URL("http://localhost:9191/count");

        URLConnection connectionWithProducer = url.openConnection();

        BufferedReader responseReader = new BufferedReader(new InputStreamReader(connectionWithProducer.getInputStream()));

        String lineFromJson;

        int numberOfStudents = 0;

        while ((lineFromJson = responseReader.readLine()) != null)
            numberOfStudents = Integer.parseInt(lineFromJson);

        responseReader.close();
        return numberOfStudents;

    }


    public static double requestForGpa(int id) throws IOException {

        URL url = new URL("http://localhost:9191/getGpa/" + id);

        URLConnection connectionWithProducer = url.openConnection();

        BufferedReader responseReader = new BufferedReader(
                new InputStreamReader(
                        connectionWithProducer.getInputStream()));

        String lineFromJson;

        StringBuilder jsonStringBuilder = new StringBuilder("");

        while ((lineFromJson = responseReader.readLine()) != null)
            jsonStringBuilder.append(lineFromJson);





        Gson gson = new Gson();

        Student student = gson.fromJson(jsonStringBuilder.toString(), Student.class);

        responseReader.close();
        return student.getGpa();

    }

    public static List<Student> getStudentsThierMarkesAbove50() throws IOException {

        URL url = new URL("http://localhost:9191/getGpa/above50");
        URLConnection connectionWithProducer = url.openConnection();

        BufferedReader responseReader = new BufferedReader(new InputStreamReader(connectionWithProducer.getInputStream()));

        String lineFromJson;
        StringBuilder jsonStringBuilder = new StringBuilder("");

        while ((lineFromJson = responseReader.readLine()) != null)
            jsonStringBuilder.append(lineFromJson);


        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writerWithDefaultPrettyPrinter();

        List<Student> studentsAbove50 = null;

        try {
            studentsAbove50 = objectMapper.readValue(jsonStringBuilder.toString(), objectMapper.getTypeFactory().constructCollectionType(List.class, Student.class));
        } catch (IOException e) {
            e.printStackTrace();
        }

        responseReader.close();
        return studentsAbove50;

    }

    public static Student requestForMaxGpa() throws IOException {

        URL url = new URL("http://localhost:9191/getGpa/max");
        URLConnection connectionWithProducer = url.openConnection();

        BufferedReader responseReader = new BufferedReader(new InputStreamReader(connectionWithProducer.getInputStream()));

        String lineFromJson;

        StringBuilder jsonStringBuilder = new StringBuilder("");

        while ((lineFromJson = responseReader.readLine()) != null)
            jsonStringBuilder.append(lineFromJson);


        Gson gson = new Gson();

        Student studentHaveMaxGpa = gson.fromJson(jsonStringBuilder.toString(), Student.class);


        responseReader.close();

        return studentHaveMaxGpa;

    }


}
