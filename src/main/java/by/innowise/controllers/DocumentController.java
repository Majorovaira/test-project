package by.innowise.controllers;

import by.innowise.controllers.utils.ControllerUtils;
import by.innowise.domain.Document;
import by.innowise.repositories.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;


@Controller()
public class DocumentController {

    private final static String TEMPLATE_NAME = "document";
    private final static String SORT_STRATEGY = "id";
    private final static String ERROR_MESSAGE_IF_PAGE_EMPTY = "There are not documents in data base for your query";
    private final static String DATE_PATTERN = "yyyy-MM-dd";
    private final static String AUTHOR = "author";
    private final static String SUBJECT = "subject";
    private final static String CREATE = "create";
    private final static String END = "end";






    @Autowired
    private DocumentRepository documentRepository;

    /**
     * Method apply GET requests and return all documents from repository sorted by id(default)
     * @param model
     * @param pageable
     * @return page with all documents or error message if documents don't exist
     */
    @GetMapping("/documents")
    public String getDocuments(Map<String, Object> model,
                               @PageableDefault(sort = {SORT_STRATEGY}, direction = Sort.Direction.ASC) Pageable pageable) {

        Page<Document> documents = documentRepository.findAll(pageable);
        model = ControllerUtils.createModelForView(model, documents, ERROR_MESSAGE_IF_PAGE_EMPTY);

        return TEMPLATE_NAME;
    }


    /**
     * Method apply GET-requests and filters documents by text criteria: author or subject
     * @param model
     * @param filter value equals "author" or "subject"
     * @param criteria author name or subject name
     * @param pageable
     * @return page with all documents or error message if documents don't exist
     */
    @GetMapping("/documents/filter/text")
    public String getDocumentsByTextCriteria(Map<String, Object> model,
                                             @RequestParam String filter,
                                             @RequestParam String criteria,
                                             @PageableDefault(sort = {SORT_STRATEGY}, direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Document> documents = Page.empty();
        if (filter.equals(AUTHOR)) {
            documents = documentRepository.findByAuthor(criteria, pageable);
        } else if (filter.equals(SUBJECT)) {
            documents = documentRepository.findBySubject(criteria, pageable);
        }
        model = ControllerUtils.createModelForView(model, documents, ERROR_MESSAGE_IF_PAGE_EMPTY);
        return TEMPLATE_NAME;


    }

    /**
     * Method apply GET-requests and filters documents by date criteria: date of creating document or finishing document
     * @param model
     * @param filter value equals "create" or "end"
     * @param criteria creating date or finishing date
     * @param pageable
     * @return page with all documents or error message if documents don't exist
     */
    @GetMapping("/documents/filter/date")
    public String getDocumentsByDateCriteria(Map<String, Object> model,
                                             @RequestParam String filter,
                                             @RequestParam @DateTimeFormat(pattern = DATE_PATTERN) Date criteria,
                                             @PageableDefault(sort = {SORT_STRATEGY}, direction = Sort.Direction.ASC) Pageable pageable) {
        LocalDate criteriaLocal = criteria.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Page<Document> documents = Page.empty();
        if (filter.equals(CREATE)) {
            documents = documentRepository.findByCreateDate(criteriaLocal, pageable);
        } else if (filter.equals(END)) {
            documents = documentRepository.findByEndDate(criteriaLocal, pageable);
        }
        model = ControllerUtils.createModelForView(model, documents, ERROR_MESSAGE_IF_PAGE_EMPTY);

        return TEMPLATE_NAME;
    }


}
