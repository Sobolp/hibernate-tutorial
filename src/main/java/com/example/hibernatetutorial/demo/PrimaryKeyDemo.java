package com.example.hibernatetutorial.demo;

import com.example.hibernatetutorial.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class PrimaryKeyDemo {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure()
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            System.out.println("Creating new 3 students object...");
            Student tempStudent1 = new Student("John","Doe","john@gmail.com");
            Student tempStudent2 = new Student("Mary","Public","mary@gmail.com");
            Student tempStudent3 = new Student("Bonita","Applebum","bonita@gmail.com");

            session.beginTransaction();

            System.out.println("Saving the student...");
            session.save(tempStudent1);
            session.save(tempStudent2);
            session.save(tempStudent3);

            session.getTransaction().commit();

            System.out.println("Done!");
        }finally {
            factory.close();
        }
    }
}
