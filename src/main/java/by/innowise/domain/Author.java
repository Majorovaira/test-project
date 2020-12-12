package by.innowise.domain;


import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private long id;


    @Column(name = "author_name", nullable = false, length = 25, unique = false)
    private String fullName;

}
