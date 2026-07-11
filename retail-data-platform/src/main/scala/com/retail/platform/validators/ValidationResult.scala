package com.retail.platform.validators

import org.apache.spark.sql.DataFrame

case class ValidationResult(validDF: DataFrame, invalidDF: DataFrame)