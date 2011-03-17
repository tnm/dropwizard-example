package com.yammer.example.data

case class Template(template: String, defaultName: String) {
  def apply(name: Option[String]) = template.format(name.getOrElse(defaultName))
}
