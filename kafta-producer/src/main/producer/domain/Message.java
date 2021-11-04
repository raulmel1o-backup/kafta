package main.producer.domain;

public class Message {

    private final String topic;
    private final String body;

    public Message(final String message) {
        this.topic = parseTopic(message);
        this.body = parseBody(message);
    }

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
}
