package by.innowise.domain;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;


@Data
@Entity
@Table(name = "documents")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private long id;

    @ManyToOne(optional = false)
    private Author author;

    @Column(name = "document_name", nullable = false, length = 100)
    private String documentName;

    @Column(name = "description", length = 1000)
    private String description;

    @Column(name="creating_date")
    private LocalDate createDate;

    @Column(name = "subject", nullable = false, length = 100)
    private String subject;

    @Column(name="end_date")
    private LocalDate endDate;


}
