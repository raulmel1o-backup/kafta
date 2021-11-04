package main.broker.domain.message;

import java.time.LocalDateTime;

public class Message {

    private Integer id;
    private final String topic;
    private final String body;
    private final LocalDateTime datetime;

    public Message(final Integer id, final String topic, final String body, final String datetime) {
        this.id = id;
        this.topic = topic;
        this.body = body;
        this.datetime = LocalDateTime.parse(datetime);
    }

    public Message(final String message) {
        this.topic = parseTopic(message);
        this.body = parseBody(message);
        this.datetime = LocalDateTime.now();
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

    private String parseTopic(final String message) {
        return message.split(";")[0];
    }

    private String parseBody(final String message) {
        return message.split(";")[1];
    }

    @Override
    public String toString() {
        if (id == null) {
            return topic + ";" + body;
        } else if (datetime == null) {
            return id + ";" + topic + ";" + body;
        }

        return id + ";" + topic + ";" + body + ";" + datetime;
    }
}
