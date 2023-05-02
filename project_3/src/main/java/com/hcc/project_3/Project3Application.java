package com.hcc.project_3;

import com.hcc.project_3.entity.Course;
import com.hcc.project_3.entity.Student;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.apache.tomcat.util.json.ParseException;
import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Iterator;

@SpringBootApplication
public class Project3Application {

    public static void main(String[] args) throws ParseException, org.json.simple.parser.ParseException {
        ConfigurableApplicationContext context =
                SpringApplication.run(Project3Application.class, args);
        Student student = context.getBean(Student.class);
        Course course = context.getBean(Course.class);


        parseJSOn("https://hccs-advancejava.s3.amazonaws.com/student_course.json");
    }

    public static void parseJSOn(String url) throws ParseException, org.json.simple.parser.ParseException {
        Client client = Client.create();
        WebResource webResource = client.resource(url);

        ClientResponse clientResponse =
                webResource.accept("application/json").get(ClientResponse.class);
        if(clientResponse.getStatus() !=200) {
            throw new RuntimeException("Failed" + clientResponse.toString());
        }

        JSONArray jsonArray =
                (JSONArray) new JSONParser().parse(clientResponse.getEntity(String.class));

        Iterator<Object> it = jsonArray.iterator();

        while(it.hasNext()) {
            JSONObject jsonObject = (JSONObject) it.next();
            JSONArray jsonArr;

            Student student = new Student();
            Long id = Long.parseLong(String.valueOf(jsonObject.get("id")));
            String firstName = String.valueOf(jsonObject.get("first_name"));
            String email = String.valueOf(jsonObject.get("email"));
            String gender = String.valueOf(jsonObject.get("gender"));

            jsonArr = ((JSONArray) jsonObject.get("course"));

            student.setStudentId(id);
            student.setFirstName(firstName);
            student.setEmail(email);
            student.setGender(gender);
            if(jsonArr != null) {
                for (Object cor : jsonArr){
                    Course course = new Course();
                    JSONObject lineItem = (JSONObject) cor;
                    String courseNo = String.valueOf(lineItem.get("courseNo"));
                    String grade = String.valueOf(lineItem.get("grade"));
                    String creditHours = String.valueOf(lineItem.get("creditHours"));
                    course.setStudentId(id);
                    course.setCourseNo(courseNo);
                    course.setGrade(grade);
                    course.setCreditHours(creditHours);
                }
            }
        }
    }
}

