package com.travazap.kafta.consumer;

import com.travazap.kafta.consumer.infra.ConnectionHandler;

import java.io.IOException;    
public class ConsumerApplication {
    private static String host = "localhost";
    private static String port = "6667"; // TODO: definir porta padrão para consumer

    public static void main(String[] args) throws IOException {

        for (int i = 0; i < args.length; i += 2) {
            switch (args[i]) {
                case "-p" -> port = args[i + 1];
                case "-h" -> host = args[i + 1];
                default -> throw new IllegalArgumentException("Argument not expected");
            }
        }

        System.out.printf("Connecting to %s on port %s\n", host, port);

        final ConnectionHandler connectionHandler = new ConnectionHandler(host, port);
        connectionHandler.handleConnection();
    }

}
