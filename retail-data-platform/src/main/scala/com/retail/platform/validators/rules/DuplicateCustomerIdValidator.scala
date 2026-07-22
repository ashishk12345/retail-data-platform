package com.retail.platform.validators.rules

import com.retail.platform.validators.{ValidationResult, Validator}
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions.{col,lit}

object DuplicateCustomerIdValidator extends Validator {

  override val rejectionReason = "DUPLICATE_CUSTOMER_ID"

  override def validate(df: DataFrame): ValidationResult = {
    val duplicateIds = df
      .groupBy("customer_id")
      .count()
      .filter(col("count")>1)
      .drop(col("count"))

    duplicateIds.printSchema()

    val invalidDF = df
      .join(duplicateIds, Seq("customer_id"), "inner")
      .withColumn("rejection_reason", lit(rejectionReason))

    val validDF = df.join(duplicateIds,Seq("customer_id"),"left_anti")

    ValidationResult(validDF,invalidDF)
  }
}
