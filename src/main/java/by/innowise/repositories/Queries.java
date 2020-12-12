package by.innowise.repositories;

public class Queries {

    public final static String SELECT_BY_AUTHOR_NAME = "SELECT d FROM Document d JOIN d.author a ON a.fullName=?1";
    public final static String SELECT_BY_AUTHOR_ID = "select d from Document d join d.author a on a.id=?1";

}
