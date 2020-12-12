package by.innowise.services.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface DocumentService {

    public Page getAllDocuments(Pageable pageable);

    public Page getDocumentsByAuthor(String name, Pageable pageable);

    public Page getDocumentsBySubject(String subject, Pageable pageable);

    public Page getDocumentsByCreateDate(LocalDate date, Pageable pageable);

    public Page getDocumentsByEndDate(LocalDate date, Pageable pageable);
}
