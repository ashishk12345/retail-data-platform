package com.retail.platform.validators.rules

import com.retail.platform.validators.{ValidationResult, Validator}
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions.{col,lit}

object NullCustomerIdValidator extends Validator {

  override val rejectionReason = "CUSTOMER_ID_NULL"

  override def validate(df: DataFrame): ValidationResult = {
    val validDF = df.filter(col("customer_id").isNotNull)
    val invalidDF = df.filter(col("customer_id").isNull)
      .withColumn("rejection_reason",lit(rejectionReason))

    ValidationResult(validDF, invalidDF)
  }

}
