package ml.socshared.service.support.service.impl;

import ml.socshared.service.support.client.TestFeignClient;
import ml.socshared.service.support.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

    private TestFeignClient client;

    @Autowired
    public TestServiceImpl(TestFeignClient feignClient) {
        this.client = feignClient;
    }

    @Override
    public String test() {
        return client.test();
    }
}
