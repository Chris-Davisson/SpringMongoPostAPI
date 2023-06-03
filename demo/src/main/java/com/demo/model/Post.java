package com.demo.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "posts")
public class Post {

    @Id
    private String id;
    private String title;
    private String content;
    private Date date;

    // Constructors, getters, and setters

    public Post(String title, String content, Date date) {
        this.title = title;
        this.content = content;
        this.date = date;
    }

}
