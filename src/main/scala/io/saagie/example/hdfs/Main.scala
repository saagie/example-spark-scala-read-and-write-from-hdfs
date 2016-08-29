package io.saagie.example.hdfs

import org.apache.log4j.LogManager
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SQLContext

object Main{

  case class HelloWorld(message: String)

  def main(args: Array[String]): Unit = {

    val log = LogManager.getRootLogger
    // Configuration of SparkContext
    val conf = {
      new SparkConf()
        .setAppName("example-spark-scala-read-and-write-from-hdfs")
        .set("spark.executor.memory", "512M")
    }

    // Creation of SparContext and SQLContext
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)
    import sqlContext.implicits._

    val hdfs_master = args(0)
    // ====== Creating a dataframe with 1 partition
    val df = Seq(HelloWorld("helloworld")).toDF().coalesce(1)

    // ======= Writing files
    // Writing file as parquet
    df.write.format("parquet").mode("overwrite").save(hdfs_master + "user/hdfs/wiki/testwiki")
    //  Writing file as csv
    df.write.format("com.databricks.spark.csv").mode("overwrite").save(hdfs_master + "user/hdfs/wiki/testwiki.csv")

    // ======= Reading files
    // Reading parquet files
    val df_parquet = sqlContext.read.parquet(hdfs_master + "user/hdfs/wiki/testwiki")
    log.info(df_parquet.show())
    //  Reading csv files
    val df_csv = sqlContext.read.format("com.databricks.spark.csv").option("inferSchema", "true").load(hdfs_master + "user/hdfs/wiki/testwiki.csv")
    log.info(df_csv.show())
  }
}
