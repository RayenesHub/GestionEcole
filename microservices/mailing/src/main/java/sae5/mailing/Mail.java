package sae5.mailing;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Mail {
    @Id
    @GeneratedValue
    private int id;
    private String sender;
    private String subject;
    private String body;
    private Date date;

    public Mail() {

    }

    public Mail(int id, Date date, String body, String subject, String sender) {
        this.id = id;
        this.date = date;
        this.body = body;
        this.subject = subject;
        this.sender = sender;
    }

    public int getId() {
        return id;
    }

    public String getSender() {
        return sender;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }

    public Date getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
