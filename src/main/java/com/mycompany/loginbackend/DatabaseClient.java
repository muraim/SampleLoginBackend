package com.mycompany.loginbackend;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import org.bson.Document;

/**
 *
 * @author Matthew
 */
public class DatabaseClient {
        
    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> userInfo;
    
    public DatabaseClient(String connUser, String connPass){
        databaseConnection(connUser, connPass);
    }
    public boolean authenticateUser(String username, String password){
        Document testUser = userInfo.find(new Document("username", username)).first();//query database for data under username, finds first result
        if (testUser != null)//check if username found in database
        {
            String passTest = testUser.getString("password");
            return password.equals(passTest);//true if password matches, false if no match
        }
        else
            return false;//Username not found
    }
    
    public boolean createUser(String username, String password){
       Document checkUser = userInfo.find(new Document("username", username)).first();//check if username is taken
       if (checkUser != null)//Username is already taken
           return false;
       else
       {
           Map<String, Object> userFields = new HashMap<>(); 
           userFields.put("username", username);
           userFields.put("password", password);
           try
           {
               userInfo.insertOne(new Document(userFields));
               return true;
           }
           catch(MongoException e)
           { 
               return false;
           }
       }
    }
    
    private void databaseConnection(String connUser, String connPass){
        mongoClient = MongoClients.create("mongodb+srv://"+ connUser + ":" + connPass + "@cluster0.ldu8s.mongodb.net/LoginSample?retryWrites=true&w=majority");
        database = mongoClient.getDatabase("LoginSample");
        userInfo = database.getCollection("UserInfo");
    } 
    
    public byte[] getImageBytes(String username){
        Document thisUser = userInfo.find(new Document("username", username)).first();
        byte[] image = Base64.getDecoder().decode(thisUser.getString("image"));
        return image;
    }
    public String getImage(String username){
        Document thisUser = userInfo.find(new Document("username", username)).first();
        String image = thisUser.getString("image");
        return image;
    }
    
    public String getPassword(String username){
        Document thisUser = userInfo.find(new Document("username", username)).first();
        String password = thisUser.getString("password");
        return password;
    }

}
