package biz.deinum.gems.web;

import biz.deinum.gems.Greeting;
import biz.deinum.gems.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author marten
 */
@RestController
@RequestMapping("/hello-world")
public class HelloWorldController {

    private final HelloWorldService service;

    @Autowired
    public HelloWorldController(HelloWorldService service) {
        this.service = service;
    }

    @RequestMapping(method= RequestMethod.GET)
    public Greeting sayHello(@RequestParam(value="name", required=false, defaultValue="Stranger") String name) {
        return service.greet(name);
    }

    @RequestMapping(value="/plain", method= RequestMethod.GET)
    public Greeting sayHello(HttpServletRequest request) {

//        HttpSession session = request.getSession(false);
//        if (session != null) {
//            String foo = session.getAttribute("foo");
//        }

//        String name = request.getParameter("name");
//        if (name == null) {
//            name = "Stranger";
//        }

        Object foo = WebUtils.getSessionAttribute(request, "foo");

        String name = ServletRequestUtils.getStringParameter(request, "name", "Stranger");
        return service.greet(name);
    }


}
