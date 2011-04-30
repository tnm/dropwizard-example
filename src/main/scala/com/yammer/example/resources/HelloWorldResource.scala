package com.yammer.example.resources

import javax.ws.rs.core.MediaType
import javax.ws.rs.{QueryParam, GET, Produces, Path}
import com.yammer.example.data.{Saying, Template}
import java.util.concurrent.atomic.AtomicLong
import com.yammer.metrics.Instrumented

@Path("/hello-world")
@Produces(Array(MediaType.APPLICATION_JSON))
class HelloWorldResource(implicit template: Template) extends Instrumented {
  private val counter = new AtomicLong(0)
  private val getTimer = metrics.timer("say-hello")

  @GET
  def sayHello(@QueryParam("name") name: Option[String]) = getTimer.time {
    Saying(counter.incrementAndGet, template(name))
  }
}
