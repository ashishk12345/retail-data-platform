package com.retail.platform.common

import com.retail.platform.config.ApplicationConfig
import org.apache.spark.sql.SparkSession

object SparkSessionFactory {

  lazy val spark: SparkSession = {

    SparkSession.builder()
      .master(ApplicationConfig.getString("spark.master"))
      .appName(ApplicationConfig.getString("spark.appName"))
      .getOrCreate()

  }

}