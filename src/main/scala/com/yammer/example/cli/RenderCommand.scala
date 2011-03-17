package com.yammer.example.cli

import com.yammer.dropwizard.cli.ConfiguredCommand
import com.yammer.example.data.Template
import org.apache.commons.cli.Options

class RenderCommand extends ConfiguredCommand {
  override protected def commandSyntax(jarSyntax: String) =
    "%s %s [options] <config file> [name1 name2]".format(jarSyntax, name)


  override def description = Some("Renders the configured template to console")

  override def cliOptions = {
    val opts = new Options
    opts.addOption("i", "include-default", false, "Also render the template with the default name")
    Some(opts)
  }

  def name = "render"

  def runWithConfigFile(opts: Map[String, List[String]],
                        args: List[String]) = {

    val template = injector.getInstance(classOf[Template])

    if (opts.contains("include-default")) {
      log.info("DEFAULT => %s", template(None))
    }

    args.foreach { name => log.info("%s => %s", name, template(Some(name))) }

    None
  }
}
