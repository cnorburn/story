package com.aerian.bbc.entities;

import com.aerian.bbc.views.Views;
import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "story")
public class Story {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.class)
    private int id;

    @Column(name = "author_id")
    @JsonView(Views.class)
    private String author_id;

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
