package com.lac.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.lac.exception.UserRunTimeException;
import com.lac.po.User;

public class UserDao {
	private static final Map<String,User> users = new HashMap<String, User>();  
    private static UserDao dao = null;  
    public UserDao(){  
        users.put("admin", new User("admin", "admin", "超级管理员"));  
          
    }  
    public static UserDao newInstence(){  
        if (null==dao)dao = new UserDao();  
        return dao;  
    }  
  
    public void add(User user) throws UserRunTimeException{  
        System.out.println("---------add--------"+user);  
        System.out.println("---------user.getUsername()--------"+user.getUsername());  
        if (users.containsKey(user.getUsername())) {  
             throw new UserRunTimeException("用户已存在");  
        }  
        users.put(user.getUsername(), user);  
    }  
    public void delete(String username){  
        System.out.println("---------delete--------"+username);  
        users.remove(username);  
    }  
    public List<User> list(){  
        System.out.println("---------list--------"+users);  
        Set<String> keys = users.keySet();  
        List<User> list = new ArrayList<User>();  
        for (String key : keys) {  
            list.add(users.get(key));  
        }  
        return list;  
    }  
    public User login(String username,String password) throws UserRunTimeException{  
        System.out.println("---------login--------"+username+password);  
        if (!users.containsKey(username)) {  
             throw new UserRunTimeException("用户不存在");  
        }  
        User u = users.get(username);  
        if (!password.equals(u.getPassword())) {  
             throw new UserRunTimeException("用户密码不正确");  
        }  
        return u;  
    }
    public User loadByUsername(String username) {  
        return users.get(username);  
    }    
}
