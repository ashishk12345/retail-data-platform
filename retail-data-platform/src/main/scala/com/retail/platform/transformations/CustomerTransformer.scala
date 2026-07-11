package com.retail.platform.transformations

import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions.{lit,col,upper}
object CustomerTransformer {

  private val stateName = "MAHARASHTRA"
  private def standardizeName(df: DataFrame): DataFrame ={
    df.withColumn("name",upper(col("name")))
  }
  private def addActiveFlag(df: DataFrame): DataFrame ={
    df.withColumn("is_active",lit(true))
  }
  private def filterByState(df: DataFrame): DataFrame = {
    df.filter(upper(col("state"))=== stateName)
  }
  def transform(df: DataFrame): DataFrame = {

    val standardizedDF = standardizeName(df)
    val activeCustomerDF = addActiveFlag(standardizedDF)
    val filteredDF = filterByState(activeCustomerDF)

    filteredDF
  }

}