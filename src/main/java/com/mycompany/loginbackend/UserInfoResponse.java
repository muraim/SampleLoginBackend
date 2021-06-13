package com.mycompany.loginbackend;
/**
 * 
 * @author Matthew
 *Deprecated
 */
public class UserInfoResponse {
    private boolean userAuthenticated;
    
    public UserInfoResponse(boolean userAuthenticated){
        this.userAuthenticated = userAuthenticated;
    }

    public boolean isUserAuthenticated() {
        return userAuthenticated;
    }

    public void setUserAuthenticated(boolean userAuthenticated) {
        this.userAuthenticated = userAuthenticated;
    }
}
