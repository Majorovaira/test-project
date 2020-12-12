package by.innowise.domain;


import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String fullName;

}
