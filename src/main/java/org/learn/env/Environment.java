package org.learn.env;



public class Environment {

    private static DBProperties PROD_DB = new DBProperties("mongodb://ds235169.mlab.com:35169", "mydb", "nick", "nick123");
    private static DBProperties DEV_DB = new DBProperties("mongodb://localhost:27017", "mydb", "nick", "nick123");

    private static boolean isProd() {
        return true;
    }

    public static DBProperties getEnv() {
        if (isProd()) {
            return PROD_DB;
        }
        return DEV_DB;
    }


}
