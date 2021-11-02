package main.consumer;

import main.consumer.infra.ConnectionHandler;

import java.io.IOException;

public class ConsumerApplication {

    private static String host = "localhost";
    private static String port = "6667";
    private static String topic = "default";
    private static Long lastMessageIndex = 0L;

    public static void main(String[] args) throws IOException {

        for (int i = 0; i < args.length; i += 2) {
            switch (args[i]) {
                case "-p" -> port = args[i + 1];
                case "-h" -> host = args[i + 1];
                case "-t" -> topic = args[i + 1];
                case "-i" -> lastMessageIndex = Long.parseLong(args[i + 1]);
                default -> throw new IllegalArgumentException("Argument not expected");
            }
        }

//        System.out.printf("Connecting to %s on port %s\n", host, port);

        final ConnectionHandler connectionHandler = new ConnectionHandler(host, port, topic, lastMessageIndex);
        connectionHandler.handleConnection();
    }

}
