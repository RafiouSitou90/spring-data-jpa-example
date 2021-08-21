package com.rafdev.spring.data.jpa.tutorial.repository.teacher;

import com.rafdev.spring.data.jpa.tutorial.entity.Course;
import com.rafdev.spring.data.jpa.tutorial.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeacherRepositoryTest
{
    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    public void saveTeacher() {
        Course courseDBA = Course.builder()
                .title("BDD")
                .credit(60)
                .build();

        Course courseJAVA = Course.builder()
                .title("JAVA")
                .credit(60)
                .build();

        Teacher teacher = Teacher.builder()
                .firstName("Teacher firstName")
                .lastName("Teacher lastName")
//                .courses(List.of(courseDBA, courseJAVA))
                .build();

        teacherRepository.save(teacher);
    }
}