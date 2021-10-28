package com.travazap.kafta.broker.infra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Logger;

public class ProducerConnectionHandler extends Thread {

    private final Logger log;
    private final Socket socket;
    private final BufferedReader in;

    public ProducerConnectionHandler(final Socket socket) throws IOException {
        this.log = Logger.getLogger(ProducerConnectionHandler.class.getName());
        this.socket = socket;
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public void run() {
        log.info("Connection with producer started");

        while (true) {
            try {
                final String message = in.readLine();

                if (message != null) {
                    System.out.println(message);
                }

//                message = new Message(new String(inputStream.readAllBytes(), StandardCharsets.UTF_8));
            } catch (IOException e) {
                e.printStackTrace();
            }

//            if (message == null) {
//                // TODO: implement message null handling
//                continue;
//            }
//            if (message.getHeaders().get("mode").equals("leave")) break;
//
//            if (message.getHeaders().get("topic") == null) {
//                // TODO: implement message topic null handling
//                continue;
//            }


        }

//        log.info("Connection with producer finished");
    }
}
