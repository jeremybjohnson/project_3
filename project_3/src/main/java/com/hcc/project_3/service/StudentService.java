package com.hcc.project_3.service;

import com.hcc.project_3.entity.Student;

import java.util.List;

public interface StudentService {

    // Save Student
    Student saveStudent(Student student);

    // Read Students
    List<Student> getStudents();

    // Update Student
    Student updateStudent(Student student, Long studentId);

    // Delete Student
    void deleteStudent(Long studentId);
}
