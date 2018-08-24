package biz.deinum.orders

import java.util.concurrent.{ThreadLocalRandom, TimeUnit}

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration.DurationInt

class OrdersSimulation extends Simulation {

  val httpConf = http
    .baseURL("http://localhost:8080/orders")
    .acceptHeader("application/json")
    .contentTypeHeader("application/json")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader("Mozilla/5.0 (Windows NT 5.1; rv:31.0) Gecko/20100101 Firefox/31.0")

  val retrieveOrders = scenario("Get Orders").during(3.minutes) {
    exec(
      http("Get All Orders")
        .get("/").check(status.is(200)))
  }

  val createOrders = scenario("Create Orders").during(3.minutes) {
    exec(
      http("Create Order")
        .post("/")
          .body(StringBody(" { \"amount\" : " + ThreadLocalRandom.current().nextDouble(2500) + "}"))
        .check(status.is(201))
    ).pause(25.milliseconds)
  }

  val createOrderAndRetrieve = scenario("Create and Retrieve Order").during(3.minutes) {
    exec(
      http("Create Order For Retrieval")
        .post("/")
        .body(StringBody(" { \"amount\" : " + ThreadLocalRandom.current().nextDouble(1234) + "}"))
        .check(status.is(201)).check(header("Location").saveAs("location")))
      .exec(http("Get Created Order")
          .get("${location}").check(status.is(200))
    ).pause(50.milliseconds, 500.milliseconds)

  }


  setUp(
//    retrieveOrders.inject(rampUsers(50) over 10.seconds).protocols(httpConf),
//    createOrders.inject(rampUsers(10) over 5.seconds).protocols(httpConf),
    createOrderAndRetrieve.inject(rampUsers(25) over 10.seconds).protocols(httpConf)
  )
}