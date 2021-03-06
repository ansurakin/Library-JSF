package ru.alexander.library.entity;
// Generated Jul 5, 2013 2:59:07 PM by Hibernate Tools 3.2.1.GA

/**
 * Vote generated by hbm2java
 */
public class Vote implements java.io.Serializable {

    private Long id;
    private Book book;
    private Integer value;
    private String username;

    public Vote() {
    }

    public Vote(Book book, String username) {
        this.book = book;
        this.username = username;
    }

    public Vote(Book book, Integer value, String username) {
        this.book = book;
        this.value = value;
        this.username = username;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Book getBook() {
        return this.book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Integer getValue() {
        return this.value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
