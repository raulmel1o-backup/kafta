package main.broker.domain.message;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

public class Message {

    private Integer id;
    private final Map<String, String> headers;
    private final String topic;
    private final String body;

    public Message(final String message) {
        this.headers = parseHeaders(message);
        this.topic = headers.getOrDefault("topic", "default");
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

    public String getTopic() {
        return topic;
    }

    public String getBody() {
        return body;
    }

    public String getHeadersAsString() {
        return "headers=" + headers.toString();
    }

    private Map<String, String> parseHeaders(final String message) {
        final Map<String, String> mapHeaders = new LinkedHashMap<>();
        mapHeaders.put("datetime", LocalDateTime.now().toString());

        final String[] headerStr = message.split(";")[0].split(",");
        if (headerStr.length == 1 || headerStr[0].isEmpty()) return mapHeaders;

        for (String param : headerStr) {
            final String key = param.split("=")[0];
            final String value = param.split("=")[1];

            mapHeaders.put(key, value);
        }

        return mapHeaders;
    }

    private String parseBody(String message) {
        return message.substring(message.indexOf(';') + 1);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();

        for (String key : headers.keySet()) {
            sb.append(key).append("=").append(headers.get(key)).append(",");
        }

        int a = sb.lastIndexOf(",");
        sb.replace(a, a + 1, ";");

        sb.append(body);

        return sb.toString();
    }
}
