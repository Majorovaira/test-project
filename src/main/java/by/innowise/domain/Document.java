package by.innowise.domain;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;


@Data
@Entity
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String author;

    private String documentName;

    private String description;

    private LocalDate createDate;



//    @ManyToOne
//    private Author author;

    private String subject;

    private LocalDate endDate;


}
