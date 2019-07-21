package mock;

import mock.model.Node;
import mock.service.LocalServiceImpl;
import mock.service.RemoteServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import javax.swing.text.html.HTML;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

/**
 * 测试spy
 * spy是如果没有指定规则，就会调用真实方法
 *
 * @Author: lx
 * @Date: Created in 2019/7/21 0021
 */
@RunWith(MockitoJUnitRunner.class)
public class MockBusinessTest3 {
    /**
     * InjectMocks
     * 要注入实现类
     * 需要注入mock对象的服务
     */
    @InjectMocks
    private LocalServiceImpl localService;
    /**
     * 要注入实现类
     * mock远程服务。这个服务会注入到InjectMocks中
     */
    @Spy
    private RemoteServiceImpl remoteService;

    /**
     * 验证spy对象
     */
    @Test
    public void testSpy() {
        Node target = new Node(2, "this is one");
        when(localService.getRemoteNode(2)).thenReturn(target);
        Node node = localService.getRemoteNode(2);
        Assert.assertEquals(target, node);

        Node node1 = localService.getRemoteNode(3);
        System.out.println(node1);
        Assert.assertEquals(99, node1.getNum());
    }

    /**
     * 设置任意参数
     */
    @Test
    public void testAny() {
        Node target = new Node(98, "any");
        when(localService.getRemoteNode(anyInt())).thenReturn(target);
        Node remoteNode = localService.getRemoteNode(20);
        Assert.assertEquals(98, remoteNode.getNum());
    }

    /**
     * 多次调用指定每次返回值
     */
    @Test
    public void testMultipleReturn() {
        Node node98 = new Node(98, "any98");
        Node node97 = new Node(97, "any97");
        Node node96 = new Node(96, "any96");

        when(localService.getRemoteNode(anyInt())).thenReturn(node98).thenReturn(node97).thenReturn(node96);
        Node nodeRe1 = localService.getRemoteNode(1);
        Node nodeRe2 = localService.getRemoteNode(1);
        Node nodeRe3 = localService.getRemoteNode(1);
        Assert.assertEquals(98, nodeRe1.getNum());
        Assert.assertEquals(97, nodeRe2.getNum());
        Assert.assertEquals(96, nodeRe3.getNum());
    }
}
