package com.retail.platform.writers

import org.apache.spark.sql.DataFrame

object DataFrameWriter {

  def write(
             df: DataFrame,
             outputPath: String
           ): Unit = {

    df.write
      .mode("overwrite")
      .option("header", "true")
      .csv(outputPath)

  }

}