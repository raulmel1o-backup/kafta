compile-linux:
	echo "Starting kafta-broker compilation to path: ./out"
	javac -d out/ src/main/broker/domain/message/Message.java src/main/broker/domain/message/MessageRepository.java src/main/broker/domain/message/MessageService.java src/main/broker/infra/ConsumerConnectionStarter.java src/main/broker/infra/ConsumerConnectionHandler.java src/main/broker/infra/ProducerConnectionStarter.java src/main/broker/infra/ProducerConnectionHandler.java src/test/broker/domain/message/MessageTests.java src/test/broker/domain/message/MessageRepositoryTests.java src/test/broker/domain/message/MessageServiceTests.java src/main/broker/BrokerApplication.java
	mkdir out/resources
	mkdir out/lib
	cp resources/banner.txt out/resources/banner.txt
	cp lib/sqlite-jdbc-3.36.0.3.jar out/lib/sqlite-jdbc-3.36.0.3.jar

compile-windows:
	echo "Starting kafta-broker compilation to path: ./out"
	javac -d out/ src/main/broker/domain/message/Message.java src/main/broker/domain/message/MessageRepository.java src/main/broker/domain/message/MessageService.java src/main/broker/infra/ConsumerConnectionStarter.java src/main/broker/infra/ConsumerConnectionHandler.java src/main/broker/infra/ProducerConnectionStarter.java src/main/broker/infra/ProducerConnectionHandler.java src/test/broker/domain/message/MessageTests.java src/test/broker/domain/message/MessageRepositoryTests.java src/test/broker/domain/message/MessageServiceTests.java src/main/broker/BrokerApplication.java
	mkdir out/resources
	copy resources/banner.txt out/resources/banner.txt
	copy lib/sqlite-jdbc-3.36.0.3.jar out/lib/sqlite-jdbc-3.36.0.3.jar

run:
	cd out/;java -cp lib/sqlite-jdbc-3.36.0.3.jar:. main.broker.BrokerApplication
