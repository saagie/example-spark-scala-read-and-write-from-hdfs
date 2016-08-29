Example for readind & writing into hdfs with Spark Scala
==================

Package for saagie : sbt clean assembly and get the package in target.

Usage in local :

 - sbt clean assembly
 - spark-submit --class=io.saagie.example.hdfs.Main example-spark-scala-read-and-write-from-hdfs-assembly-1.0.jar "hdfs://hdfshost:8020/"

Usage in Saagie :

 - sbt clean assembly (in local, to generate jar file)
 - create new Spark Job
 - upload the jar (target/scala-2.10/example-spark-scala-read-and-write-from-hdfs-assembly-1.0.jar)
 - Replace MyClass with the full class name (ex : io.saagie.example.hdfs.Main)
 - copy URL from HDFS connection details panel and add it in first argument in the command line
 - choose Spark 1.6.1
 - choose resources you want to allocate to the job
 - create and launch.
