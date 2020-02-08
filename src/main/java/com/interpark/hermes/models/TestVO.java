package com.interpark.hermes.models;

import com.fasterxml.jackson.annotation.*;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Getter
@Setter
@XmlRootElement(name = "TestVO")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class TestVO {
    @JsonProperty("ab")
    @SerializedName("ab")
    @XmlElement(name="ab")
    private String r;
    private String e;
    private String s;
    private String t;
    private List<Child> child;
}
