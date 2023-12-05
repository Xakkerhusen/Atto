package org.example.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Setter
@Getter
public class Appl {
    public static ApplicationContext applicationContext = new ClassPathXmlApplicationContext("test.xml");
}
