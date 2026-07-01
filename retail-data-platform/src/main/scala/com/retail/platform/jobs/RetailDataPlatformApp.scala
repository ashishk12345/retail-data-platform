package com.retail.platform.jobs

import com.retail.platform.common.SparkSessionFactory
import com.retail.platform.config.ApplicationConfig
import com.retail.platform.readers.CSVReader
import com.retail.platform.validators.DataValidator
object RetailDataPlatformApp {

  def main(args: Array[String]): Unit = {

    println("Retail Data Platform Started")
    val spark = SparkSessionFactory.spark
    val inputPath =
      ApplicationConfig.getString("paths.input") + "/" +
        ApplicationConfig.getString("files.customers")

    val customerDf = CSVReader.read(spark, inputPath)

    val validatedDF = DataValidator.validate(customerDf)
    validatedDF.printSchema()
    validatedDF.show()
    spark.stop()

  }
}