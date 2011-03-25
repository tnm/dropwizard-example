package com.yammer.example.health

import com.yammer.metrics.core.HealthCheck
import com.yammer.metrics.core.HealthCheck.Result
import com.yammer.example.data.Template
import com.google.inject.{Singleton, Inject}

@Singleton
class TemplateHealthCheck @Inject()(template: Template) extends HealthCheck {
  def name = "template"

  def check = {
    template(Some("poo"))
    template(None)
    Result.healthy()
  }
}
