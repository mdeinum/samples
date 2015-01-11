package biz.deinum.sample.factorymethod;

/**
 * Created by marten on 18-02-14.
 */
public class Factory {

    public SimpleService createService() {
        return new SimpleService();
    }

    public SimpleRepository createRepository() {
        return new SimpleRepository();
    }

}
