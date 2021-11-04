compile-linux:
	echo "Starting kafta-producer compilation to path: ./out"
	javac -d out/ src/main/producer/domain/Message.java src/main/producer/infra/ConnectionHandler.java src/main/producer/ProducerApplication.java
	mkdir out/resources
	cp resources/banner.txt out/resources/banner.txt

compile-windows:
	echo "Starting kafta-producer compilation to path: ./out"
	javac -d out/ src/main/producer/domain/Message.java src/main/producer/infra/ConnectionHandler.java src/main/producer/ProducerApplication.java
	mkdir out/resources
	copy resources/banner.txt out/resources/banner.txt

run:
	cd out/;java main.producer.ProducerApplication
