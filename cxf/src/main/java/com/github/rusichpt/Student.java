package com.github.rusichpt;

import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlJavaTypeAdapter(StudentAdapter.class)
public interface Student {
    public String getName();
}
