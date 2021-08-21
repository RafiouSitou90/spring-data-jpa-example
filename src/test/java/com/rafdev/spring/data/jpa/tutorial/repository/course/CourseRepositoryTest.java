package com.rafdev.spring.data.jpa.tutorial.repository.course;

import com.rafdev.spring.data.jpa.tutorial.entity.Course;
import com.rafdev.spring.data.jpa.tutorial.entity.Guardian;
import com.rafdev.spring.data.jpa.tutorial.entity.Student;
import com.rafdev.spring.data.jpa.tutorial.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseRepositoryTest
{
    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void getAllCourses() {
        List<Course> courses = courseRepository.findAll();

        System.out.println("Courses = " + courses);
    }

    @Test
    public void saveCourseWithTeacher() {
        Teacher teacher = Teacher.builder()
                .firstName("John")
                .lastName("Doe")
                .build();

        Course course = Course.builder()
                .title("C#")
                .credit(100)
                .teacher(teacher)
                .build();

        courseRepository.save(course);
    }

    @Test
    public void getAllCoursePagination() {
        Pageable firstPageWithThreeRecords = PageRequest.of(0, 3);
        Pageable secondPageWithTwoRecords = PageRequest.of(1, 2);

        List<Course> courses = courseRepository.findAll(firstPageWithThreeRecords).getContent();

        long totalElements = courseRepository.findAll(firstPageWithThreeRecords).getTotalElements();
        long totalPages = courseRepository.findAll(firstPageWithThreeRecords).getTotalPages();

        System.out.println("Total Elements = " + totalElements);
        System.out.println("Total Pages = " + totalPages);
        System.out.println("Courses = " + courses);
    }

    @Test
    public void getAllCourseSorting() {
        Pageable sortByTitleASC = PageRequest.of(0, 4, Sort.by("title").ascending());
        Pageable sortByCreditDESC = PageRequest.of(0, 4, Sort.by("credit").descending());
        Pageable sortByTitleAndCreditDESC = PageRequest.of(
                0, 4,
                Sort.by("title").descending().and(Sort.by("credit").descending())
        );

        List<Course> courses = courseRepository.findAll(sortByTitleASC).getContent();

        System.out.println("Courses sort by Title ASC = " + courses);
    }

    @Test
    public void getCourseByTitleContaining() {
        Pageable firstPageTenRecords = PageRequest.of(0, 10);

        List<Course> courses = courseRepository.findByTitleContaining("C", firstPageTenRecords).getContent();

        System.out.println("Courses = " + courses);
    }

    @Test
    public void saveCourseWithStudentAndTeacher() {
        Teacher teacher = Teacher.builder()
                .firstName("John_1")
                .lastName("Doe_1")
                .build();

        Guardian guardian_1 = Guardian.builder()
                .name("Guardian Name 2")
                .email("guardian_email_2@example.com")
                .mobile("123456789")
                .build();

        Student student_1 = Student.builder()
                .emailId("johndoe_2@example.com")
                .firstName("John_2")
                .lastName("Doe_2")
                .guardian(guardian_1)
                .build();

        Student student_2 = Student.builder()
                .emailId("johndoe_3@example.com")
                .firstName("John_3")
                .lastName("Doe_3")
                .build();

        Course course = Course.builder()
                .title("PYTHON")
                .credit(50)
                .teacher(teacher)
                .build();

        course.addStudent(student_1);
        course.addStudent(student_2);

        courseRepository.save(course);
    }
}