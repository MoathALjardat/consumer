package com.example.demo.consumers;

import com.example.demo.requset.RequestsAndResponses;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

@Setter
@Getter
@NoArgsConstructor
public class Consumer {

    private Thread thread;
    private int id;

    public Consumer(int id) {
        this.id = id;
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Logger logger = Logger.getLogger(Consumer.class.getName());
                    String dateTimeFormatterBefore = DateTimeFormatter.ofPattern("HH:mm:ss:ms").format(LocalDateTime.now());
                    double gpa = RequestsAndResponses.requestForGpa(id);
                    String dateTimeFormatterAfter = DateTimeFormatter.ofPattern("HH:mm:ss:ms").format(LocalDateTime.now());

                    logger.info("Thread #" + id + " the gpa is " + gpa + " it start from -->"+dateTimeFormatterBefore+"  its finished at --> " + dateTimeFormatterAfter+"\n");

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    public void sendToProducer() throws InterruptedException {

        Thread.sleep(2000);
        this.thread.start();
    }
}