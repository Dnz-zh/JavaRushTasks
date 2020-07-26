package com.javarush.task.task33.task3309;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "someObject")
@XmlRootElement
public class SomeObject {
    public String name;
    public int x;
    public SomeObject() {}
}