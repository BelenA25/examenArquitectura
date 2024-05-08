package com.ucb.producto.servicio;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class IGreetingTest {
    @Test
    @DisplayName("When the user not found should return default message")
    void testGreet() {
        IGreeting greting = new Greeting(null);
        String actual = greting.greet();
        assertEquals("Este es un saludo sobresaliente!", actual);
    }
}
