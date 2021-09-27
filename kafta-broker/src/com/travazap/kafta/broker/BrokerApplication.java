package com.travazap.kafta.broker;

import java.io.IOException;
import java.net.ServerSocket;

public class BrokerApplication {
    public static void main(String[] args) throws IOException {
        System.out.println("broker");

        ServerSocket serverSocket = new ServerSocket(6666);
        serverSocket.accept();
    }
}
