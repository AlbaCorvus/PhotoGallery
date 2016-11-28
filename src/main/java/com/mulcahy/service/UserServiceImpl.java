package com.mulcahy.service;

import com.mulcahy.dao.UserDao;
import com.mulcahy.model.Category;
import com.mulcahy.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.sql.SQLException;

/**
 * Created by Chris on 11/16/2016.
 */

@Service
public class UserServiceImpl implements UserService{


    private UserDao userDao;

    public void setUserDao(UserDao userDao){
        this.userDao=userDao;
    }

    @Override
    @Transactional
    public boolean checkForUser(String name, String password) throws SQLException {
        return userDao.checkForUser(name,password);
    }

    @Override
    @Transactional
    public User initUser(String name, String password) throws SQLException {
        return userDao.initUser(name,password);
    }

    @Override
    @Transactional
    public boolean uploadImage(User user, CommonsMultipartFile file) throws SQLException {
        return userDao.uploadImage(user,file);
    }

    @Override
    public boolean addCategory(User u, Category c) throws Exception {
        return userDao.addCategory(u,c);
    }

    @Override
    public Category getCategoryById(int id) throws Exception {
        return userDao.getCategoryById(id);
    }

    @Override
    public boolean uploadGif(String id, CommonsMultipartFile file, String gifName) throws Exception {
        return userDao.uploadGif(id,file,gifName);
    }

    @Override
    public byte[] getImage(int id) throws Exception {
        return userDao.getImage(id);
    }
}
