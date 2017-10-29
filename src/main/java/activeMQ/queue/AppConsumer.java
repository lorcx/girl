package activeMQ.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 消费者
 */
public class AppConsumer {
    // url
    private static final String url = "failover:(tcp://192.168.1.109:61616,tcp://192.168.1.109:61617,tcp://192.168.1.109:61618)?randomize=true";
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
        // 创建一个消费者
        MessageConsumer consumer = session.createConsumer(dest);
        // 创建一个监听器
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage) message;
                try {
                    System.out.println("接收消息：" + textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        // 关闭连接  消息的接收是一个异步过程，如果把连接关闭了消息就接收不到了
//        connection.close();
    }
}
