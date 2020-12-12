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
import org.springframework.format.annotation.NumberFormat;
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


@RestController()
public class DocumentController {

    private final static String SORT_STRATEGY = "id";
    private final static String DATE_PATTERN = "yyyy-MM-dd";
    private final static String AUTHOR = "author";
    private final static String SUBJECT = "subject";
    private final static String CREATE = "create";
    private final static String END = "end";
    private final static String FILTER_TEXT_PATH = "/filter/text";
    private final static String FILTER_NUMBER_PATH = "/filter/number";
    private final static String FILTER_DATE_PATH = "/filter/date";
    private final static String FILTER = "filter";
    private final static String CRITERIA = "criteria";








    @Autowired
    private DocumentService documentService;

    /**
     * Method apply GET requests and return all documents from repository sorted by id(default)
     * @param pageable
     * @return page with all documents
     */
    @GetMapping
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
     * @return page with filtered documents
     */
    @GetMapping(
            value = (FILTER_TEXT_PATH),
            params = {FILTER, CRITERIA}
    )
    public List<Document> getDocumentsByTextCriteria(
                                             @RequestParam (FILTER) String filter,
                                             @RequestParam (CRITERIA) String criteria,
                                             @PageableDefault(sort = {SORT_STRATEGY}, direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Document> documents = Page.empty();
        if (filter.equals(AUTHOR)) {
            documents = documentService.getDocumentsByAuthorName(criteria, pageable);
        } else if (filter.equals(SUBJECT)) {
            documents = documentService.getDocumentsBySubject(criteria, pageable);
        }
        return documents.getContent();


    }

    /**
     * Method apply GET request and filters documents by number criteria: author id
     * @param filter type of filter (in this version it is only "author")
     * @param id
     * @param pageable
     * @return page with filtered documents
     */
    @GetMapping(
            value = (FILTER_NUMBER_PATH),
            params = {FILTER, CRITERIA})

    public List<Document> getDocumentsByNumberCriteria(
                                            @RequestParam (FILTER)String filter,
                                            @RequestParam (CRITERIA) @NumberFormat long id,
                                            @PageableDefault(sort = {SORT_STRATEGY}, direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Document> documents = Page.empty();
        if (filter.equals(AUTHOR)){
            documents = documentService.getDocumentsByAuthorId(id, pageable);
        }
        return documents.getContent();
    }


    /**
     * Method apply GET-requests and filters documents by date criteria: date of creating document or finishing document
     * @param filter value equals "create" or "end"
     * @param criteria creating date or finishing date
     * @param pageable
     * @return page with filtered documents
     */
    @GetMapping(
            value = (FILTER_DATE_PATH),
            params = {FILTER, CRITERIA}
    )
    public List<Document> getDocumentsByDateCriteria(
                                             @RequestParam (FILTER) String filter,
                                             @RequestParam (CRITERIA) @DateTimeFormat(pattern = DATE_PATTERN) Date criteria,
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
