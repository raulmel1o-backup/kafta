package com.travazap.kafta.broker;

import com.travazap.kafta.broker.infra.ConnectionStarter;

import java.io.IOException;

public class BrokerApplication {

    private static ConnectionStarter starter;

    public static void main(String[] args) throws IOException {
        if (args.length != 0) {
            final Integer port = handleParameters(args);
            starter = new ConnectionStarter(port);
        } else {
            starter = new ConnectionStarter();
        }

        starter.runSocket();
    }

    private static Integer handleParameters(final String[] args) {
        for (int i = 0; i < args.length; i += 2) {
            if (args[i].equals("-p")) return Integer.parseInt(args[i + 1]);
        }

        throw new IllegalArgumentException("No port argument found");
    }
}
