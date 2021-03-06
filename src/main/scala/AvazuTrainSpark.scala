import breeze.linalg.SparseVector
import org.apache.spark.SparkContext

/**
  * Created by Taehee on 2018. 5. 31..
  */
/*
object AvazuTrainSpark {



  import java.io.{FileInputStream, FileOutputStream, ObjectOutputStream}
  import java.util.zip.GZIPInputStream

  import scala.io.Source
  import scala.util.hashing.MurmurHash3

  /**
    *
    * id, click, hour, C1, banner_pos,
    * site_id, site_domain, site_category, app_id, app_domain,
    * app_category, device_id, device_ip, device_model, device_type,
    * device_conn_type, C14, C15, C16, C17,
    * C18, C19, C20, C21

    */

  case class data(
                   id:String, click:String, hour:String, C1:String, banner_pos:String,
                   site_id:String, site_domain:String, site_category:String,
                   app_id:String, app_domain:String,
                   app_category:String, device_id:String, device_ip:String,
                   device_model:String, device_type:String,
                   device_conn_type:String, C14:String, C15:String,
                   C16:String, C17:String, C18:String,
                   C19:String, C20:String, C21:String
                 )

  def hash(c: String) = {
    math.abs(MurmurHash3.stringHash(c, 1))
  }

  def main(args: Array[String]): Unit = {




    val reader = new GZIPInputStream(new FileInputStream("/Users/Taehee/Downloads/train.gz"))
    //val writer = new PrintStream(new FileOutputStream("~/stream/avazu/avazu-train", true))
    //val filename = "/Users/Taehee/Downloads/test"

    var i = 1

    val model = new Ftrl().setAlpha(1).setBeta(1).setL1(2).setL2(0)


    var aa : Array[(Int, SparseVector[Double])] = Array.empty

    for (line <- Source.fromInputStream(reader).getLines) {

      val p = if (i == 1) Array.fill(24)("0") else line.split(",")
      val d = data(
        p(0), p(1), p(2), p(3), p(4), p(5), p(6), p(7), p(8), p(9),
        p(10), p(11), p(12), p(13), p(14), p(15), p(16), p(17), p(18), p(19),
        p(20), p(21), p(22), p(23)
      )

      import java.text.SimpleDateFormat


      val date = if (i == 1)  "20140101" else "20" + d.hour.take(6)
      val dd = new SimpleDateFormat("yyyyMMdd")
      val day = dd.parse(date).getDay()

      val hour = if (i == 1)  "01" else d.hour.drop(6)

      //println(day, hour)

      val y = d.click
      val x = Array(
        //"1" + d.hour,
        "1d" + day,
        "1h" + hour,
        "2" + d.C1,
        "3" + d.banner_pos,
        "4" + d.site_id,
        "5" + d.site_domain,
        "6" + d.site_category,
        "7" + d.app_id,
        "8" + d.app_domain,
        "9" + d.app_category,
        "10" + d.device_type,
        "11" + d.device_model,
        "12" + d.device_conn_type,
        "13" + d.C14,
        "14" + d.C15,
        "15" + d.C16,
        "16" + d.C17,
        "17" + d.C18,
        "18" + d.C19,
        "19" + d.C20,
        "20" + d.C21
      )
      val xHash = x.map(s => (hash(s), 1D)).toMap

      val sparseX = FtrlRun.mapToSparseVector(xHash, Int.MaxValue)

      aa ++ Array(y.toInt, sparseX)

      i += 1

    }


    val sc = SparkContext.getOrCreate()

    val ss = sc.parallelize(aa)

    val kk = new FtrlSpark()

    kk.update(ss)

    println(kk.globalW)

    //writer.close()

  }

}
*/