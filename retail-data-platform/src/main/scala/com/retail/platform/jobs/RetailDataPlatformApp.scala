package com.retail.platform.jobs

import com.retail.platform.common.SparkSessionFactory
import com.retail.platform.config.ApplicationConfig
import com.retail.platform.readers.CSVReader
import com.retail.platform.readers.CSVReader.getClass
import com.retail.platform.transformations.CustomerTransformer
import com.retail.platform.validators.DataValidator
import com.retail.platform.writers.DataFrameWriter
import org.apache.logging.log4j.{LogManager, Logger}
object RetailDataPlatformApp {

  private val logger: Logger = LogManager.getLogger(getClass)

  def main(args: Array[String]): Unit = {

    logger.info("Retail Data Platform Started")

    val spark = SparkSessionFactory.spark
    val inputPath = ApplicationConfig.getString("files.customers")

    val validOutputPath =  ApplicationConfig.getString("files.validOutput")
    val invalidOutputPath = ApplicationConfig.getString("files.invalidOutput")

    val customerDF = CSVReader.read(spark, inputPath)
    val validatedDF = DataValidator.validate(customerDF)
    val transformedDF = CustomerTransformer.transform(validatedDF.validDF)

    DataFrameWriter.write(transformedDF,validOutputPath)
    DataFrameWriter.write(validatedDF.invalidDF,invalidOutputPath)

    spark.stop()

  }
}