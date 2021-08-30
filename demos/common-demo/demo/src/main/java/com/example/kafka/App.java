package com.example.kafka;

import java.time.Duration;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

/**
 * kafka first demo
 * bin/kafka-topics.sh --bootstrap-server localhost:9092 --create --topic test-topic1
 * 
 * [root@VM-0-2-centos kafka_2.13-2.8.0]# bin/kafka-topics.sh --bootstrap-server localhost:9092 --list
 *  __consumer_offsets
 *  test-topic1
 */
public class App {
    private static final int NUM_OF_MESSAGES = 100;
    private static final Logger logger = LoggerFactory.getLogger(App.class);
    public static void main( String[] args ) throws InterruptedException, ExecutionException {
        logger.info("task begin");
        Producer<String, String> producer = new SimpleProducer().getProducer();
        Consumer<String, String> consumer = new SimpleConsumer().getConsumer();

        ExecutorService executorService = Executors.newFixedThreadPool(4);
        executorService.submit(() -> {
            for (int i = 0; i < NUM_OF_MESSAGES; i++) {
                System.out.println("[Producer]send " + i);
                producer.send(
                    new ProducerRecord<String, String>("test-topic1", Integer.toString(i), Integer.toString(i)),
                    new Callback() {
                        @Override
                        public void onCompletion(RecordMetadata arg0, Exception arg1) {
                            if (arg1 == null) {
                                logger.info("[Producer]send success " + arg0.offset());  
                            } else {
                                logger.error("failed", arg1);
                            }
                        }
                    }
                );
            }
        });

        // consumer是非线程安全的，不能放到线程池里去玩
        // 这里简单的收齐NUM_OF_MESSAGES条消息后就结束
        int count = 0;
        while (count < NUM_OF_MESSAGES) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            count += records.count();
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("[ConsumerProcess]offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
            }
        }
       
        producer.close();
        consumer.close();
        executorService.shutdown();

        logger.info("task end");
    }
}
