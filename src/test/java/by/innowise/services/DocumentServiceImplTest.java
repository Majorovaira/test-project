package by.innowise.services;

import by.innowise.domain.Author;
import by.innowise.domain.Document;
import by.innowise.repositories.DocumentRepository;
import by.innowise.services.interfaces.DocumentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.*;

@DisplayName("test for services")
@MyApplicationAnnotation
class DocumentServiceImplTest {

    private final String NAME = "Max";
    private final long ID = 1;

    @Autowired
    private DocumentService documentService;

    @MockBean
    private DocumentRepository documentRepository;

    @Captor
    private ArgumentCaptor<Pageable> pageableCaptor;

    private Pageable pageable;

    @MockBean
    private Page<Document> page;

    private List<Document> documents;

    @BeforeEach
    void init() {
        given(documentRepository.findAll(pageable)).willReturn(page);
    }

    @DisplayName("test getAllDocument method should address to repo")
    @Test
    void getAllDocuments() {
        documents = new ArrayList<>();
        documents.add(new Document());
        given(page.getContent()).willReturn(documents);
        Page<Document> document = documentService.getAllDocuments(pageable);
        verify(documentRepository).findAll(pageableCaptor.capture());
        assertEquals(document.getContent(), documents);
        verify(page).getContent();
        assertEquals(pageable, pageableCaptor.getValue());

    }

    @DisplayName("test getDocumentByAuthorName should address to repo and return" +
            "documents with right params")
    @Test
    void getDocumentsByAuthorName() {
        Document doc = new Document();
        doc.setAuthor(new Author(ID, NAME));
        given(page.getContent()).willReturn(List.of(doc));
        given(documentRepository.findByAuthorName(NAME, pageable)).willReturn(page);
        Page<Document> document = documentService.getDocumentsByAuthorName(NAME, pageable);
        verify(documentRepository).findByAuthorName(eq(NAME), pageableCaptor.capture());
        assertEquals(pageableCaptor.getValue(), pageable);
        assertEquals(page.getContent(), document.getContent());
    }

    @DisplayName("test getDocumentByAuthorId should address to repo and return" +
            "documents with right params")
    @Test
    void getDocumentsByAuthorId() {
        Document doc = new Document();
        doc.setAuthor(new Author(ID, NAME));
        given(page.getContent()).willReturn(List.of(doc));
        given(documentRepository.findByAuthorId(ID, pageable)).willReturn(page);
        Page<Document> document = documentService.getDocumentsByAuthorId(ID, pageable);
        verify(documentRepository).findByAuthorId( eq(ID), pageableCaptor.capture());
        assertEquals(pageableCaptor.getValue(), pageable);
        assertEquals(page.getContent(), document.getContent());
    }


    @DisplayName("test getDocumentSubject should address to repo and return" +
            "documents with right params")
    @Test
    void getDocumentsBySubject() {
        Document doc = new Document();
        doc.setSubject(NAME);
        given(page.getContent()).willReturn(List.of(doc));
        given(documentRepository.findBySubject(NAME, pageable)).willReturn(page);
        Page<Document> document = documentService.getDocumentsBySubject(NAME, pageable);
        verify(documentRepository).findBySubject(eq(NAME), pageableCaptor.capture());
        assertEquals(pageableCaptor.getValue(), pageable);
        assertEquals(page.getContent(), document.getContent());
    }

    @DisplayName("test getDocumentByCreateDate should address to repo and return" +
            "documents with right params")
    @Test
    void getDocumentsByCreateDate() {
        Document doc = new Document();
        LocalDate date = LocalDate.now();
        doc.setCreateDate(date);
        given(page.getContent()).willReturn(List.of(doc));
        given(documentRepository.findByCreateDate(date, pageable)).willReturn(page);
        Page<Document> document = documentService.getDocumentsByCreateDate(date, pageable);
        verify(documentRepository).findByCreateDate( eq(date), pageableCaptor.capture());
        assertEquals(pageableCaptor.getValue(), pageable);
        assertEquals(page.getContent(), document.getContent());
    }

    @DisplayName("test getDocumentByEndDate should address to repo and return" +
            "documents with right params")
    @Test
    void getDocumentsByEndDate() {
        Document doc = new Document();
        LocalDate date = LocalDate.now();
        doc.setEndDate(date);
        given(page.getContent()).willReturn(List.of(doc));
        given(documentRepository.findByEndDate(date, pageable)).willReturn(page);
        Page<Document> document = documentService.getDocumentsByEndDate(date, pageable);
        verify(documentRepository).findByEndDate( eq(date), pageableCaptor.capture());
        assertEquals(pageableCaptor.getValue(), pageable);
        assertEquals(page.getContent(), document.getContent());
    }
}

