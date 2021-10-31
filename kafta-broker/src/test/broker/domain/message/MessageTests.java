package test.broker.domain.message;

import main.broker.domain.message.Message;

public class MessageTests {

    private static final String textMessage = "mode=producer,user=raulmello,topic=xesque;hello world!";
    private static final String textMessageWithoutHeaders = "hello world!";

    public void Should_Parse_Message() {
        final Message message = new Message(textMessage);

        if (!message.getBody().equals("hello world!")) {
            throw new AssertionError();
        }

        if (message.getHeaders().size() != 4) {
            throw new AssertionError();
        }

        if (!message.getHeaders().get("mode").equals("producer")) {
            throw new AssertionError();
        }

        if (!message.getHeaders().get("user").equals("raulmello")) {
            throw new AssertionError();
        }

        if (!message.getTopic().equals("xesque")) {
            throw new AssertionError();
        }

        if (message.getHeaders().get("datetime").isEmpty()) {
            throw new AssertionError();
        }
    }

    public void Should_Parse_Message_With_Empty_Headers() {
        final Message message = new Message(textMessageWithoutHeaders);

        if (!message.getBody().equals("hello world!")) {
            throw new AssertionError();
        }

        if (message.getHeaders().size() != 1) {
            throw new AssertionError();
        }

        if (!message.getTopic().equals("default")) {
            throw new AssertionError();
        }

        if (message.getHeaders().get("datetime").isEmpty()) {
            throw new AssertionError();
        }
    }

    public void Should_Return_Message_As_String() {
        final Message message = new Message(textMessage);

        if (!message.toString().substring(0, 36).contains("datetime")) {
            throw new AssertionError();
        }

        if (!message.toString().substring(36).equals(textMessage)) {
            throw new AssertionError();
        }
    }

    public void Should_Get_Headers_As_String() {
        final Message message = new Message(textMessage);

        final String str = message.getHeadersAsString().substring(0, 18) + message.getHeadersAsString().substring(44);
        if (!str.equals("headers={datetime=, mode=producer, user=raulmello, topic=xesque}")) {
            throw new AssertionError();
        }
    }

    public static void main(String[] args) {
        final MessageTests tests = new MessageTests();

        tests.Should_Parse_Message();
        tests.Should_Parse_Message_With_Empty_Headers();
        tests.Should_Return_Message_As_String();
        tests.Should_Get_Headers_As_String();
    }
}
