/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.loginbackend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Base64;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Matthew
 */
@SpringBootApplication
@RestController
@CrossOrigin
public class ClientController {

    private DatabaseClient client;
    public ClientController() throws IOException{
        client = new DatabaseClient("LoginSampleOperator", "OperatorPassword");// TODO Add username and password
        }
    
        @PostMapping("/auth")
        @ResponseBody
        public boolean auth(@RequestBody UserInfo userInfo){
            boolean userAuthenticated = client.authenticateUser(userInfo.getUsername(), userInfo.getPassword());
            return userAuthenticated;
        }
        @GetMapping("/users")
        @ResponseBody
        public String getUserImage(@RequestParam(defaultValue = "", name = "username") String username ){
             return client.getImage(username);
        }
        
        @PostMapping("/created")
        @ResponseBody
        public boolean createUser(@RequestBody UserInfo userInfo){
            return client.createUser(userInfo.getUsername(), userInfo.getPassword());
        }
}
