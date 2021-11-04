package main.consumer.domain;

import java.time.LocalDateTime;

public class Message {

    private final Integer id;
    private final String topic;
    private final String body;
    private final LocalDateTime datetime;

    public Message(final String message) {
        this.id = parseId(message);
        this.topic = parseTopic(message);
        this.body = parseBody(message);
        this.datetime = parseDateTime(message);
    }

    public Integer getId() {
        return id;
    }

    public String getTopic() {
        return topic;
    }

    public String getBody() {
        return body;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    private Integer parseId(final String message) {
        return Integer.parseInt(message.split(";")[0]);
    }

    private String parseTopic(final String message) {
        return message.split(";")[1];
    }

    private String parseBody(final String message) {
        return message.split(";")[2];
    }

    private LocalDateTime parseDateTime(final String message) {
        return LocalDateTime.parse(message.split(";")[3]);
    }

    @Override
    public String toString() {
        return "id = " + id + ", topic = " + topic + ", body = " + body + ", datetime = " + datetime;
    }
}
