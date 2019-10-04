
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;
import static spark.Spark.*;

//import spark.template.handlebars.HandlebarsTemplateEngine;
public class App {
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }

    public static void main(String[] args){
        port(getHerokuAssignedPort());
        staticFileLocation("/public");

        get("/",((request, response) -> {
            Map<String,Object> model=new HashMap<>();
            return new ModelAndView(model, "home.hbs");
        }), new HandlebarsTemplateEngine());

        get("/new/department",(request, response) -> {
            Map<String,Object> model=new HashMap<>();
            return new ModelAndView(model, "departmentForm.hbs");
        }, new HandlebarsTemplateEngine());


        post ("/all/dept",((request, response) -> {
            Map<String,Object> model=new HashMap<>();
            String dname=request.queryParams("dname");
            String deptid=request.queryParams("deptid");
            String members= request.queryParams("members");
            String slogon=request.queryParams("slogon");
            model.put("dname",dname);
            model.put("members",members);
            model.put("slogon",slogon);
            Departments department=new Departments(deptid,dname,slogon,members);
            department.save();
            model.put("departmnet",department);
            return new ModelAndView(model, "logged.hbs");
        }), new HandlebarsTemplateEngine());
//to view all list of the depts saved
        get("/depts",(request, response) -> {
            Map<String,Object>model=new HashMap<>();
            model.put("departments", Departments.all());
            return new ModelAndView(model,"AllDepartments.hbs");
        },new HandlebarsTemplateEngine());


//to call the employee form input
        get("/user-new",((request, response) -> {
            Map<String,Object> model=new HashMap<>();
            return new ModelAndView(model, "userForm.hbs");
        }), new HandlebarsTemplateEngine());
//notification of being saved as employee
        post("/users",(request, response) -> {
            Map<String,Object> model=new HashMap<>();
            String name=request.queryParams("name");
            String  position= request.queryParams("position");
            String dept= request.queryParams("dept");
            String  badgeid=request.queryParams("badgeid");
            User employee=new User( name, position, dept, badgeid);
            employee.save();
            model.put("name",name);
            model.put("position",position);
            model.put("dept)",badgeid);
            model.put(" badgeid" ,dept);
            model.put("employee",employee);
            return new ModelAndView(model, "usersaved.hbs");
        },new HandlebarsTemplateEngine());

//get list of all saved employees
        get("/all/users",(request, response) -> {
            Map<String,Object> model=new HashMap<>();
            List<User> users = User.all();
            model.put("users",users);
            return new ModelAndView(model, "users.hbs");
        },new HandlebarsTemplateEngine());


//call news form
        get("/news",((request, response) -> {
            Map<String,Object> model=new HashMap<>();
            return new ModelAndView(model, "newsForm.hbs");
        }), new HandlebarsTemplateEngine());

//get the written articles
        post("/article/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String title=request.queryParams("title");
            String content=request.queryParams("content");
            News article= new News(title,content);
            article.save();
            model.put("title",title);
            model.put("content",content);
            return new ModelAndView(model, "received.hbs");
        }, new HandlebarsTemplateEngine());
// to see all saved article;
        get("/arictle/all",((request, response) -> {
            Map<String,Object> model=new HashMap<>();
            List<News> article=News.all();
            model.put("article",article);
            return new ModelAndView(model, "article.hbs");
        }), new HandlebarsTemplateEngine());
        //delete the news per id
        get("/news/:id/delete", (request, response) -> {
            Map<String, Object> article = new HashMap<>();
            int idOfNewsToDelete = Integer.parseInt(request.params("id"));
            News deleteNews= News.findById(idOfNewsToDelete); //change
            deleteNews.delete();
            response.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());


        get("/annoucement",((request, response) -> {
            Map<String,Object> model=new HashMap<>();
            return new ModelAndView(model, "deptNewsForm.hbs");
        }), new HandlebarsTemplateEngine());

//get the written articles
        post("/annoucement/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String headline=request.queryParams("headline");
            String develop=request.queryParams("develop");
            DepartmentNews annoucement= new DepartmentNews(headline,develop);
            annoucement.save();
            model.put("headline",headline);
            model.put("develop",develop);
            return new ModelAndView(model, "NewsDept.hbs");
        }, new HandlebarsTemplateEngine());
// to see all saved article;
        get("/annoucement/all",((request, response) -> {
            Map<String,Object> model=new HashMap<>();
            List<News> annoucement=News.all();
            model.put("annoucement",annoucement);
            return new ModelAndView(model, "annoucements.hbs");
        }), new HandlebarsTemplateEngine());
        //delete the news per id
        get("/annoucement/:id/delete", (request, response) -> {
            Map<String, Object> article = new HashMap<>();
            int idOfNewsToDelete = Integer.parseInt(request.params("id"));
            News deleteNews= News.findById(idOfNewsToDelete); //change
            deleteNews.delete();
            response.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());




    }

}

