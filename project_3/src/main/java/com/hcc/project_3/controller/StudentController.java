package com.hcc.project_3.controller;

import com.hcc.project_3.entity.Course;
import com.hcc.project_3.entity.Student;
import com.hcc.project_3.service.CourseService;
import com.hcc.project_3.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RequestMapping("api")
@RestController
public class StudentController {


    @Autowired
    private StudentService studentService;
    @Autowired
    private CourseService courseService;


    @PostMapping("/student")
    public Student saveStudent(@Valid @RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    @PostMapping("/course")
    public Course saveCourse(@Valid @RequestBody Course course) {
        return courseService.saveCourse(course);
    }

    @PutMapping("/student/{id}")
    public Student updateStudent(@RequestBody Student student, @PathVariable("id") Long studentId) {
        return studentService.updateStudent(student, studentId);
    }

    @PutMapping("/course/{id}")
    public Course updateStudent(@RequestBody Course course, @PathVariable("id") Long courseId) {
        return courseService.updateCourse(course, courseId);
    }

    @DeleteMapping("/student/{id}")
    public String deleteStudentById(@PathVariable("id") Long studentId) {
        studentService.deleteStudent(studentId);
        return "Deleted Successfully";
    }

    @DeleteMapping("/course/{id}")
    public String deleteCourseById(@PathVariable("id") Long courseId) {
        courseService.deleteCourses(courseId);
        return "Deleted Successfully";
    }

    @GetMapping("/students")
    public List<Student> getStudentList(){
        return studentService.getStudents();
    }

    @GetMapping("/courses")
    public List<Course> getCourseList() {
        return courseService.getCourses();
    }

    @GetMapping("/course/{name}")
    public Course course(@PathVariable String name){
        List<Course> courses = courseService.getCourses();
        for (Course course : courses) {
            if(course.getCourseNo().equalsIgnoreCase(name)) {
                return course;
            }
        }
        return null;
    }

    @GetMapping("/student/{name}")
    public Student student(@PathVariable String name){
        List<Student> students = studentService.getStudents();
        for (Student student : students){
            if(student.getFirstName().equalsIgnoreCase(name)) {
                return student;
            }
        }
        return null;
    }


}
