package com.chiva.phoneshop.example.qualifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class FormatService {
    @Autowired
    // @Qualifier("fooFormatter")

    /*
        we can skip name in component annotation,
        and we can use qualifier instead with name
     */
    @Qualifier("barFormatterOther")
    private Formatter formatter;
}
