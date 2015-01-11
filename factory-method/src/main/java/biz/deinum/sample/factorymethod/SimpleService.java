package biz.deinum.sample.factorymethod;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by marten on 18-02-14.
 */
public class SimpleService {

    @Autowired
    private SimpleRepository repository;


    public SimpleRepository getRepository() {
        return this.repository;
    }

}
