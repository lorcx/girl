package mongodb;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.List;

/**
 * mongodb 工具
 */
public class MongodbUitl {
    /**
     * mongo客户端
     */
    private static MongoClient mongoClient = null;

    private MongodbUitl() {
    }

    /**
     * 拿到数据库连接
     *
     * @param db 数据库名称
     * @return
     */
    public static MongoDatabase getDatabase(String db) {
        MongoDatabase database = null;
        try {
            MongoCredential credential = MongoCredential.createCredential("test_rw", db, "test_rw".toCharArray());
            ServerAddress serverAddress = new ServerAddress("192.168.1.109", 12345);
            List<ServerAddress> addrs = new ArrayList<>();
            addrs.add(serverAddress);
            List<MongoCredential> credentials = new ArrayList<>();
            credentials.add(credential);
            mongoClient = new MongoClient(addrs, credentials);
            database = mongoClient.getDatabase(db);
            System.out.println("Connection database successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return database;
    }

    /**
     * 关闭数据库连接
     */
    public static void close() {
        mongoClient.close();
    }
}
