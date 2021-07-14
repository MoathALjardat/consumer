package com.example.demo;

import com.example.demo.consumers.RunConsumers;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) throws IOException, InterruptedException {
		RunConsumers.runConsumers();
    }
}