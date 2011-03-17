package com.yammer.example

import com.yammer.dropwizard.Service
import com.yammer.dropwizard.service.Jersey
import config.TemplateModule

object Example extends Service with Jersey {
  require(new TemplateModule)

  def name = "example-service"
  
  override def banner = Some("""
HERP A DERP DERP HERIPTY DERP DERP HERP A DING DERP DOP TEEDLY TUM HERP DOOP BOH
HERP A DERP DERP HERIPTY DERP DERP HERP A DING DERP DOP TEEDLY TUM HERP DOOP BOH
HERP A DERP DERP               Example Service!                    HERP DOOP BOH
HERP A DERP DERP HERIPTY DERP DERP HERP A DING DERP DOP TEEDLY TUM HERP DOOP BOH
HERP A DERP DERP HERIPTY DERP DERP HERP A DING DERP DOP TEEDLY TUM HERP DOOP BOH
""")
}
