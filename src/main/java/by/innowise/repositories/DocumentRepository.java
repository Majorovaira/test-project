package by.innowise.repositories;

import by.innowise.domain.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;


@Repository
public interface DocumentRepository extends CrudRepository<Document, Long> {

    Page<Document> findAll(Pageable pageable);

    Page<Document> findByAuthor(String name, Pageable pageable);

    Page<Document> findBySubject(String name, Pageable pageable);

    Page<Document> findByEndDate(LocalDate date, Pageable pageable);

    Page<Document> findByCreateDate(LocalDate date, Pageable pageable);

}
