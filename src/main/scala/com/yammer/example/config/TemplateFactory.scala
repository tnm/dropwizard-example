package com.yammer.example.config

import com.codahale.fig.Configuration
import com.yammer.example.data.Template

object TemplateFactory {
  def buildTemplate(implicit config: Configuration): Template = {
    Template(
      config("example.template").asRequired[String],
      config("example.default_name").or("Stranger")
    )
  }
}
