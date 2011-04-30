package com.yammer.example.config

import com.yammer.dropwizard.lifecycle.Managed
import com.codahale.logula.Logging

/**
 * A total doofus class which intentionally creates a deadlock so as to provide
 * an example of what Metrics' deadlock detection looks like in a healthcheck.
 */
class Derplocker extends Managed with Logging {
  val lockA = new Object
  val lockB = new Object

  override def start() {
    log.info("Starting")

    val a = new Thread {
      // acquire A, wait 100, acquire B
      override def run() {
        log.info("Derp!")
        lockA.synchronized {
          Thread.sleep(100)
          lockB.synchronized {
            log.warn("HERP DERP") // should never be reached
          }
        }
      }
    }

    val b = new Thread {
      // acquire B, wait 100, acquire A
      override def run() {
        log.info("Herp!")
        lockB.synchronized {
          Thread.sleep(100)
          lockA.synchronized {
            log.warn("DERP HERP") // should never be reached
          }
        }
      }
    }

    a.start()
    b.start()
  }
}
