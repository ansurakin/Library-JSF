package ru.alexander.library.entity;
// Generated 09.02.2018 13:39:59 by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * Author generated by hbm2java
 */
public class Author  implements java.io.Serializable {


     private long id;
     private String fio;
     private Date birthday;

    public Author() {
    }

    public Author(long id, String fio, Date birthday) {
       this.id = id;
       this.fio = fio;
       this.birthday = birthday;
    }
   
    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    public String getFio() {
        return this.fio;
    }
    
    public void setFio(String fio) {
        this.fio = fio;
    }
    public Date getBirthday() {
        return this.birthday;
    }
    
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }




}


