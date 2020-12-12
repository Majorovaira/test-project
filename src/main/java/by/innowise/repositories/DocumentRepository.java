package by.innowise.repositories;

import by.innowise.domain.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import static by.innowise.repositories.Queries.*;

import java.time.LocalDate;


@Repository
public interface DocumentRepository extends CrudRepository<Document, Long> {


    Page<Document> findAll(Pageable pageable);

    @Query(SELECT_BY_AUTHOR_NAME)
    Page<Document> findByAuthorName(String name, Pageable pageable);

    @Query(SELECT_BY_AUTHOR_ID)
    Page<Document> findByAuthorId(Long id, Pageable pageable);

    Page<Document> findBySubject(String name, Pageable pageable);

    Page<Document> findByEndDate(LocalDate date, Pageable pageable);

    Page<Document> findByCreateDate(LocalDate date, Pageable pageable);

}
