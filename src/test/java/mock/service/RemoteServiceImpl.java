package mock.service;

import mock.model.Node;
import org.springframework.stereotype.Service;

/**
 * @Author: lx
 * @Date: Created in 2019/7/21 0021
 */
@Service
public class RemoteServiceImpl implements IRemoteService {
    @Override
    public Node getRemoteNode(int num) {
        return new Node(99, "remote Node");
    }
}
