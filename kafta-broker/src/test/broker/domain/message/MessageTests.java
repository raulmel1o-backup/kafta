package test.broker.domain.message;

import main.broker.domain.message.Message;

public class MessageTests {

    private static final String textMessage = "xesque;hello world!";

    public void Should_Parse_Message() {
        final Message message = new Message(textMessage);

        if (!message.getBody().equals("hello world!")) {
            throw new AssertionError();
        }

        if (!message.getTopic().equals("xesque")) {
            throw new AssertionError();
        }

        if (message.getDatetime() == null) {
            throw new AssertionError();
        }
    }

    public void Should_Return_Message_As_String() {
        final Message message = new Message(textMessage);

        if (!message.toString().equals(textMessage)) {
            throw new AssertionError();
        }
    }

    public static void main(String[] args) {
        final MessageTests tests = new MessageTests();

        tests.Should_Parse_Message();
        tests.Should_Return_Message_As_String();
    }
}
