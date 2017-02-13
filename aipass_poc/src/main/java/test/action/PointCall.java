package test.action;

import test.Test;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 * Created by q.a.guo on 2/10/2017.
 */
@Path("/path1")
public class PointCall {
    @GET
    @Produces("text/plain")
    public String getPoint(@QueryParam("hdfsFile") final String hdfsFile) {
        String file = null;
        if(null == hdfsFile) {
            file = "hdfs://n1.c1.ai:8020/home/spark/POC/derby.log";
        } else {
            file = hdfsFile;
        }
        return "[" + hdfsFile + "] total word count = [" + Test.wordCount(file) + "]";
    }

    @GET
    @Produces("text/plain")
    @Path("/test")
    public String test(@QueryParam("path") final String path) {
        return "test ! path = " + path;
    }
}
