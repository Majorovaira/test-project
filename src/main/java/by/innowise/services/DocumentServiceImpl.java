package by.innowise.services;

import by.innowise.repositories.DocumentRepository;
import by.innowise.services.interfaces.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    @Override
    public Page getAllDocuments(Pageable pageable) {

        return documentRepository.findAll(pageable);
    }

    @Override
    public Page getDocumentsByAuthorName(String name, Pageable pageable) {
        return documentRepository.findByAuthorName(name, pageable);
    }

    @Override
    public Page getDocumentsByAuthorId(Long id, Pageable pageable) {
        return documentRepository.findByAuthorId(id, pageable);
    }

    @Override
    public Page getDocumentsBySubject(String subject, Pageable pageable) {
        return documentRepository.findBySubject(subject, pageable);
    }

    @Override
    public Page getDocumentsByCreateDate(LocalDate date, Pageable pageable) {
        return documentRepository.findByCreateDate(date, pageable);
    }

    @Override
    public Page getDocumentsByEndDate(LocalDate date, Pageable pageable) {
        return documentRepository.findByEndDate(date, pageable);
    }
}
