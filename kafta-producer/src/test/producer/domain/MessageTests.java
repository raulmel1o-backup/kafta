package test.producer.domain;

import main.producer.domain.Message;

public class MessageTests {

    private static final String textMessage = "xesque;hello world!";
//    private static final String textMessageWithoutHeaders = ";hello world!";

    public void Should_Parse_Message() {
        final Message message = new Message(textMessage);

        if (!message.getBody().equals("hello world!")) {
            throw new AssertionError();
        }

//        if (message.getHeaders().size() != 3) {
//            throw new AssertionError();
//        }
//
//        if (!message.getHeaders().get("mode").equals("producer")) {
//            throw new AssertionError();
//        }
//
//        if (!message.getHeaders().get("user").equals("raulmello")) {
//            throw new AssertionError();
//        }

        if (!message.getTopic().equals("xesque")) {
            throw new AssertionError();
        }
    }

    public void Should_Convert_Message_To_String() {
        final Message message = new Message(textMessage);

        final String messageStr = message.toString();

        if (!messageStr.equals(textMessage)) {
            throw new AssertionError();
        }
    }

//    public void Should_Parse_Message_With_Empty_Headers() {
//        final Message message = new Message(textMessageWithoutHeaders);
//
//        if (!message.getBody().equals("hello world!")) {
//            throw new AssertionError();
//        }
//
//        if (message.getHeaders().size() != 1) {
//            throw new AssertionError();
//        }
//
//        if (!message.getTopic().equals("default")) {
//            throw new AssertionError();
//        }
//    }

    public static void main(String[] args) {
        final MessageTests tests = new MessageTests();

        tests.Should_Parse_Message();
        tests.Should_Convert_Message_To_String();
//        tests.Should_Parse_Message_With_Empty_Headers();
    }
}
