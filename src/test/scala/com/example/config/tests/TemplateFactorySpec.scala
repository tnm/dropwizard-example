package com.example.config.tests

import com.codahale.simplespec.Spec
import io.Source
import com.yammer.example.data.Template
import com.codahale.fig.{ConfigurationException, Configuration}
import com.yammer.example.config.TemplateFactory

object TemplateFactorySpec extends Spec {
  class `A config file with a template and a default name` {
    private implicit val config = new Configuration(Source.fromString("""
      {
        "example": {
          "template": "Hello there %s.",
          "default_name": "dude"
        }
      }
    """))

    def `should produce a template with a default name` = {
      TemplateFactory.buildTemplate must beEqualTo(Template("Hello there %s.", "dude"))
    }
  }

  class `A config file with a template and no default name` {
    private implicit val config = new Configuration(Source.fromString("""
      {
        "example": {
          "template": "Hello there %s."
        }
      }
    """
      )
    )

    def `should produce a template with a default name` = {
      TemplateFactory.buildTemplate must beEqualTo(Template("Hello there %s.", "Stranger"))
    }
  }

  class `A config file with no template` {
    private implicit val config = new Configuration(Source.fromString("""
      {
        "example": {
        }
      }
    """
      )
    )

    def `should throw an exception` = {
      TemplateFactory.buildTemplate must throwA[ConfigurationException]
    }
  }
}
