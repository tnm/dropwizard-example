package com.yammer.example.cli

import com.yammer.dropwizard.cli.{Flag, ConfiguredCommand}
import com.yammer.dropwizard.Service
import com.codahale.fig.Configuration
import com.yammer.example.config.TemplateFactory

class RenderCommand extends ConfiguredCommand {
  def name = "render"

  override def description = Some("Renders the configured template to console")

  override protected def commandSyntax(jarSyntax: String) =
    "%s %s [options] <config file> [name1 name2]".format(jarSyntax, name)

  override def options = Flag("i", "include-default", "Also render the template with the default name") :: Nil


  def run(service: Service, config: Configuration, opts: Map[String, List[String]], args: List[String]) = {
    val template = TemplateFactory.buildTemplate(config)

    if (opts.contains("include-default")) {
      log.info("DEFAULT => %s", template(None))
    }

    args.foreach { name => log.info("%s => %s", name, template(Some(name))) }

    None
  }
}
