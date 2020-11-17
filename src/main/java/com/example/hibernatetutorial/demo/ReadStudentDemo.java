package com.example.hibernatetutorial.demo;

import com.example.hibernatetutorial.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class ReadStudentDemo {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure()
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            System.out.println("Creating new student object...");
            Student tempStudent = new Student("Daffy","Duck","daffy@gmail.com");

            session.beginTransaction();

            System.out.println("Saving the student...");
            System.out.println(tempStudent);

            session.save(tempStudent);

            session.getTransaction().commit();

            System.out.println("Saved student. Generated id: " + tempStudent.getId());

            session = factory.getCurrentSession();

            session.beginTransaction();

            System.out.println("\nGetting student with id: " + tempStudent.getId());
            Student student = session.get(Student.class,tempStudent.getId());

            System.out.println("Get complete: " + student);

            session.getTransaction().commit();

            System.out.println("Done!");
        }finally {
            factory.close();
        }
    }
}
