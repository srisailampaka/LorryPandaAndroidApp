package com.lorrypanda.models;

import java.util.HashMap;
import java.util.Map;



public class UserProfileData {

private Integer code;
private String message;
private String name;
private String email;
private String mobile;
private String userId;
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

/**
* 
* @return
* The code
*/
public Integer getCode() {
return code;
}

/**
* 
* @param code
* The code
*/
public void setCode(Integer code) {
this.code = code;
}

/**
* 
* @return
* The message
*/
public String getMessage() {
return message;
}

/**
* 
* @param message
* The message
*/
public void setMessage(String message) {
this.message = message;
}

/**
* 
* @return
* The name
*/
public String getName() {
return name;
}

/**
* 
* @param name
* The name
*/
public void setName(String name) {
this.name = name;
}

/**
* 
* @return
* The email
*/
public String getEmail() {
return email;
}

/**
* 
* @param email
* The email
*/
public void setEmail(String email) {
this.email = email;
}

/**
* 
* @return
* The mobile
*/
public String getMobile() {
return mobile;
}

/**
* 
* @param mobile
* The mobile
*/
public void setMobile(String mobile) {
this.mobile = mobile;
}

/**
* 
* @return
* The userId
*/
public String getUserId() {
return userId;
}

/**
* 
* @param userId
* The user_id
*/
public void setUserId(String userId) {
this.userId = userId;
}

public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

}