package com.aerian.bbc.entities;

import com.aerian.bbc.views.Views;
import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "stories")
public class Story {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.class)
    private int id;

    @Column(name = "name")
    @JsonView(Views.class)
    private String name;

    @Column(name = "school")
    @JsonView(Views.class)
    private String school;

    @Column(name = "dob")
    @JsonView(Views.class)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dob;

    @Column(name = "gender")
    @JsonView(Views.class)
    private String gender;

    @Column(name = "title")
    @JsonView(Views.class)
    private String title;

    @Column(name = "text")
    @JsonView(Views.class)
    private String text;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
