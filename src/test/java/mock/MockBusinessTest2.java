package mock;

import mock.model.Node;
import mock.service.LocalServiceImpl;
import mock.service.RemoteServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.Whitebox;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

/**
 * @Author: lx
 * @Date: Created in 2019/7/21 0021
 */
@RunWith(MockitoJUnitRunner.class)
public class MockBusinessTest2 {
    /**
     * 方式2手动注入
     */
    private LocalServiceImpl localService;
    private RemoteServiceImpl remoteService;

    @Before
    public void setUp() {
        localService = new LocalServiceImpl();
        RemoteServiceImpl mock = Mockito.mock(RemoteServiceImpl.class);
        Whitebox.setInternalState(localService, "remoteService", mock);
    }

    @Test
    public void test1() {
        Node target = new Node(2, "this is one");
        when(localService.getRemoteNode(2)).thenReturn(target);
        Node node = localService.getRemoteNode(2);
        Assert.assertEquals(target, node);


    }
}
