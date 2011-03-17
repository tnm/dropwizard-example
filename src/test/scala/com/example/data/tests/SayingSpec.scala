package com.example.data.tests

import com.codahale.simplespec.Spec
import com.yammer.example.data.Saying

object SayingSpec extends Spec {
  class `A saying` {
    val saying = Saying(1, "yay")

    def `should have an ID` {
      saying.id must beEqualTo(1)
    }

    def `should have content` {
      saying.content must beEqualTo("yay")
    }
  }
}
