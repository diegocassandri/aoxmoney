package com.sistema.security;

import java.util.Date;
import javax.servlet.http.HttpSession;

import com.sistema.model.Staff;


public class UserForm {

    private String sessionId;
    private Staff staff;
    private Date sessionCreatedTime;
    private String userMachineName;
    private String userIpAdress;
    private HttpSession session;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Date getSessionCreatedTime() {
        return sessionCreatedTime;
    }

    public void setSessionCreatedTime(Date sessionCreatedTime) {
        this.sessionCreatedTime = sessionCreatedTime;
    }

    public String getUserMachineName() {
        return userMachineName;
    }

    public void setUserMachineName(String userMachineName) {
        this.userMachineName = userMachineName;
    }

    public String getUserIpAdress() {
        return userIpAdress;
    }

    public void setUserIpAdress(String userIpAdress) {
        this.userIpAdress = userIpAdress;
    }

    public HttpSession getSession() {
        return session;
    }

    public void setSession(HttpSession session) {
        this.session = session;
    }

}
