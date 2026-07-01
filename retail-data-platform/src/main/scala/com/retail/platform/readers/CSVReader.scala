package com.retail.platform.readers
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

object CSVReader {

  private val logger: Logger = LogManager.getLogger(getClass)

  def read(spark:SparkSession,filePath:String):DataFrame ={
    try{
      logger.info(s"Reading file: $filePath")
      spark.read
        .option("header", "true")
        .option("inferSchema", "true")
        .csv(filePath)
    }catch {
      case e : Exception => logger.error(s"File not found $filePath",e)
        throw e
    }
  }

}
