package com.aerian.bbc.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "writer")
public class Writer  {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "school")
    private String school;

    @Column(name = "dob")
    private Date dob;

    @Column(name = "gender")
    private String gender;

    @Column(name = "title")
    private String title;

    @Column(name = "text")
    private String text;

}
