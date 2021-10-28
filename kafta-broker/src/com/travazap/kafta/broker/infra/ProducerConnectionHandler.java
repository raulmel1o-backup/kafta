package com.travazap.kafta.broker;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Logger;

public class ProducerConnectionHandler extends Thread {

    private final Logger log;
    private final Socket socket;
    private final InputStream inputStream;
    private final OutputStream outputStream;

    public ProducerConnectionHandler(final Socket socket) throws IOException {
        this.log = Logger.getLogger(ProducerConnectionHandler.class.getName());
        this.socket = socket;
        this.inputStream = socket.getInputStream();
        this.outputStream = socket.getOutputStream();
    }

    @Override
    public void run() {
        Message message;

        do {
            log.info("Connection with producer started");

        } while (!message.getHeaders().get("mode").equals("leave"));
    }
}
