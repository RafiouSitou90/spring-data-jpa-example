package com.rafdev.spring.data.jpa.tutorial.repository.teacher;

import com.rafdev.spring.data.jpa.tutorial.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long>
{
}
