package by.innowise.controllers.utils;

import by.innowise.domain.Document;
import org.springframework.data.domain.Page;

import java.util.Map;

/**
 * Class provides utility methods for DocumentController
 */
public class ControllerUtils {

    private final static String ERROR = "error";
    private final static String EMPTY = "";
    private final static String SIZE = "size";
    private final static String DOCUMENTS = "documents";

    /**
     * Static method returns size of pages as array from 0
     * @param page
     * @return array of indices of pages
     */
    public static int[] getPagesSizeAsArray(Page<Document> page) {
        int[] pages = new int[page.getTotalPages()];
        for(int i = 0; i < page.getTotalPages(); i ++) {
            pages[i] = i ;
        }
        return pages;
    }

    /**
     * Method creates model, sets error message if page is empty, sets valid page for mapping on view
     * @param model for creating
     * @param page
     * @param errorMessage
     * @return model
     */
    public static Map<String, Object> createModelForView(Map<String, Object> model, Page<Document> page, String errorMessage) {
        if (page.isEmpty()) {
            model.put(ERROR, errorMessage);
        }
        else {
            model.put(SIZE, getPagesSizeAsArray(page));
            model.put(ERROR, EMPTY);
        }
        model.put(DOCUMENTS, page);
        return model;
    }
}
