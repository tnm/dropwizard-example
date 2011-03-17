package com.yammer.example.config

import com.codahale.fig.Configuration
import com.google.inject.{Provides, Singleton}
import com.yammer.example.data.Template
import com.yammer.dropwizard.modules.ProviderModule

class TemplateModule extends ProviderModule {
  @Provides
  @Singleton
  def provideTemplate(config: Configuration): Template = {
    Template(
      config("example.template").asRequired[String],
      config("example.default_name").or("Stranger")
    )
  }
}
