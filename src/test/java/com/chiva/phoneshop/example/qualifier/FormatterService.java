package com.chiva.phoneshop.example.qualifier;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
public class FormatterService {

    Formatter formatter;

    @Test
    public void testGetBeanOfFormatter() {
        assertNotNull(formatter);
    }
}