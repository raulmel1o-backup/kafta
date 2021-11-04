compile-linux:
	echo "Starting kafta compilation to path: ./out"
	javac -d out/kafta-broker kafta-broker/src/main/broker/domain/message/Message.java kafta-broker/src/main/broker/domain/message/MessageRepository.java kafta-broker/src/main/broker/domain/message/MessageService.java kafta-broker/src/main/broker/infra/ConsumerConnectionStarter.java kafta-broker/src/main/broker/infra/ConsumerConnectionHandler.java kafta-broker/src/main/broker/infra/ProducerConnectionStarter.java kafta-broker/src/main/broker/infra/ProducerConnectionHandler.java kafta-broker/src/test/broker/domain/message/MessageTests.java kafta-broker/src/test/broker/domain/message/MessageRepositoryTests.java kafta-broker/src/test/broker/domain/message/MessageServiceTests.java kafta-broker/src/main/broker/BrokerApplication.java
	javac -d out/kafta-consumer kafta-consumer/src/main/consumer/domain/Message.java kafta-consumer/src/main/consumer/infra/ConnectionHandler.java kafta-consumer/src/main/consumer/ConsumerApplication.java
	javac -d out/kafta-producer kafta-producer/src/main/producer/domain/Message.java kafta-producer/src/main/producer/infra/ConnectionHandler.java kafta-producer/src/main/producer/ProducerApplication.java
	mkdir out/kafta-broker/resources
	mkdir out/kafta-broker/lib
	mkdir out/kafta-consumer/resources
	mkdir out/kafta-producer/resources
	cp kafta-broker/resources/banner.txt out/kafta-broker/resources/banner.txt
	cp kafta-broker/lib/sqlite-jdbc-3.36.0.3.jar out/kafta-broker/lib/sqlite-jdbc-3.36.0.3.jar
	cp kafta-consumer/resources/banner.txt out/kafta-consumer/resources/banner.txt
	cp kafta-consumer/resources/lmi.txt out/kafta-consumer/resources/lmi.txt
	cp kafta-producer/resources/banner.txt out/kafta-producer/resources/banner.txt

compile-windows:
	echo "Starting kafta compilation to path: ./out"
	javac -d out/kafta-broker kafta-broker/src/main/broker/domain/message/Message.java kafta-broker/src/main/broker/domain/message/MessageRepository.java kafta-broker/src/main/broker/domain/message/MessageService.java kafta-broker/src/main/broker/infra/ConsumerConnectionStarter.java kafta-broker/src/main/broker/infra/ConsumerConnectionHandler.java kafta-broker/src/main/broker/infra/ProducerConnectionStarter.java kafta-broker/src/main/broker/infra/ProducerConnectionHandler.java kafta-broker/src/test/broker/domain/message/MessageTests.java kafta-broker/src/test/broker/domain/message/MessageRepositoryTests.java kafta-broker/src/test/broker/domain/message/MessageServiceTests.java kafta-broker/src/main/broker/BrokerApplication.java
	javac -d out/kafta-consumer kafta-consumer/src/main/consumer/domain/Message.java kafta-consumer/src/main/consumer/infra/ConnectionHandler.java kafta-consumer/src/main/consumer/ConsumerApplication.java
	javac -d out/kafta-producer kafta-producer/src/main/producer/domain/Message.java kafta-producer/src/main/producer/infra/ConnectionHandler.java kafta-producer/src/main/producer/ProducerApplication.java
	mkdir out/kafta-broker/resources
	mkdir out/kafta-broker/lib
	mkdir out/kafta-consumer/resources
	mkdir out/kafta-producer/resources
	copy kafta-broker/resources/banner.txt out/kafta-broker/resources/banner.txt
	copy kafta-broker/lib/sqlite-jdbc-3.36.0.3.jar out/kafta-broker/lib/sqlite-jdbc-3.36.0.3.jar
	copy kafta-consumer/resources/banner.txt out/kafta-consumer/resources/banner.txt
	copy kafta-consumer/resources/lmi.txt out/kafta-consumer/resources/lmi.txt
	copy kafta-producer/resources/banner.txt out/kafta-producer/resources/banner.txt

run-broker:
	cd out/kafta-broker;java -cp lib/sqlite-jdbc-3.36.0.3.jar:. main.broker.BrokerApplication

run-consumer:
	cd out/kafta-consumer;java main.consumer.ConsumerApplication

run-producer:
	cd out/kafta-producer;java main.producer.ProducerApplication