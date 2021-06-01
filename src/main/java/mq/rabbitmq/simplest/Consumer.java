package mq.rabbitmq.simplest;

import com.rabbitmq.client.*;
import mq.rabbitmq.RabbitMqUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Dong.Chao
 * @Classname Consumer
 * @Description
 * @Date 2021/4/30 11:52
 * @Version V1.0
 */
public class Consumer {

    public static void main(String[] args) throws IOException, TimeoutException {
        //获取MQ链接 消费者
        Connection connection = RabbitMqUtils.getConnection();
        Channel channel = connection.createChannel();
        /* 3.消费者关联队列 */
        channel.queueDeclare("queue.probeInfo", true, false, false, null);
        /* 4.消费者绑定交换机 参数1 队列 参数2交换机 参数3 routingKey */
        channel.queueBind("probe.192.168.1.170",  "probeExchange", "192.168.1.170");
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String msg = new String(body, "UTF-8");
                System.out.println("消费者获取生产者消息:" + msg);
            }
        };
        /* 5.消费者监听队列消息 */
        channel.basicConsume("queue.probeInfo", true, consumer);
      /*  channel.close();
        connection.close();*/
    }
}
