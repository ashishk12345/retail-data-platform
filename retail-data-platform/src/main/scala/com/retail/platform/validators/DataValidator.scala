package com.retail.platform.validators

import org.apache.spark.sql.DataFrame

object DataValidator {

  def validate(df: DataFrame): DataFrame = {

    println(s"Input Record Count: ${df.count()}")

    df
  }
}