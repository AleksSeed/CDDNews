package org.example.news;

import javax.persistence.*;

@Entity
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String shortDescript;
    private String fullDescript;
    @OneToOne
    private NewsType type;

    public News() {
    }

    public News(String heading, String shortDescription, String fullDescription, NewsType type) {
        this.name = name;
        this.shortDescript = shortDescript;
        this.fullDescript = fullDescript;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortDescript() {
        return shortDescript;
    }

    public void setShortDescript(String shortDescript) {
        this.shortDescript = shortDescript;
    }

    public String getFullDescript() {
        return fullDescript;
    }

    public void setFullDescript(String fullDescript) {
        this.fullDescript = fullDescript;
    }

    public NewsType getType() {
        return type;
    }

    public void setType(NewsType type) {
        this.type = type;
    }




}