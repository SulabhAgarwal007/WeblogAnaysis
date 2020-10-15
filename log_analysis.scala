import org.apache.spark.sql.Row
import org.apache.spark.sql.types.{StructType, StructField, StringType}
import org.apache.spark._
import org.apache.spark.SQLContext

val data1 = sc.textFile("./text_file.txt")

--val split_data3 = data1.map(_.split(" - - ")).map(x=>Row(x(0),x(1)))
val split_data = data.map(_.split("\\s\\d+\\s\\D")).map(x=>Row(x(0),x(1)))

--val pattern2 = "\\s\\d+\\s\\D".r 

val header_str = "p1 p2"
val schema= StructType(header_str.split(" ").map(a=>StructField(a,StringType, true)))


val sqlContext = new SQLContext(sc)

val split_df = sqlContext.createDataFrame(split_data, schema)
split_df.registerTempTable("split_df")

val sql_data= sqlContext.sql("select * from split_df")

sql_data.show()

val p1_data = sqlContext.sql("select p1 from split_df")

val df = p1_data.withColumn("ip", split($"p1"," - - ").getItem(0)).withColumn("rest", split($"p1"," - - ").getItem(1))

var df1 = df.withColumn("date", split($"rest","\\s").getItem(0)).withColumn("path", split($"rest","\\s").getItem(2)).withColumn("status", split($"rest","\\s
").getItem(4)).drop($"rest")


import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

--val conf = new SparkConf().setAppName("weblog analysis")

val file = SparkContext.getOrCreate.textFile("./text_file.txt")

var split_file = file.flatMap(line=>line.split(" "))

var df = split_file.toDF()


import org.apache.spark.sql.Row
import org.apache.spark.sql.types.{StructType, StructField, StringType}
import org.apache.spark._
import org.apache.spark.sql.SQLContext

val data = sc.textFile("./text_file.txt")

--val split_data3 = data1.map(_.split(" - - ")).map(x=>Row(x(0),x(1)))
val split_data = data.map(_.split("\\s\\d+\\s\\D")).map(x=>Row(x(0),x(1)))

--val pattern2 = "\\s\\d+\\s\\D".r

--val header_str = "p1 p2"
--val schema= StructType(header_str.split(" ").map(a=>StructField(a,StringType, true)))

val schema= StructType("part1 part2".split(" ").map(a=>StructField(a,StringType, true)))

val sqlContext = new SQLContext(sc)

val split_df = sqlContext.createDataFrame(split_data, schema)
split_df.registerTempTable("split_df")

--val sql_data= sqlContext.sql("select * from split_df")

--sql_data.show()

val p1_data = sqlContext.sql("select p1 from split_df")

--val df = p1_data.withColumn("ip", split($"p1"," - - ").getItem(0)).withColumn("rest", split($"p1"," - - ").getItem(1))

--var df1 = df.withColumn("date", split($"rest","\\s").getItem(0)).withColumn("path", split($"rest","\\s").getItem(2)).withColumn("status", split($"rest","\\s
").getItem(4)).drop($"rest")

var df2 = part1_df.withColumn("IP", split($"part1", " - - ").getItem(0)).withColumn("Date",split(split($"part1", " - - ").getItem(1)," ").getItem(0)).withColumn("Rest",split(split($"part1", " - - ").getItem(1)," ").getItem(3)).withColumn("Request_type",split(split($"part1", " - - ").getItem(1)," ").getItem(4)).withColumn("Request_status",split(split($"part1", " - - ").getItem(1)," ").getItem(5)).withColumn("Request_status",split(split($"part1", " - - ").getItem(1)," ").getItem(6)).drop("part1")

var df3 = df2.withColumn("Main_Category", split($"Rest", "/").getItem(1)).withColumn("Sub_Category", split($"Rest", "/").getItem(2)).withColumn("Product", s
plit($"Rest", "/").getItem(3))


// mysql -h cxln2.c.thelab-240901.internal -u sqoopuser -p
//NHkkP876rp


//sqoop export -connect jdbc:mysql://cxln2.c.thelab-240901.internal/sqoopex -username sqoopuser -P -table web_log_data -export-dir /user/sulabhagarwaliet6372/file_output_10_lakh_rec.csv/ -m 1