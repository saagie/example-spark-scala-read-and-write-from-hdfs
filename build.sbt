import sbt.Keys._

name := "example-spark-scala-read-and-write-from-hdfs"

version := "1.1"

scalaVersion := "2.11.11"

libraryDependencies += "org.apache.spark" %% "spark-core" % "2.1.0" % "provided"
libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.1.0" % "provided"

assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = false)
