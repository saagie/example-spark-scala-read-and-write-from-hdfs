import sbt.Keys._

name := "example-spark-scala-read-and-write-from-hdfs"

version := "1.0"

scalaVersion := "2.10.5"

libraryDependencies += "org.apache.spark" %% "spark-core" % "1.6.1" % "provided"
libraryDependencies += "org.apache.spark" %% "spark-sql" % "1.6.1" % "provided"
libraryDependencies += "com.databricks" %% "spark-csv" % "1.3.0"

assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = false)
