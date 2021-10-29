package com.travazap.kafta.broker;

import com.travazap.kafta.broker.infra.ConnectionStarter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class BrokerApplication {

    //    private static final String BANNER_FILE_PATH = "./kafta-broker/resources/banner.txt";
    private static final String BANNER_FILE_PATH = "./resources/banner.txt";

    private static ConnectionStarter starter;

    public static void main(String[] args) throws IOException {
        printBanner();

        if (args.length != 0) {
            final Integer port = handleParameters(args);
            starter = new ConnectionStarter(port);
        } else {
            starter = new ConnectionStarter();
        }

        starter.runSocket();
    }

    private static void printBanner() throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new InputStreamReader(new FileInputStream(BANNER_FILE_PATH)))) {
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new FileNotFoundException("Banner file was not found");
        }
    }

    private static Integer handleParameters(final String[] args) {
        for (int i = 0; i < args.length; i += 2) {
            if (args[i].equals("-p")) return Integer.parseInt(args[i + 1]);
        }

        throw new IllegalArgumentException("No port argument found");
    }
}
