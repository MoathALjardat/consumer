package com.example.demo.consumers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ConsumerTest {

    @Test
    @DisplayName("Check if the constructor is run successfully")
    @Tag("Constructor")
    public void constructorTest() throws InterruptedException {

        Consumer consumer = mock(Consumer.class);
        when(consumer.sendToProducer()).thenReturn(true);
        assertTrue(consumer.sendToProducer());

    }

}