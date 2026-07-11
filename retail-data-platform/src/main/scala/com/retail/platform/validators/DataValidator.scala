package com.retail.platform.validators

import com.retail.platform.common.SparkSessionFactory
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.functions.col

object DataValidator {

  private def removeNullCustomerId(df: DataFrame): DataFrame = {
    df.filter(col("customer_id").isNotNull)
  }

  def validate(df: DataFrame): ValidationResult = {

      val validDF = removeNullCustomerId(df)

      ValidationResult(
        validDF = validDF,
        invalidDF = SparkSessionFactory.spark.emptyDataFrame
      )
    }
}