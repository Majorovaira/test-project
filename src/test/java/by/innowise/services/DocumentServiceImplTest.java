package by.innowise.services;

import by.innowise.repositories.DocumentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DocumentServiceImplTest {

    @Configuration
    @ComponentScan("by.innowise.repositories")
    static class TestConfiguration{
    }

    @MockBean
    private DocumentRepository documentRepository;

    @MockBean
    private Pageable pageable;

    @BeforeEach
    void init() {

    }

    @Test
    void getAllDocuments() {
        BDDMockito.given(documentRepository.findAll(pageable).getContent()).willReturn()
    }

    @Test
    void getDocumentsByAuthorName() {
    }

    @Test
    void getDocumentsByAuthorId() {
    }

    @Test
    void getDocumentsBySubject() {
    }

    @Test
    void getDocumentsByCreateDate() {
    }

    @Test
    void getDocumentsByEndDate() {
    }
}