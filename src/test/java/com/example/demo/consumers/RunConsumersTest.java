package com.example.demo.consumers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RunConsumersTest {

    @Test
    @DisplayName("Ensure the consumers are run")
    public  void runConsumersTest() throws InterruptedException {
        // first request
        Integer numberOfRecords = new Integer(5);

        ArrayList<Consumer> arrayListOfConsumers = mock(ArrayList.class);

        when(arrayListOfConsumers.size()).thenReturn(5);

        Consumer consumer = mock(Consumer.class);

        when(arrayListOfConsumers.get(anyInt())).thenReturn(consumer);
        when(consumer.sendToProducer()).thenReturn(true);


        for (int i = 0; i < numberOfRecords; i++) {
            try {
                assertEquals(arrayListOfConsumers.get(i).sendToProducer(), true);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
