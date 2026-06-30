package com.retail.platform.jobs

import com.retail.platform.common.SparkSessionFactory

object RetailDataPlatformApp {

  def main(args: Array[String]): Unit = {

    println("Retail Data Platform Started")
    val spark = SparkSessionFactory.spark
    println(s"Spark Version: ${spark.version}")
    spark.stop()

  }
}