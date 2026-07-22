package com.retail.platform.validators

import com.retail.platform.validators.rules.{DuplicateCustomerIdValidator, NullCustomerIdValidator, NullEmailValidator}
import org.apache.spark.sql.DataFrame

import scala.collection.mutable.ListBuffer

object DataValidator {

  private val validators: Seq[Validator] = Seq(
    NullCustomerIdValidator,
    NullEmailValidator,
    DuplicateCustomerIdValidator
  )

  def validate(df: DataFrame): ValidationResult = {

    var currentDF = df
    val rejectedDFs = ListBuffer[DataFrame]()

    validators.foreach { validator =>

      val resultDF = validator.validate(currentDF)
      currentDF = resultDF.validDF
      rejectedDFs += resultDF.invalidDF
    }
    val invalidDF = if (rejectedDFs.nonEmpty) rejectedDFs.reduce(_ union _) else currentDF.sparkSession.emptyDataFrame
    ValidationResult(currentDF, invalidDF)
  }
}