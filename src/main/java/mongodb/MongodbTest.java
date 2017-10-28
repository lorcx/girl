package mongodb;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * mongodb连接
 */
public class MongodbTest {
    public static void main(String[] args) {
        MongodbTest mt = new MongodbTest();
        // 获取数据库连接
        MongoDatabase db = MongodbUitl.getDatabase("test");

//        mt.batchSave(db);

//        mt.findAll(db);

//        mt.findCondition(db);

//        mt.findPattern(db);

//        mt.findPage(db);

//        mt.update(db);

//        mt.delete(db);

        mt.statistis(db);
        MongodbUitl.close();
    }

    /**
     * 批量保存
     */
    public void batchSave(MongoDatabase db) {
        MongoCollection emps = db.getCollection("emps");
        System.out.println(emps.count());
        List<Document> all = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Document doc = new Document();
            doc.append("sid", i);
            doc.append("name", "姓名 - " + i);
            doc.append("sex ", "男");
            all.add(doc);
        }
        emps.insertMany(all);
    }

    /**
     * 查询所有
     *
     * @param db
     */
    public void findAll(MongoDatabase db) {
        MongoCollection emps = db.getCollection("emps");
        BasicDBObject cond = new BasicDBObject();
        MongoCursor<Document> cursor = emps.find().iterator();
        while (cursor.hasNext()) {
            System.out.println(cursor.next());
        }
    }

    /**
     * 根据条件查询
     *
     * @param db
     */
    public void findCondition(MongoDatabase db) {
        MongoCollection emps = db.getCollection("emps");
        BasicDBObject cond = new BasicDBObject();
        cond.put("sid", new BasicDBObject("$gt", 5).append("$lt", 10));
        MongoCursor<Document> cursor = emps.find(cond).iterator();
        while (cursor.hasNext()) {
            System.out.println(cursor.next());
        }
    }

    /**
     * 正则查询
     *
     * @param db
     */
    public void findPattern(MongoDatabase db) {
        MongoCollection emps = db.getCollection("emps");
        Pattern p = Pattern.compile("10");
        BasicDBObject cond = new BasicDBObject();
        cond.put("name", new BasicDBObject("$regex", p).append("$options", "i"));
        MongoCursor<Document> cursor = emps.find(cond).iterator();
        while (cursor.hasNext()) {
            System.out.println(cursor.next());
        }
    }

    /**
     * 分页显示
     *
     * @param db
     */
    public void findPage(MongoDatabase db) {
        MongoCollection emps = db.getCollection("emps");
        MongoCursor<Document> cursor = emps.find().skip(5).limit(5).iterator();
        while (cursor.hasNext()) {
            System.out.println(cursor.next());
        }
    }

    /**
     * 更新单个字段
     *
     * @param db
     */
    public void update(MongoDatabase db) {
        MongoCollection emps = db.getCollection("emps");
        BasicDBObject conA = new BasicDBObject("sid", 0);
        BasicDBObject conB = new BasicDBObject("$set", new BasicDBObject("name", "姓名 - wwwwwdddbbbbbcccccwww"));
        UpdateResult result = emps.updateMany(conA, conB);
        System.out.println(result);
    }

    /**
     * 删除
     */
    public void delete(MongoDatabase db) {
        MongoCollection emps = db.getCollection("emps");
        BasicDBObject cond = new BasicDBObject("sid", 0);
        DeleteResult result = emps.deleteMany(cond);
        System.out.println(result.getDeletedCount());
    }

    /**
     * 统计查询
     *
     * @param db
     */
    public void statistis(MongoDatabase db) {
        MongoCollection emps = db.getCollection("test");
        BasicDBObject cond = new BasicDBObject("$group",
                        new BasicDBObject("_id", "$job")
                        );
        List<BasicDBObject> all = new ArrayList<>();
        all.add(cond);
        MongoCursor<Document> cursor = emps.aggregate(all).iterator();
        while (cursor.hasNext()) {
            Document doc = cursor.next();
            System.out.println(doc.getString("_id") + ", " + doc.getDouble("max_sal") + ", " + doc.getDouble("min_sal"));

        }
    }
}
