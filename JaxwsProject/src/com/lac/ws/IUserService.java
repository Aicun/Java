package com.lac.ws;

import java.util.List;

import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import com.lac.exception.*;
import com.lac.po.User;

@WebService(name = "IUserService", targetNamespace = "http://ws.lac.com") 
@HandlerChain(file="handler-chain.xml")
public interface IUserService {
	/** 
     *  
     * @param user 
     * @throws UserRunTimeException_Exception 
     */  
    @WebMethod  
    @RequestWrapper(localName = "add", targetNamespace = "http://ws.lac.com")  
    @ResponseWrapper(localName = "addResponse", targetNamespace = "http://ws.lac.com")  
    public void add(  
        @WebParam(name = "user", targetNamespace = "")  
        User user)  
        throws UserRunTimeException  
    ;  
  
    /** 
     *  
     * @param username 
     */  
    @WebMethod  
    @RequestWrapper(localName = "delete", targetNamespace = "http://ws.lac.com")  
    @ResponseWrapper(localName = "deleteResponse", targetNamespace = "http://ws.lac.com")  
    public void delete(  
        @WebParam(name = "username", targetNamespace = "")  
        String username)
        throws UserRunTimeException  ;  
  
    /** 
     *  
     * @return 
     *     returns java.util.List<cn.edu.zttc.service.User> 
     */  
    @WebMethod  
    @WebResult(name = "user", targetNamespace = "")  
    @RequestWrapper(localName = "list", targetNamespace = "http://ws.lac.com")  
    @ResponseWrapper(localName = "listResponse", targetNamespace = "http://ws.lac.com")  
    public List<User> list();  
  
    /** 
     *  
     * @param username 
     * @param password 
     * @return 
     *     returns cn.edu.zttc.service.User 
     * @throws UserRunTimeException_Exception 
     */  
    @WebMethod  
    @WebResult(name = "user", targetNamespace = "")  
    @RequestWrapper(localName = "login", targetNamespace = "http://ws.lac.com")  
    @ResponseWrapper(localName = "loginResponse", targetNamespace = "http://ws.lac.com")  
    public User login(  
        @WebParam(name = "username", targetNamespace = "")  
        String username,  
        @WebParam(name = "password", targetNamespace = "")  
        String password)  
        throws UserRunTimeException  
    ;  
}
