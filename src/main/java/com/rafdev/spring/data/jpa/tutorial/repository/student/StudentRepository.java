package com.rafdev.spring.data.jpa.tutorial.repository.student;

import com.rafdev.spring.data.jpa.tutorial.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>
{
    List<Student> findByFirstName(String firstName);

    List<Student> findByFirstNameContaining(String firstName);

    List<Student> findByGuardianName(String guardianName);

    Student findByFirstNameAndLastName(String firstName, String lastName);

    // JPQL
    @Query("SELECT s FROM Student s WHERE s.emailId = ?1")
    Student findByEmailAddress(String emailId);

    // JPQL
    @Query("SELECT s.firstName FROM Student s WHERE s.emailId = ?1")
    String findFirstNameByEmailAddress(String emailId);

    // Native
    @Query(
            value = "SELECT * FROM tab_students s WHERE s.email_address = ?1",
            nativeQuery = true
    )
    Student findByEmailAddressNative(String emailId);

    // Native Named Param
    @Query(
            value = "SELECT * FROM tab_students s WHERE s.email_address = :emailId",
            nativeQuery = true
    )
    Student findByEmailAddressNativeNamedParam(@Param("emailId") String emailId);

    @Modifying
    @Transactional
    @Query(
            value = "UPDATE tab_students set first_name = ?1 WHERE email_address = ?2",
            nativeQuery = true
    )
    void updateNameByEmailAddress(String firstName, String emailId);
}
