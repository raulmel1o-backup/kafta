compile-linux:
	echo "Starting kafta-broker compilation to path: ./out"
	javac -d out/ src/main/broker/domain/message/Message.java src/main/broker/domain/message/MessageRepository.java src/main/broker/domain/message/MessageService.java src/main/broker/infra/ConsumerConnectionStarter.java src/main/broker/infra/ConsumerConnectionHandler.java src/main/broker/infra/ProducerConnectionStarter.java src/main/broker/infra/ProducerConnectionHandler.java src/test/broker/domain/message/MessageTests.java src/test/broker/domain/message/MessageRepositoryTests.java src/test/broker/domain/message/MessageServiceTests.java src/main/broker/BrokerApplication.java
	mkdir out/resources
	cp resources/banner.txt out/resources/banner.txt

compile-windows:
	echo "Starting kafta-broker compilation to path: ./out"
	javac -d out/ src/main/broker/domain/message/Message.java src/main/broker/domain/message/MessageRepository.java src/main/broker/domain/message/MessageService.java src/main/broker/infra/ConsumerConnectionStarter.java src/main/broker/infra/ConsumerConnectionHandler.java src/main/broker/infra/ProducerConnectionStarter.java src/main/broker/infra/ProducerConnectionHandler.java src/test/broker/domain/message/MessageTests.java src/test/broker/domain/message/MessageRepositoryTests.java src/test/broker/domain/message/MessageServiceTests.java src/main/broker/BrokerApplication.java
	mkdir out/resources
	copy resources/banner.txt out/resources/banner.txt

run:
	cd out/;java main.broker.BrokerApplication
