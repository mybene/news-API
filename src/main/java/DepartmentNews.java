import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;
import java.util.List;

public class DepartmentNews {
   private String headline;
   private String  develop;
   private int id;

    public DepartmentNews(String headline, String develop) {
        this.headline = headline;
        this.develop = develop;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getDevelop() {
        return develop;
    }

    public void setDevelop(String develop) {
        this.develop = develop;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void save() {
        String given="INSERT INTO annoucements(headline,develop) VALUES (:headline,:develop)";
        try(Connection con = DB.sql2o.open()){
            con.createQuery(given).addParameter("headline", headline)
                    .addParameter("develop", develop).executeUpdate();

        }

    }
    public static List<DepartmentNews>all() {
        String given="SELECT *FROM annoucements ";
        try(Connection con = DB.sql2o.open()){
            return con.createQuery(given).executeAndFetch(DepartmentNews.class);
        }
    }


    void delete() {
        String given="DELETE  FROM annoucements WHERE id=:id" ;
        try(Connection con = DB.sql2o.open()){
            con.createQuery(given).addParameter("headline",headline).executeUpdate();
        }catch (Sql2oException ex){
            System.out.println();
        }

    }
    public static  DepartmentNews findById(int id ) {
        try(Connection con = DB.sql2o.open()){
            return con.createQuery("SELECT * FROM news WHERE  id= :id")
                    .addParameter("id", id) //key/value pair, key must match above
                    .executeAndFetchFirst(DepartmentNews.class); //fetch an individual item
        }
    }


}
