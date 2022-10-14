package com.revature;

import com.revature.service.RequestServiceAPI;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringXmlDriver {
    public static void main(String[] args) {
        System.out.println("Creating the bean container...");
        try (ClassPathXmlApplicationContext beanContainer = new ClassPathXmlApplicationContext("beans.xml")) {
            System.out.println("Bean Container Created!");
            RequestServiceAPI requestServiceAPI = beanContainer.getBean("myRequest", RequestServiceAPI.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
