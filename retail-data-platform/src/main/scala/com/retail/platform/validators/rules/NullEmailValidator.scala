package com.retail.platform.validators.rules

import com.retail.platform.validators.{ValidationResult, Validator}
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions.{col, lit}

object NullEmailValidator extends Validator {

  override val rejectionReason = "EMAIL_NULL"

  override def validate(df: DataFrame): ValidationResult = {
    val validDF = df.filter(col("email").isNotNull)
    val invalidDF = df.filter(col("email").isNull)
      .withColumn("rejection_reason",lit(rejectionReason))

    ValidationResult(validDF, invalidDF)
  }

}
