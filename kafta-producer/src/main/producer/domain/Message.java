package main.producer.domain;

//import java.util.HashMap;
//import java.util.Map;

public class Message {

//    private final Map<String, String> headers;
    private final String topic;
    private final String body;

    public Message(final String message) {
        this.topic = parseTopic(message);
        this.body = parseBody(message);
    }

//    public Message(final String message) {
//        this.headers = parseHeaders(message);
//        this.topic = headers.getOrDefault("topic", "default");
//        this.body = parseBody(message);
//    }

//    public Map<String, String> getHeaders() {
//        return headers;
//    }

    public String getTopic() {
        return topic;
    }

    public String getBody() {
        return body;
    }

    private String parseTopic(final String message) {
        return message.split(";")[0];
    }

    private String parseBody(final String message) {
        return message.split(";")[1];
    }

    @Override
    public String toString() {
        return topic + ";" + body;
    }

//    private Map<String, String> parseHeaders(final String message) {
//        final Map<String, String> mapHeaders = new HashMap<>();
//        mapHeaders.put("mode", "producer");
//
//        final String[] headerStr = message.split(";")[0].split(",");
//
//        if (headerStr.length == 1 && headerStr[0].isEmpty()) return mapHeaders;
//
//        for (String param : headerStr) {
//            mapHeaders.put(param.split("=")[0], param.split("=")[1]);
//        }
//
//        return mapHeaders;
//    }
//
//    private String parseBody(String message) {
//        return message.substring(message.indexOf(';') + 1);
//    }
//
//    @Override
//    public String toString() {
//        final StringBuilder sb = new StringBuilder();
//
//        for (String key : headers.keySet()) {
//            sb.append(key).append("=").append(headers.get(key)).append(",");
//        }
//
//        int a = sb.lastIndexOf(",");
//        sb.replace(a, a + 1, ";");
//
//        sb.append(body);
//
//        return sb.toString();
//    }

}
