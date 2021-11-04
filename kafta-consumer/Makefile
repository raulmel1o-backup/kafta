compile-linux:
	echo "Starting kafta-consumer compilation to path: ./out"
	javac -d out/ src/main/consumer/domain/Message.java src/main/consumer/infra/ConnectionHandler.java src/main/consumer/ConsumerApplication.java
	mkdir out/resources
	cp resources/banner.txt out/resources/banner.txt
	cp resources/lmi.txt out/resources/lmi.txt

compile-windows:
	echo "Starting kafta-consumer compilation to path: ./out"
	javac -d out/ src/main/consumer/domain/Message.java src/main/consumer/infra/ConnectionHandler.java src/main/consumer/ConsumerApplication.java
	mkdir out/resources
	copy resources/banner.txt out/resources/banner.txt
	copy resources/lmi.txt out/resources/lmi.txt

run:
	cd out/;java main.consumer.ConsumerApplication
