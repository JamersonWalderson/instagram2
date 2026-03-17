package gatling.simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class BasicSimulation extends Simulation {

  val httpProtocol = http
    .baseUrl("http://localhost:8080")
    .acceptHeader("application/json")

  val scn = scenario("Teste API Posts")
    .exec(http("GET Posts")
      .get("/api/posts")
      .check(status.is(200)))
    .pause(1)
    .exec(http("POST Post")
      .post("/api/posts")
      .header("Content-Type", "application/json")
      .body(StringBody("""{"title":"Test Post","content":"Test Content"}"""))
      .check(status.is(201)))

  setUp(
    scn.inject(
      rampUsers(100).during(30.seconds)
    )
  ).protocols(httpProtocol)
}
