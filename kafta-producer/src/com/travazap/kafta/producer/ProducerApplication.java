package com.travazap.kafta.producer;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class ProducerApplication {

    public static void main(String[] args) throws IOException {
        System.out.println("producer");

        Socket socket = new Socket("localhost", 6666);

        OutputStream outputStream = socket.getOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

        dataOutputStream.writeBytes("Hello Trava");
        dataOutputStream.flush();
        dataOutputStream.close();

        socket.close();
    }
}
