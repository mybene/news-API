import org.sql2o.*;

import java.awt.*;


public class DB {
    //    public static Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/media", "wecode", "12307");
    static String connectionString = "jdbc:postgresql://c2-54-235-100-99.compute-1.amazonaws.com:5432/d57bhvrthn1vjj"; //!
    static Sql2o sql2o = new Sql2o(connectionString, "nwnqrqjgzzxwtc", "855916fd80e7462e4c6b3c69fa5eca20ca88fe64638d29b30f902b9eb9877a0c"); //!
}