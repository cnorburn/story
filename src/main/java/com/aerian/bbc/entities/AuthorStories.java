package com.aerian.bbc.entities;

import com.aerian.bbc.views.Views;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "author")
@SecondaryTable(name="story")
public class AuthorStories {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.class)
    private int id;

    @Column(name="name")
    @JsonView(Views.class)
    private String name;

    @OneToMany(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonView(Views.class)
    @JoinColumn(name="author_id")
    private List<Story> stories;

}
