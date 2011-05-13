package com.example.resources.test

import com.codahale.simplespec.Spec
import com.yammer.example.resources.HelloWorldResource
import com.yammer.example.data.{Saying, Template}

object HelloWorldResourceSpec extends Spec {
  class `Saying hello to the world` {
    private implicit val template = Template("Hello there %s.", "stranger")
    private val resource = new HelloWorldResource

    def `should return a saying with a unique ID` = {
      resource.sayHello(None) must beEqualTo(Saying(1, "Hello there stranger."))
      resource.sayHello(None) must beEqualTo(Saying(2, "Hello there stranger."))
    }

    def `should use a specific name if one is given` = {
      resource.sayHello(Some("Frank")) must beEqualTo(Saying(1, "Hello there Frank."))
    }
  }
}
