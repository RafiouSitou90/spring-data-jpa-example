package com.rafdev.spring.data.jpa.tutorial.repository.student;

import com.rafdev.spring.data.jpa.tutorial.entity.Guardian;
import com.rafdev.spring.data.jpa.tutorial.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentRepositoryTest
{
    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void saveStudent() {
        Student student = Student.builder()
                .emailId("johndoe@example.com")
                .firstName("John")
                .lastName("Doe")
                .build();

        studentRepository.save(student);
    }

    @Test
    public void saveStudentWithGuardian() {
        Guardian guardian = Guardian.builder()
                .name("Guardian Name 1")
                .email("guardian_email_1@example.com")
                .mobile("123456789")
                .build();

        Student student = Student.builder()
                .emailId("johndoe_1@example.com")
                .firstName("John_1")
                .lastName("Doe_1")
                .guardian(guardian)
                .build();

        studentRepository.save(student);
    }

    @Test
    public void getAllStudents() {
        List<Student> students = studentRepository.findAll();

        System.out.println("Students list = " + students);
    }

    @Test
    public void getStudentByFirstName() {
        List<Student> students = studentRepository.findByFirstName("John");

        System.out.println("Students list = " + students);
    }

    @Test
    public void getStudentByFirstNameContaining() {
        List<Student> students = studentRepository.findByFirstNameContaining("John");

        System.out.println("Students list = " + students);
    }

    @Test
    public void getStudentByGuardianName() {
        List<Student> students = studentRepository.findByGuardianName("Guardian Name");

        System.out.println("Students list = " + students);
    }

    @Test
    public void getStudentByFirstNameAndLastName() {
        Student student = studentRepository.findByFirstNameAndLastName("John", "Doe");

        System.out.println("Student = " + student);
    }

    @Test
    public void getStudentByEmailAddress() {
        Student student = studentRepository.findByEmailAddress("johndoe@example.com");

        System.out.println("Student = " + student);
    }

    @Test
    public void getStudentFirstNameByEmailAddress() {
        String studentFirstName = studentRepository.findFirstNameByEmailAddress("johndoe@example.com");

        System.out.println("Student first name = " + studentFirstName);
    }

    @Test
    public void getStudentByEmailAddressNative() {
        Student student = studentRepository.findByEmailAddressNative("johndoe@example.com");

        System.out.println("Student = " + student);
    }

    @Test
    public void getStudentByEmailAddressNamedParam() {
        Student student = studentRepository.findByEmailAddressNativeNamedParam("johndoe@example.com");

        System.out.println("Student = " + student);
    }

    @Test
    public void updateStudentFirstNameByEmailAddress() {
        studentRepository.updateNameByEmailAddress("John_1 Updated", "johndoe_1@example.com");
    }
}