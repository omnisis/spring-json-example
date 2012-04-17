package com.nextinstruction.springjson.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerExceptionResolver;

@Component
public class AnnotatedExceptionResolver extends AnnotationMethodHandlerExceptionResolver {

    public AnnotatedExceptionResolver() {
        setOrder(HIGHEST_PRECEDENCE);
    }
}
