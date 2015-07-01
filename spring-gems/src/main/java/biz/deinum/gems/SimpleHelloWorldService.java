package biz.deinum.gems;

import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author marten
 */
@Service
class SimpleHelloWorldService implements HelloWorldService {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();


    @Override
    public Greeting greet(final String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));

    }
}
