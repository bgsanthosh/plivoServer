package com.plivo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;


public class Me {

    @JsonProperty("your_name")
    String name;
    @JsonProperty
    Integer id;
    @JsonProperty
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    Date date;


    Me()    {

    }
    Me(String name, int id) {

        this.id = id;
        this.name = name;
    }

}
