package com.hcc.project_3.service;

import com.hcc.project_3.entity.Course;
import com.hcc.project_3.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CourseServiceImpl implements CourseService{

    @Autowired
    CourseRepository courseRepository;

    @Override
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public List<Course> getCourses() {
        return (List<Course>) courseRepository.findAll();
    }

    @Override
    public Course updateCourse(Course course, Long studentId) {
        return null;
    }

    @Override
    public void deleteCourses(Long courseId) {
        courseRepository.deleteById(courseId);
    }
}
