package com.travazap.kafta.broker;

import com.travazap.kafta.broker.infra.ConsumerConnectionStarter;
import com.travazap.kafta.broker.infra.ProducerConnectionStarter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class BrokerApplication {

        private static final String BANNER_FILE_PATH = "./kafta-broker/resources/banner.txt";
//    private static final String BANNER_FILE_PATH = "./resources/banner.txt";

    public static void main(String[] args) throws IOException {
        printBanner();

        ProducerConnectionStarter producerStarter;
        ConsumerConnectionStarter consumerStarter;
        if (args.length != 0) {
            final Integer port = handleParameters(args);
            producerStarter = new ProducerConnectionStarter(port);
            consumerStarter = new ConsumerConnectionStarter(port + 1);
        } else {
            producerStarter = new ProducerConnectionStarter();
            consumerStarter = new ConsumerConnectionStarter();
        }

        producerStarter.start();
        consumerStarter.start();
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
