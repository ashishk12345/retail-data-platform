package com.retail.platform.jobs

import com.retail.platform.config.ApplicationConfig

object RetailDataPlatformApp {

  def main(args: Array[String]): Unit = {

    println("Application Started")

    println(ApplicationConfig.getString("app.name"))

    println(ApplicationConfig.getString("spark.master"))

  }

}