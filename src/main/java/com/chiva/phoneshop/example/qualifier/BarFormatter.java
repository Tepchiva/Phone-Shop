package com.chiva.phoneshop.example.qualifier;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("barFormatterOther")
public class BarFormatter implements Formatter {
    @Override
    public String format() {
        return "bar";
    }
}
