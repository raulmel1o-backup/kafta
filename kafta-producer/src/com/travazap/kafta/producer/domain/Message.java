package com.travazap.kafta.producer.domain;

import java.util.HashMap;
import java.util.Map;

public class Message {

    private final Map<String, String> headers;
    private final String body;

    public Message(final String message) {
        this.headers = parseHeaders(message);
        this.body = parseBody(message);
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }

    private Map<String, String> parseHeaders(final String message) {
        final Map<String, String> mapHeaders = new HashMap<>();

        final String[] headerStr = message.split(";")[0].split(",");

        for (String param : headerStr) {
            mapHeaders.put(param.split("=")[0], param.split("=")[1]);
        }

        return mapHeaders;
    }

    private String parseBody(String message) {
        return message.split(";")[1];
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();

        sb.append("headers=");

        for (String key : headers.keySet()) {
            sb.append(key).append("=").append(headers.get(key)).append(",");
        }

        sb.append(";").append("body=").append(body);

        return sb.toString();
    }

}
