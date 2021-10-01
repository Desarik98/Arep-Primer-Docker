package edu.escuelaing.arep;

import static spark.Spark.*;

/**
 * Hello world!
 *
 */
public class SparkWebServer
{
    private static BDConnection mongoConnection = new BDConnection();
    public static void main(String... args){
        port(getPort());
        get("hello", (req,res) -> "Hello Docker!");
        post("/message", (req,res) -> insertMessage(req,res));
        get("daniel", (req,res) -> "Daniel");

    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }

    private static String insertMessage(spark.Request request, spark.Response response){
        mongoConnection.insertMessage(request.body());
        String content = getMessages(request,response);
        return content;
    }

    private static String getMessages(spark.Request request, spark.Response response){
        String content = mongoConnection.getData();
        return content;
    }

}
