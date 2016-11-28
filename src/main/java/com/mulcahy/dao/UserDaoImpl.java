package com.mulcahy.dao;

import com.mulcahy.model.Category;
import com.mulcahy.model.Gif;
import com.mulcahy.model.User;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Chris on 11/14/2016.
 */
@Repository
public class UserDaoImpl implements UserDao {


    private SessionFactory sessionFactory;

    private int userId;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Override
    public boolean checkForUser(String name, String password) throws SQLException {
        Session session = this.sessionFactory.openSession();
        List<User> user = session.createQuery("from User u where u.name ='" + name + "' and u.password='" + password + "'").list();
        if (user.isEmpty()) {
            session.close();
            return false;
        }
        session.close();
        return true;

    }

    @Override
    public User initUser(String name, String password) throws SQLException {
        Session session = this.sessionFactory.openSession();
        List<User> user = session.createQuery("from User u where u.name ='" + name + "' and u.password='" + password + "'").list();
        Hibernate.initialize(user.get(0).getCategory());
        session.close();
        return user.get(0);
    }

    @Override
    public boolean uploadImage(User user, CommonsMultipartFile file) throws SQLException {
        Session session = sessionFactory.openSession();
        int id = user.getId();
        session.beginTransaction();
        User u = (User) session.get(User.class, id);
        u.setImage(file.getBytes());
        u.setName("Chris");
        session.saveOrUpdate(u);
        session.getTransaction().commit();
        session.close();
        return true;


    }

    @Override
    public boolean addCategory(User u, Category c) throws Exception {
        Session session=this.sessionFactory.openSession();
        try {
            c.setUserId(u.getId());
            session.beginTransaction();
            u.getCategory().add(c);
            session.saveOrUpdate(u);
            Hibernate.initialize(u.getCategory());
            session.getTransaction().commit();
            session.close();
            return true;
        }catch(Exception e){
            e.printStackTrace();
        }
        session.close();
        return false;
    }

    @Override
    public Category getCategoryById(int id) throws Exception {
        Session session=this.sessionFactory.openSession();
        try{
            Category c=(Category) session.get(Category.class,id);
            Hibernate.initialize(c.getGif());
            session.close();
            return c;
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(session.isOpen()){
                session.close();
            }
        }
        return null;
    }

    @Override
    public boolean uploadGif(String id, CommonsMultipartFile file, String gifName) throws Exception {
        Gif gif=new Gif();
        gif.setUserId(Integer.parseInt(id));
        gif.setName(gifName);
        gif.setImageData(file.getBytes());

        Session session= this.sessionFactory.openSession();
        try {
            if(gif.getName().equals(""))return false;
            session.beginTransaction();
            session.saveOrUpdate(gif);
            Category c =(Category) session.get(Category.class,Integer.parseInt(id));
            c.getGif().add(gif);
            session.saveOrUpdate(c);
            Hibernate.initialize(c);
            session.getTransaction().commit();
            session.close();
            return true;
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(session.isOpen()){
                session.close();
            }
        }
        return false;
    }

    @Override
    public byte[] getImage(int id) throws Exception {
        Session session = this.sessionFactory.openSession();
        Gif gif = (Gif) session.get(Gif.class,id);
        session.close();
        return gif.getImageData();
    }
}