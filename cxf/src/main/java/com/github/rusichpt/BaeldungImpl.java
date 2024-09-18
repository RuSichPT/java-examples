package com.github.rusichpt;

import jakarta.jws.WebService;

import java.util.LinkedHashMap;
import java.util.Map;

@WebService(endpointInterface = "com.github.rusichpt.Baeldung")
public class BaeldungImpl implements Baeldung {
    private Map<Integer, Student> students = new LinkedHashMap<Integer, Student>();

    public String hello(String name) {
        return "Hello " + name;
    }

    public String helloStudent(Student student) {
        students.put(students.size() + 1, student);
        return "Hello " + student.getName();
    }

    public Map<Integer, Student> getStudents() {
        return students;
    }
}
