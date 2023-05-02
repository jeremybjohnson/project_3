package com.hcc.project_3.service;

import com.hcc.project_3.entity.Course;

import java.util.List;

public interface CourseService {

    // Save Course
    Course saveCourse(Course course);

    // Read courses
    List<Course> getCourses();

    // Update Course
    Course updateCourse(Course course, Long studentId);

    // Delete course
    void deleteCourses(Long studentId);
}
