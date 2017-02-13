package test;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;

/**
 * Created by q.a.guo on 2/9/2017.
 */
public class Test {
    private static ServerShims server = null;
    public static JavaSparkContext jsc = null;
    public static void main(String args[]) {


        SparkConf conf = new SparkConf().setAppName("AIPass_POC");
        jsc = new JavaSparkContext(conf);
        server = new JettyServerShims();
        server.start();

    }

    public static int wordCount(String file) {
        JavaRDD<String> distFile = Test.jsc.textFile(file);
        JavaRDD<Integer> lineLengths = distFile.map(new Function<String, Integer>() {
            @Override
            public Integer call(String s) throws Exception {
                return s.length();
            }
        });

        int totalLength = lineLengths.reduce(new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer a, Integer b) throws Exception {
                return a + b;
            }
        });
        return totalLength;
    }
}
