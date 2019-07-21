package mock;

import mock.model.Node;
import mock.service.ILocalService;
import mock.service.IRemoteService;
import mock.service.LocalServiceImpl;
import mock.service.RemoteServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

/**
 * 方式1注解注入
 * @Author: lx
 * @Date: Created in 2019/7/21 0021
 */
@RunWith(MockitoJUnitRunner.class)
public class MockBusinessTest {
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
    @Mock
    private RemoteServiceImpl remoteService;



    @Test
    public void test1() {
        Node target = new Node(2, "this is one");
        when(localService.getRemoteNode(2)).thenReturn(target);
        Node node = localService.getRemoteNode(2);
        Assert.assertEquals(target, node);
    }
}
