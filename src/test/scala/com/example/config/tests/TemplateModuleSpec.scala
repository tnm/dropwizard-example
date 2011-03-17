package com.example.config.tests

import com.codahale.simplespec.Spec
import io.Source
import com.yammer.example.config.TemplateModule
import com.yammer.example.data.Template
import com.codahale.fig.{ConfigurationException, Configuration}

object TemplateModuleSpec extends Spec {
  class `A config file with a template and a default name` {
    val config = new Configuration(Source.fromString("""
      {
        "example": {
          "template": "Hello there %s.",
          "default_name": "dude"
        }
      }
    """))

    val module = new TemplateModule

    def `should produce a template with a default name` {
      module.provideTemplate(config) must beEqualTo(Template("Hello there %s.", "dude"))
    }
  }

  class `A config file with a template and no default name` {
    val config = new Configuration(Source.fromString("""
      {
        "example": {
          "template": "Hello there %s."
        }
      }
    """
      )
    )

    val module = new TemplateModule

    def `should produce a template with a default name` {
      module.provideTemplate(config) must beEqualTo(Template("Hello there %s.", "Stranger"))
    }
  }

  class `A config file with no template` {
    val config = new Configuration(Source.fromString("""
      {
        "example": {
        }
      }
    """
      )
    )

    val module = new TemplateModule

    def `should throw an exception` {
      module.provideTemplate(config) must throwA[ConfigurationException]
    }
  }
}
