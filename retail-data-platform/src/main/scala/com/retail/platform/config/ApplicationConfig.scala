package com.retail.platform.config

import com.typesafe.config.{Config, ConfigFactory}

object ApplicationConfig {

  private val config: Config =
    ConfigFactory.load()

  def getString(path: String): String =
    config.getString(path)

}