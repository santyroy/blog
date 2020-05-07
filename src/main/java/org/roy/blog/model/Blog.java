package org.roy.blog.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String message;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @ManyToOne
    private BlogUser blogUser;

    public Blog() {
    }

    public Blog(String message, Date date, BlogUser blogUser) {
        this.message = message;
        this.date = date;
        this.blogUser = blogUser;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BlogUser getBlogUser() {
        return blogUser;
    }

    public void setBlogUser(BlogUser blogUser) {
        this.blogUser = blogUser;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", date=" + date +
                ", blogUser=" + blogUser +
                '}';
    }
}
