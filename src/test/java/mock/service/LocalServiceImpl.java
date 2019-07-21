package mock.service;

import mock.model.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: lx
 * @Date: Created in 2019/7/21 0021
 */
@Service
public class LocalServiceImpl implements ILocalService {
    @Autowired
    private IRemoteService remoteService;

    @Override
    public Node getRemoteNode(int num) {
        return remoteService.getRemoteNode(num);
    }
}
