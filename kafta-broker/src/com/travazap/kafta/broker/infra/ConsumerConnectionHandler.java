package com.travazap.kafta.broker;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Logger;

public class ConsumerConnectionHandler extends Thread {

    private final Logger log;
    private final Socket socket;
    private final InputStream inputStream;
    private final OutputStream outputStream;

    public ConsumerConnectionHandler(final Socket socket) throws IOException {
        this.log = Logger.getLogger(ConsumerConnectionHandler.class.getName());
        this.socket = socket;
        this.inputStream = socket.getInputStream();
        this.outputStream = socket.getOutputStream();
    }

    @Override
    public void run() {
        Message message;

        do {
            log.info();
        } while (!message.getHeaders().get("mode").equals("leave"));
    }
}
