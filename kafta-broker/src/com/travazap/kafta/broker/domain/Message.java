package com.travazap.kafta.broker.domain;

import java.util.Map;

public class Message {
    private Long id;
    private Map<String, String> headers; // fixme: find a better solution to headers
    private String body;

    public Message(final Long id, final Map<String, String> headers, final String body) {
        this.id = id;
        this.headers = headers;
        this.body = body;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
