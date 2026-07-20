package com.retail.platform.validators

import org.apache.spark.sql.DataFrame

trait Validator {

  def rejectionReason:String
  def validate(df: DataFrame): ValidationResult
}