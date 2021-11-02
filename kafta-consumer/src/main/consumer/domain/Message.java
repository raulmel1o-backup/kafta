package main.consumer.domain;

import java.util.HashMap;
import java.util.Map;

public class Message {

    private final Integer id;
    private final Map<String, String> headers;
    private final String body;

    public Message(final String message) {
        this.id = parseId(message);
        this.headers = parseHeaders(message);
        this.body = parseBody(message);
    }

    public Integer getId() {
        return id;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }

    private Integer parseId(String message) {
        return Integer.parseInt(message.substring(0, message.indexOf("{")));
    }

    private Map<String, String> parseHeaders(String message) {
        message = message.substring(1);

        final Map<String, String> mapHeaders = new HashMap<>();

        final String[] headerStr = message.split(";")[0].split(",");

        for (String param : headerStr) {
            mapHeaders.put(param.split("=")[0], param.split("=")[1]);
        }

        return mapHeaders;
    }

    private String parseBody(String message) {
        message = message.substring(1);

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
