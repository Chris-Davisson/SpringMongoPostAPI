package com.temp.chris.models;
 import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "post")
public class Post {

    @Id
    private String _id;
    private String title;
    private String content;
    private Date date;

    // Constructors, getters, and setters


    //I think the id needs to be part of a service
    public Post(String _id, String title, String content, Date date) {
        this._id = _id;
        this.title = title;
        this.content = content;
        this.date = this.getDate();
    }

    public String getId(){
        return this._id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
