package mq.rabbitmq;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author Dong.Chao
 * @Classname RabbitMqUtils
 * @Description
 * @Date 2021/4/30 11:13
 * @Version V1.0
 */
public class RabbitMqUtils {

    private static Connection connection = null;

    public static Connection getConnection(){
        if (connection != null){
            return connection;
        }

        try {
            //定义连接工厂
            ConnectionFactory factory = new ConnectionFactory();
            //设置服务地址
            factory.setHost("192.168.1.10");
            //端口
            factory.setPort(5672);
            //设置账号信息，用户名、密码、vhost
            //factory.setVirtualHost("ems");
            factory.setUsername("admin");
            factory.setPassword("admin");
            // 通过工程获取连接
            connection = factory.newConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }
}
