package mock.model;

/**
 * @Author: lx
 * @Date: Created in 2019/7/21 0021
 */
public class Node {
    private int num;
    private String name;

    public Node(int num, String name) {
        this.num = num;
        this.name = name;
    }
//以下忽略所有构造方法和get、set方法

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Node{" +
                "num=" + num +
                ", name='" + name + '\'' +
                '}';
    }
}
