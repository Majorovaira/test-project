package by.innowise.controllers;

import by.innowise.domain.Document;
import by.innowise.repositories.DocumentRepository;
import by.innowise.services.interfaces.DocumentService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.print.Doc;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class DocumentController {

    private final static String SORT_STRATEGY = "id";
    private final static String DATE_PATTERN = "yyyy-MM-dd";
    private final static String AUTHOR = "author";
    private final static String SUBJECT = "subject";
    private final static String CREATE = "create";
    private final static String END = "end";






    @Autowired
    private DocumentService documentService;

    /**
     * Method apply GET requests and return all documents from repository sorted by id(default)
     * @param pageable
     * @return page with all documents or error message if documents don't exist
     */
    @GetMapping("/documents")
    public List<Document> getDocuments(
                               @PageableDefault(sort = {SORT_STRATEGY}, direction = Sort.Direction.ASC) Pageable pageable) {

        Page<Document> documents = documentService.getAllDocuments(pageable);
        return documents.getContent();
    }


    /**
     * Method apply GET-requests and filters documents by text criteria: author or subject
     * @param filter value equals "author" or "subject"
     * @param criteria author name or subject name
     * @param pageable
     * @return page with all documents or error message if documents don't exist
     */
    @GetMapping("/documents/filter/text")
    public List<Document> getDocumentsByTextCriteria(
                                             @RequestParam String filter,
                                             @RequestParam String criteria,
                                             @PageableDefault(sort = {SORT_STRATEGY}, direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Document> documents = Page.empty();
        if (filter.equals(AUTHOR)) {
            documents = documentService.getDocumentsByAuthor(criteria, pageable);
        } else if (filter.equals(SUBJECT)) {
            documents = documentService.getDocumentsBySubject(criteria, pageable);
        }
        return documents.getContent();


    }

    /**
     * Method apply GET-requests and filters documents by date criteria: date of creating document or finishing document
     * @param filter value equals "create" or "end"
     * @param criteria creating date or finishing date
     * @param pageable
     * @return page with all documents or error message if documents don't exist
     */
    @GetMapping("/documents/filter/date")
    public List<Document> getDocumentsByDateCriteria(
                                             @RequestParam String filter,
                                             @RequestParam @DateTimeFormat(pattern = DATE_PATTERN) Date criteria,
                                             @PageableDefault(sort = {SORT_STRATEGY}, direction = Sort.Direction.ASC) Pageable pageable) {
        LocalDate criteriaLocal = criteria.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Page<Document> documents = Page.empty();
        if (filter.equals(CREATE)) {
            documents = documentService.getDocumentsByCreateDate(criteriaLocal, pageable);
        } else if (filter.equals(END)) {
            documents = documentService.getDocumentsByEndDate(criteriaLocal, pageable);
        }

        return documents.getContent();
    }


}
