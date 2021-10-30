package com.travazap.kafta.broker.domain.message;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Message {

    private Integer id;
    private final Map<String, String> headers;
    private final String body;

    public Message(final String message) {
        this.headers = parseHeaders(message);
        this.body = parseBody(message);
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public String getHeadersAsString() {
        return "headers=" + headers.toString();
    }

    public String getBody() {
        return body;
    }

    private Map<String, String> parseHeaders(final String message) {
        final Map<String, String> mapHeaders = new HashMap<>();
//        mapHeaders.put("mode", "producer");
//        mapHeaders.put("datetime", LocalDateTime.now().toString());

        final String[] headerStr = message.split(";")[0].split(",");
        if (headerStr.length == 1 || headerStr[0].isEmpty()) return mapHeaders;

        for (String param : headerStr) {
            mapHeaders.put(param.split("=")[0], param.split("=")[1]);
        }

        return mapHeaders;
    }

    private String parseBody(String message) {
        return message.substring(message.indexOf(';') + 1);
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
