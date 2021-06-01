package mq.rabbitmq.simplest;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import mq.rabbitmq.RabbitMqUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Dong.Chao
 * @Classname Simplest
 * @Description
 * @Date 2021/4/30 11:16
 * @Version V1.0
 */
public class Producer {

    public static void main(String[] args) {
        //获取MQ链接 消费者
        Connection connection = RabbitMqUtils.getConnection();
        //获取通道
        try {
            Channel channel = connection.createChannel();
   /*         //参数2: 是否持久化  参数3:是否独占队列 参数4:是否自动删除  参数5:其他属性
            channel.exchangeDeclare("probeExchange", BuiltinExchangeType.DIRECT,false);
            channel.basicPublish("probeExchange", "192.168.1.160", null, "HelloWorld".getBytes());
*/

            channel.exchangeDeclare("probeExchange", BuiltinExchangeType.DIRECT,true);
            /** 4.发送消息 */
            String message = "",sendType="";
            for (int i = 0; i < 10; i++)
            {
                message = "HelloWord";
                channel.basicPublish("probeExchange", "probeList", null, message.getBytes("utf-8"));
                try {
                    Thread.sleep(5 * i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            /** 5.关闭通道、连接 */

            channel.close();
            connection.close();
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }


}
