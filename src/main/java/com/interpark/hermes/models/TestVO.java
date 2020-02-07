package com.interpark.hermes.models;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Getter
@Setter
@XmlRootElement(name = "TestVO")
public class TestVO {
    private String t;
    private String e;
    private String s;
    private String tt;
    private SubClass subClass;
    private SubClass subClass2;
    private List<ChildClass> childList;
}
