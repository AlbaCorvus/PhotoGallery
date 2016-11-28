package com.mulcahy.service;

import com.mulcahy.model.Category;
import com.mulcahy.model.User;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.sql.SQLException;

/**
 * Created by Chris on 11/16/2016.
 */
public interface UserService {

    public boolean checkForUser(String name , String password) throws SQLException;

    public User initUser(String name, String password) throws SQLException;

    public boolean uploadImage(User user, CommonsMultipartFile file) throws SQLException;

    public boolean addCategory(User u,Category c) throws Exception;

    public Category getCategoryById(int id)throws Exception;

    public boolean uploadGif(String id,CommonsMultipartFile file,String gifName) throws Exception;

    public byte[] getImage(int id) throws Exception;

}
