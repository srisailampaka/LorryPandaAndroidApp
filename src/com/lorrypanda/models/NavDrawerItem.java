package com.lorrypanda.models;

public class NavDrawerItem {
  
  private String title;
  private int icon;
  private String subTitle;
  // boolean to set visiblity of the counter
  private boolean isCounterVisible = false;
  
  public NavDrawerItem(){}

  public NavDrawerItem(String title, int icon){
    this.title = title;
    this.icon = icon;
  }
  
  public NavDrawerItem(String title, int icon,String subTitle){
    this.title = title;
    this.icon = icon;
    this.subTitle = subTitle;
  }
  
  public String getTitle(){
    return this.title;
  }
  
  public int getIcon(){
    return this.icon;
  }
  
  
  
  public String getSubTitle() {
	return subTitle;
}

public void setSubTitle(String subTitle) {
	this.subTitle = subTitle;
}

public boolean getCounterVisibility(){
    return this.isCounterVisible;
  }
  
  public void setTitle(String title){
    this.title = title;
  }
  
  public void setIcon(int icon){
    this.icon = icon;
  }
  
 
  public void setCounterVisibility(boolean isCounterVisible){
    this.isCounterVisible = isCounterVisible;
  }
}
