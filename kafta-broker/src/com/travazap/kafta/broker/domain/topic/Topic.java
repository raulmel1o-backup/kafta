package com.travazap.kafta.broker.domain.topic;

public class Topic {

    private Integer id;
    private final String name;

    public Topic(final String name) {
        this.name = name;
    }

    public Topic(final Integer id, final String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
}
