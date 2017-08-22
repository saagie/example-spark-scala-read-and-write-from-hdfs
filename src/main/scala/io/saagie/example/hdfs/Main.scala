package io.saagie.example.hdfs

import org.apache.log4j.LogManager
import org.apache.spark.sql.{SaveMode, SparkSession}

object Main{

  case class HelloWorld(message: String)

  def main(args: Array[String]): Unit = {

    val log = LogManager.getRootLogger
    // Creation of Spark Session
    val sparkSession = SparkSession.builder().appName("example-spark-scala-read-and-write-from-hdfs").getOrCreate()
    import sparkSession.implicits._

    val hdfs_master = args(0)
    // ====== Creating a dataframe with 1 partition
    val df = Seq(HelloWorld("helloworld")).toDF().coalesce(1)

    // ======= Writing files
    // Writing file as parquet
    df.write.mode(SaveMode.Overwrite).parquet(hdfs_master + "user/hdfs/wiki/testwiki")
    //  Writing file as csv
    df.write.mode(SaveMode.Overwrite).csv(hdfs_master + "user/hdfs/wiki/testwiki.csv")

    // ======= Reading files
    // Reading parquet files
    val df_parquet = sparkSession.read.parquet(hdfs_master + "user/hdfs/wiki/testwiki")
    log.info(df_parquet.show())
    //  Reading csv files
    val df_csv = sparkSession.read.option("inferSchema", "true").csv(hdfs_master + "user/hdfs/wiki/testwiki.csv")
    log.info(df_csv.show())
  }
}
