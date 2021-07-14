package com.example.demo.consumers;

import com.example.demo.requset.RequestsAndResponses;
import com.example.demo.student.models.Student;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class RunConsumers {

    public static void runConsumers() throws IOException, InterruptedException {
        // first request
        int numberOfRecords = RequestsAndResponses.requestForCount();

        System.out.println(numberOfRecords);

        ArrayList<Consumer> ar = new ArrayList<>();

        for (int i = 0; i < numberOfRecords; i++) {
            ar.add(new Consumer(i + 1));
        }


        // second request
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);

                    Logger logger = Logger.getLogger(Consumer.class.getName());
                    String dateTimeFormatterBefore = DateTimeFormatter.ofPattern("HH:mm:ss:ms").format(LocalDateTime.now());
                    List<Student> students = RequestsAndResponses.getStudentsThierMarkesAbove50();
                    String dateTimeFormatterAfter = DateTimeFormatter.ofPattern("HH:mm:ss:ms").format(LocalDateTime.now());


                    for (Student student : students) {
                        logger.info("Students Above 50 : Student ->" + student.getName() + " the gpa is " + student.getGpa() + "   " + dateTimeFormatterAfter+"\n");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        // second request
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);

                    Logger logger = Logger.getLogger(Consumer.class.getName());
                    String dateTimeFormatterBefore = DateTimeFormatter.ofPattern("HH:mm:ss:ms").format(LocalDateTime.now());

                    Student student = RequestsAndResponses.requestForMaxGpa();
                    String dateTimeFormatterAfter = DateTimeFormatter.ofPattern("HH:mm:ss:ms").format(LocalDateTime.now());


                    logger.info("Students have the max gpa is : Student ->" + student.getName() + " the gpa is " + student.getGpa() + "   " + dateTimeFormatterAfter+"\n");


                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        thread.start();
        thread2.start();

        for (int i = 0; i < numberOfRecords; i++) {
            ar.get(i).sendToProducer();
        }
    }

}
