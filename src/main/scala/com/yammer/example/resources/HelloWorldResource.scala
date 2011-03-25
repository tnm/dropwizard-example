package com.yammer.example.resources

import javax.ws.rs.core.MediaType
import com.google.inject.{Inject, Singleton}
import javax.ws.rs.{QueryParam, GET, Produces, Path}
import com.yammer.example.data.{Saying, Template}
import java.util.concurrent.atomic.AtomicLong
import com.yammer.metrics.guice.Timed

@Path("/hello-world")
@Produces(Array(MediaType.APPLICATION_JSON))
@Singleton
class HelloWorldResource @Inject()(template: Template) {
  private val counter = new AtomicLong(0)

  @GET
  @Timed(name = "say-hello")
  def sayHello(@QueryParam("name") name: Option[String]) =
    Saying(counter.incrementAndGet, template(name))
}
