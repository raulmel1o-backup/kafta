package main.broker;

import main.broker.infra.ConsumerConnectionStarter;
import main.broker.infra.ProducerConnectionStarter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class BrokerApplication {

//    private static final String BANNER_FILE_PATH = "./kafta-broker/resources/banner.txt"; // run on IDE
    private static final String BANNER_FILE_PATH = "./resources/banner.txt"; // run on terminal

    private static final Scanner sc = new Scanner(System.in);

    private static Integer producerPort = 6666;
    private static Integer consumerPort = 6667;

    public static void main(String[] args) throws IOException {
        printBanner();

        getProducerPortFromTerminalInput();
        getConsumerPortFromTerminalInput();

        ProducerConnectionStarter producerStarter = new ProducerConnectionStarter(producerPort);
        ConsumerConnectionStarter consumerStarter = new ConsumerConnectionStarter(consumerPort);

        producerStarter.start();
        consumerStarter.start();
    }

    private static void getProducerPortFromTerminalInput() {
        System.out.println("Insert producer port (6666): ");
        producerPort = sc.nextInt();
    }

    private static void getConsumerPortFromTerminalInput() {
        System.out.println("Insert consumer port (6667): ");
        consumerPort = sc.nextInt();
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
}
