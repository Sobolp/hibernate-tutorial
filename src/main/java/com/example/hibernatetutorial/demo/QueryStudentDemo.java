package com.example.hibernatetutorial.demo;

import com.example.hibernatetutorial.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;


public class QueryStudentDemo {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure()
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

            List<Student> studentList = session.createQuery("from Student").list();

            displayStudents(studentList);

            studentList = session.createQuery("from Student s where s.lastName = 'Doe'").list();

            displayStudents(studentList);

            studentList = session.createQuery("from Student s where s.lastName = 'Doe'"
                    +"or s.firstName = 'Daffy'").list();

            displayStudents(studentList);

            studentList = session.createQuery( "from Student s where s.email like '%.ncom'").list();

            displayStudents(studentList);

            session.getTransaction().commit();

            System.out.println("Done!");
        } finally {
            factory.close();
        }
    }

    private static void displayStudents(List<Student> studentList) {
        studentList.forEach(System.out::println);
    }
}
