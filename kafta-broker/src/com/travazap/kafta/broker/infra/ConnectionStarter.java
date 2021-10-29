package com.travazap.kafta.broker.infra;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class ConnectionStarter {



    private final Logger log;
    private final Integer port;
    private final ServerSocket serverSocket;

    public ConnectionStarter() throws IOException {
        this.log = Logger.getLogger(ConnectionStarter.class.getName());
        this.port = 6666;
        this.serverSocket = startSocket(port);
    }

    public ConnectionStarter(final Integer port) throws IOException {
        this.log = Logger.getLogger(ConnectionStarter.class.getName());
        this.port = port;
        this.serverSocket = startSocket(this.port);
    }

    public void runSocket() throws IOException {
        while (true) {
            final Socket socket = serverSocket.accept();
            Thread producer = new ProducerConnectionHandler(socket);
            producer.start();
        }
    }

    private ServerSocket startSocket(final Integer port) throws IOException {
        ServerSocket ss;

        try {
            ss = new ServerSocket(port);
        } catch (IOException e) {
            log.severe("Server socket not available");
            throw new IOException(e.getMessage());
        }

        return ss;
    }


}
