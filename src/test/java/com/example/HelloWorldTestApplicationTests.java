package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

public class HelloWorldTestApplicationTests {

    @Test
    public void contextLoads() {
        System.out.println("Context load check.");
    }

    @Test
    public void testHelloEndpoint() {
        System.out.println("Testing HW done");
    }
}