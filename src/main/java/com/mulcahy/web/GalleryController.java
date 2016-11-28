package com.mulcahy.web;

import com.mulcahy.domain.Color;
import com.mulcahy.domain.Errors;
import com.mulcahy.model.Category;
import com.mulcahy.model.User;
import com.mulcahy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Chris on 11/14/2016.
 */

@Controller("galleryController")
public class GalleryController {

    @Autowired
    private UserService userService;


    private User mainUser;

    private Map<String,String> links=new HashMap<String,String>();

    @RequestMapping(value="/validate",method= RequestMethod.GET)
    public ModelAndView validate(){
        links.put("Home","inactive");

        links.put("Categories","inactive");
       ModelAndView model= new ModelAndView();
        model.addObject("Links",links);
        model.setViewName("home/Login");
        return model;

    }

    @RequestMapping(value="/Home",method=RequestMethod.GET)
    public ModelAndView goToHome(){
        ModelAndView model= new ModelAndView();
        if(mainUser==null){
            model.setViewName("redirect:/gallery/validate");
            return model;
        }
        links.put("Home","active");

        links.put("Categories","inactive");
        model.addObject("Links",links);
        model.setViewName("home/Home");
        model.addObject("User",mainUser);
        return model;
    }


    @RequestMapping(value="/login",method=RequestMethod.POST)
    public ModelAndView checkUser(HttpServletRequest request) throws SQLException{
        links.put("Home","active");

        links.put("Categories","inactive");
        ModelAndView model= new ModelAndView();
        model.addObject("Links",links);

        if(userService.checkForUser(request.getParameter("name"),request.getParameter("password"))){
            mainUser=userService.initUser(request.getParameter("name"),request.getParameter("password"));
            model.setViewName("home/Home");
            model.addObject("User",mainUser);
            return model;
        }
        model.setViewName("home/Login");
        model.addObject("error",Errors.LOGINERROR.getMessage());
        return model;
    }

    @RequestMapping(value="/profilePic",method=RequestMethod.GET)
    @ResponseBody public byte[] getProfilePic(){

        return mainUser.getImage();
    }

    @RequestMapping(value="/upload", method=RequestMethod.POST)
    public String insertData(@RequestParam CommonsMultipartFile file) throws IOException,SQLException{

        mainUser.setImage(file.getBytes());
        userService.uploadImage(mainUser,file);
        return "home/Home";

    }

    @RequestMapping(value="categories/addGif",method=RequestMethod.POST)
    @ResponseBody
    public ModelAndView addGif(@RequestParam CommonsMultipartFile file,HttpServletRequest request) throws Exception{
        ModelAndView model = new ModelAndView();
        if(userService.uploadGif(request.getParameter("categoryId"),file,request.getParameter("gifName"))){
            links.clear();
            links.put("Home", "inactive");

            links.put("Categories", "inactive");
            model.addObject("Links", links);
            model.addObject("User", mainUser);
            model.addObject("Category",userService.getCategoryById(Integer.parseInt(request.getParameter("categoryId"))));
            model.addObject("action", "addGif");
            model.setViewName("home/Gifs");
            return model;
        }
            model.setViewName("home/Home");
        model.addObject("action", "addGif");
        links.put("Home", "active");

        links.put("Categories", "inactive");
        model.addObject("Links", links);
        model.addObject("User", mainUser);

        model.addObject("error",Errors.ADDGIF.getMessage());
            return model;

    }

    @RequestMapping(value="gifs/{id}",method=RequestMethod.GET)
    @ResponseBody
    public byte[] getSourceImg(@PathVariable int id) throws Exception{
         return userService.getImage(id);
    }



    @RequestMapping(value="/Categories",method=RequestMethod.GET)
    public ModelAndView categories(){
        links.put("Home","inactive");

        links.put("Categories","active");
        ModelAndView model= new ModelAndView();
        if(mainUser==null){
            model.setViewName("redirect:/gallery/validate");
            return model;
        }
        model.addObject("Links",links);
        model.addObject("User",mainUser);
        model.addObject("action","addCategory");
        model.setViewName("home/Category");
        model.addObject("colors", Color.values());
        return model;
    }

    @RequestMapping(value="/addCategory",method=RequestMethod.POST)
    public ModelAndView addCategory(HttpServletRequest request) throws Exception{
        Category category=new Category();
        category.setName(request.getParameter("CategoryName"));
        category.setColorCode(request.getParameter("color-code"));
        ModelAndView model = new ModelAndView();
        if( userService.addCategory(mainUser,category)) {
            links.put("Home", "active");

            links.put("Categories", "inactive");
            model.addObject("Links", links);
            model.addObject("User", mainUser);
            model.setViewName("home/Home");
            return model;
        }
        model.addObject("error", Errors.CATEGORY.getMessage());
        model.addObject("Links",links);
        model.addObject("User",mainUser);
        return new ModelAndView("/home/Category");
    }
    @RequestMapping(value="/categories/{id}")
    public ModelAndView viewGifs(@PathVariable int id ){
        ModelAndView model = new ModelAndView();
        try {
            model.addObject("Category", userService.getCategoryById(id));
            links.put("Home", "inactive");

            links.put("Categories", "inactive");
            model.addObject("Links", links);
            model.addObject("action", "addGif");
            model.addObject("User", mainUser);
            model.setViewName("home/Gifs");
        }catch(Exception e){
            links.put("Home", "inactive");

            links.put("Categories", "active");
            model.addObject("Links", links);
            model.addObject("User", mainUser);
            model.addObject("error",Errors.ADDCATEGORY.getMessage());
            model.setViewName("home/Home");
        }
        return model;
    }

}
