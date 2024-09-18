package com.github.rusichpt;

import jakarta.jws.WebService;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.util.Map;

@WebService
public interface Baeldung {
    public String hello(String name);

    public String helloStudent(Student student);

    @XmlJavaTypeAdapter(StudentMapAdapter.class)
    public Map<Integer, Student> getStudents();
}
