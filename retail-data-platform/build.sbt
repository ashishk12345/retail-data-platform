ThisBuild / version := "1.0.0"

ThisBuild / scalaVersion := "2.12.15"

lazy val sparkVersion = "3.5.1"

lazy val root = (project in file("."))
  .settings(
    name := "retail-data-platform",

    libraryDependencies ++= Seq(

      "org.apache.spark" %% "spark-core" % sparkVersion,
      "org.apache.spark" %% "spark-sql"  % sparkVersion,
      "com.typesafe" % "config" % "1.4.3",
      "org.apache.logging.log4j" % "log4j-api" % "2.24.0",
      "org.apache.logging.log4j" % "log4j-core" % "2.24.0",
      "org.scalatest" %% "scalatest" % "3.2.19" % Test
    )
  )