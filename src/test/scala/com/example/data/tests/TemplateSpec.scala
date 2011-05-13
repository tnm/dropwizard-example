package com.example.data.tests

import com.codahale.simplespec.Spec
import com.yammer.example.data.Template

object TemplateSpec extends Spec {
  class `A template` {
    private val template = Template("Hello there %s.", "stranger")

    def `should have a template` = {
      template.template must beEqualTo("Hello there %s.")
    }

    def `should have a default name` = {
      template.defaultName must beEqualTo("stranger")
    }

    def `should render the template` = {
      template(Some("Frank")) must beEqualTo("Hello there Frank.")
    }

    def `should render the template using the default name` = {
      template(None) must beEqualTo("Hello there stranger.")
    }
  }
}
