package mock;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * @Author: lx
 * @Date: Created in 2019/7/21 0021
 */
//@SpringBootTest
//@RunWith(SpringRunner.class)
public class mockBaseTest {
    @Test
    public void test1() {
        Map mockMap = mock(Map.class);
        System.out.println(mockMap);
        assertTrue(mockMap instanceof Map);

        HashMap hashMapMock = mock(HashMap.class);
        assertTrue(hashMapMock instanceof Map);
        assertTrue(hashMapMock instanceof HashMap);

        when(mockMap.size()).thenReturn(100);
        assertEquals(mockMap.size(), 100);

        when(mockMap.put(1, 2)).thenReturn(2);
        assertEquals(mockMap.put(1, 2), 2);

        // 抛出异常
        when(mockMap.get(1)).thenThrow(new NullPointerException());
        //mockMap.get(1);
    }

    @Test
    public void businessTest() {

    }
}
