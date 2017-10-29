package activeMQ.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * active mq
 * 提供者
 */
public class AppProducer {
    // url 默认端口61616
    private static final String url = "failover:(tcp://192.168.1.109:61617,tcp://192.168.1.109:61618)?randomize=true";
    // 队列名称
    private static final String queueName = "queue-test";

    public static void main(String[] args) throws JMSException {
        // 创建连接工厂
        ConnectionFactory conn = new ActiveMQConnectionFactory(url);
        // 创建连接
        Connection connection = conn.createConnection();
        // 启动连接
        connection.start();
        // 创建会话  (第一个参数代表是否在事务中创建)
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 创建目标
        Destination dest = session.createQueue(queueName);
        // 创建一个生产者
        MessageProducer producer = session.createProducer(dest);

        for (int i = 0; i < 100; i++) {
            // 创建消息
            TextMessage textMessage = session.createTextMessage("test" + i);
            producer.send(textMessage);
            System.out.println("发送消息:" + textMessage.getText());
        }
        System.out.println("消息发送成功");

        // 关闭连接
        connection.close();
    }
}
