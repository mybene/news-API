import org.sql2o.*;
import java.net.URI;
import java.net.URISyntaxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class DB {
        private static URI dbUri;
        public static Sql2o sql2o;
        static Logger logger = LoggerFactory.getLogger(DB.class);
        static {

                try {
                        if (System.getenv("postgresql-dimensional-10461") == null) {
                                dbUri = new URI("postgres://localhost:5432/media");
                        } else {
                                dbUri = new URI(System.getenv("postgresql-dimensional-10461"));
                        }
                        int port = dbUri.getPort();
                        String host = dbUri.getHost();
                        String path = dbUri.getPath();
                        String username = (dbUri.getUserInfo() == null) ? umimogzlysxckw: dbUri.getUserInfo().split(":")[0];
                        String password = (dbUri.getUserInfo() == null) ? 08c28bfbea02d1e27d2db1844a9dfadbb3b6b53e738c59b270af9b3741b1bf7 : dbUri.getUserInfo().split(":")[1];
                        sql2o = new Sql2o("jdbc:postgresql://" + host + ":" + port + path, username, password);
                } catch (URISyntaxException e ) {
                        logger.error("Unable to connect to database.");
                }
        }
}