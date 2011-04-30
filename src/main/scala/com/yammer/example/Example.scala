package com.yammer.example

import cli.RenderCommand
import config.TemplateFactory
import health.TemplateHealthCheck
import com.codahale.fig.Configuration
import com.yammer.dropwizard.{Environment, Service}
import resources.HelloWorldResource

object Example extends Service {
  provide(new RenderCommand)

  def name = "example-service"
  
  override def banner = Some("""
HERP A DERP DERP HERIPTY DERP DERP HERP A DING DERP DOP TEEDLY TUM HERP DOOP BOH
HERP A DERP DERP HERIPTY DERP DERP HERP A DING DERP DOP TEEDLY TUM HERP DOOP BOH
HERP A DERP DERP               Example Service!                    HERP DOOP BOH
HERP A DERP DERP HERIPTY DERP DERP HERP A DING DERP DOP TEEDLY TUM HERP DOOP BOH
HERP A DERP DERP HERIPTY DERP DERP HERP A DING DERP DOP TEEDLY TUM HERP DOOP BOH
""")

  def configure(implicit config: Configuration, environment: Environment) {
    implicit val template = TemplateFactory.buildTemplate
    environment.addHealthCheck(new TemplateHealthCheck)
//    // enable this if you'd like to see what deadlock detection looks like
//    environment.manage(new com.yammer.example.config.Derplocker)

    environment.addResource(new HelloWorldResource)
  }
}
