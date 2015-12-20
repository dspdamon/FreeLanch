/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.octopus.freelanch.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

/**
 *
 * @author yorg
 */
@NodeEntity
public class User {
   @GraphId
   public Long id;
   
   @Index(unique = true)
   private String account;
   private String password;
   private String createDate;
   
   
   @Relationship(type = "HOSTED", direction=Relationship.OUTGOING)
   public Set<Meeting> hostedMeeting;
   
   @Relationship(type = "JOINED", direction=Relationship.OUTGOING)
   public Set<Meeting> joinedMeeting;
   
   @Relationship(type = "CHECKED", direction=Relationship.OUTGOING)
   public Set<Meeting> checkedMeeting;
   
   //Basic Info
   private String nickname;
   private String gender;
   private String avatar;
   private String school;
   private String sid;
   private String major;
   private String birthday;
   private String wechat;
   private int exp;
   private int vip;
   private String[] hobbies;
   private String[] tags;
//   private Map behavior;
   
   
   //Personal Info
   private int credit;
   private int rank;
   private int moneyGold;
   private int moneySilver;
   
    public static User create(String account, String password){
      User user = new User();
      user.account = account;
      user.setPassword(password);
      user.createDate = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
      
      
      user.setGender("");
      user.setAvatar("");
      user.setSchool("");
      user.setSid("");
      user.setMajor("");
      user.setBirthday("");
      user.setWechat("");
      user.setExp(0);
      user.setVip(0);
      user.setHobbies(new JSONArray());
      user.setTags(new JSONArray());
      

      user.setCredit(0);
      user.setRank(0);
      user.setMoneyGold(0);
      user.setMoneySilver(0);
      
      user.hostedMeeting = new HashSet();
      user.joinedMeeting = new HashSet();
      user.checkedMeeting = new HashSet();
      
      
      return user;
   }
  
   
   /**
    * @return the id
    */
   public Long getId() {
      return id;
   }

   /**
    * @return the account
    */
   public String getAccount() {
      return account;
   }

   /**
    * @return the password
    */
   public String getPassword() {
      return password;
   }

   /**
    * @param password the password to set
    */
   public void setPassword(String password) {
      this.password = password;
   }

   /**
    * @return the school
    */
   public String getSchool() {
      return school;
   }

   /**
    * @param school the school to set
    */
   public void setSchool(String school) {
      this.school = school;
   }

   
   public String toJSONBasicInfo(){
      JSONObject obj = new JSONObject();
      try{
         obj.put("signupDate", getCreateDate());
         obj.put("gender", getGender());
         obj.put("avatar",getAvatar());
         obj.put("school", getSchool());
         obj.put("sid", getSid());
//         obj.put("major", getMajor());
         obj.put("birthday", getBirthday());
         obj.put("wechat",getWechat());
         obj.put("expLevel",getExp());
         obj.put("vipLevel",getVip());
         obj.put("hobbies", getHobbies());
         obj.put("tags",getTags());
         
      } catch (Exception e){
         e.printStackTrace();
         return null;
      }
      
      return obj.toString();
   }
   
   public void setBasicInfoFromJSON(String info){
      try{
         JSONObject obj = new JSONObject(info);
         this.setGender(obj.getString("gender"));
         this.setAvatar((obj.getString("avatar")));
         this.setSchool(obj.getString("school"));
         this.setSid(obj.getString("sid"));
//         this.setMajor(obj.getString("major"));
         this.setBirthday(obj.getString("birthday"));
         this.setWechat(obj.getString("wechat"));
         this.setExp(obj.getInt("expLevel"));
         this.setVip(obj.getInt("vipLevel"));
         this.setHobbies(obj.getJSONArray("hobbies"));
         this.setTags(obj.getJSONArray("tags"));
         
      } catch (Exception e){
         e.printStackTrace();
      }
   }

   public String toJSONPersonalInfo() {
      JSONObject obj = new JSONObject();
      String basicInfo = this.toJSONBasicInfo();
      try{
         obj = new JSONObject(basicInfo);
         obj.put("aacount",getAccount());
         obj.put("password", getPassword());
         obj.put("credit",getCredit());
         obj.put("rank",getRank());
         obj.put("moneyGold", getMoneyGold());
         obj.put("moneySilver", getMoneySilver());
//         obj.put("hostedMatchKeys",getHostedMatchKeys());
//         obj.put("joinedMatchKeys",getJoinedMatchKeys());
//         obj.put("openedMatchKeys",getOpenedMatchKeys());
      } catch (Exception e) {
         e.printStackTrace();
      }
      return obj.toString();
   }
   
   /**
    * @return the money
    */
   public int getMoneyGold() {
      return this.moneyGold;
   }

   /**
    * @param money the money to set
    */
   public void setMoneyGold(int money) {
      this.moneyGold = money;
   }

   /**
    * @return the gender
    */
   public String getGender() {
      return gender;
   }

   /**
    * @param gender the gender to set
    */
   public void setGender(String gender) {
      this.gender = gender;
   }

   /**
    * @return the sid
    */
   public String getSid() {
      return sid;
   }

   /**
    * @param sid the sid to set
    */
   public void setSid(String sid) {
      this.sid = sid;
   }

   /**
    * @return the major
    */
   public String getMajor() {
      return major;
   }

   /**
    * @param major the major to set
    */
   public void setMajor(String major) {
      this.major = major;
   }

   /**
    * @return the birthday
    */
   public String getBirthday() {
      return birthday;
   }

   /**
    * @param birthday the birthday to set
    */
   public void setBirthday(String birthday) {
      this.birthday = birthday;
   }

   

   /**
    * @return the avatar
    */
   public String getAvatar() {
      return avatar;
   }

   /**
    * @param avatar the avatar to set
    */
   public void setAvatar(String avatar) {
      this.avatar = avatar;
   }

   /**
    * @return the key
    */
   public String getCreateDate() {
      return createDate;
   }

   /**
    * @return the nickname
    */
   public String getNickname() {
      return nickname;
   }

   /**
    * @param nickname the nickname to set
    */
   public void setNickname(String nickname) {
      this.nickname = nickname;
   }

   /**
    * @return the wechat
    */
   public String getWechat() {
      return wechat;
   }

   /**
    * @param wechat the wechat to set
    */
   public void setWechat(String wechat) {
      this.wechat = wechat;
   }

   /**
    * @return the exp
    */
   public int getExp() {
      return exp;
   }

   /**
    * @param exp the exp to set
    */
   public void setExp(int exp) {
      this.exp = exp;
   }

   /**
    * @return the vip
    */
   public int getVip() {
      return vip;
   }

   /**
    * @param vip the vip to set
    */
   public void setVip(int vip) {
      this.vip = vip;
   }

   /**
    * @return the hobbies
    */
   public String[] getHobbies() {
      return hobbies;
   }

   /**
    * @param hobbies the hobbies to set
    */
   public void setHobbies(JSONArray hobbies) {
      this.hobbies = new String[hobbies.length()];
      try{
         for(int i=0;i<hobbies.length();i++) {
            this.hobbies[i] = hobbies.getString(i);
         }
      } catch(Exception e) {
         e.printStackTrace();
      }
   }

   /**
    * @return the tags
    */
   public String[] getTags() {
      return tags;
   }

   /**
    * @param tags the tags to set
    */
   public void setTags(JSONArray tags) {
      this.tags = new String[tags.length()];
      try{
         for(int i=0;i<tags.length();i++) {
            this.tags[i] = tags.getString(i);
         }
      } catch(Exception e) {
         e.printStackTrace();
      }
   }

   /**
    * @return the credit
    */
   public int getCredit() {
      return credit;
   }

   /**
    * @param credit the credit to set
    */
   public void setCredit(int credit) {
      this.credit = credit;
   }

   /**
    * @return the rank
    */
   public int getRank() {
      return rank;
   }

   /**
    * @param rank the rank to set
    */
   public void setRank(int rank) {
      this.rank = rank;
   }

   /**
    * @return the moneySilver
    */
   public int getMoneySilver() {
      return moneySilver;
   }

   /**
    * @param moneySilver the moneySilver to set
    */
   public void setMoneySilver(int moneySilver) {
      this.moneySilver = moneySilver;
   }

//   /**
//    * @return the hostedMatchKeys
//    */
//   public JSONArray getHostedMatchKeys() {
//      JSONArray array = new JSONArray();
//      try{
//         for(int i=0;i<hostedMatchKeys.length;i++) {
//            array.put(hostedMatchKeys[i]);
//         }
//      } catch(Exception e) {
//         e.printStackTrace();
//      }
//      return array;
//   }

//   /**
//    * @param hostedMatchKeys the hostedMatchKeys to set
//    */
//   public void setHostedMatchKeys(JSONArray hostedMatchKeys) {
//      this.hostedMatchKeys = new String[hostedMatchKeys.length()];
//      try{
//         for(int i=0;i<hostedMatchKeys.length();i++) {
//            this.hobbies[i] = hostedMatchKeys.getString(i);
//         }
//      } catch(Exception e) {
//         e.printStackTrace();
//      }
//   }
//
//   /**
//    * @return the joinedMatchKeys
//    */
//   public JSONArray getJoinedMatchKeys() {
//      JSONArray array = new JSONArray();
//      try{
//         for(int i=0;i<joinedMatchKeys.length;i++) {
//            array.put(joinedMatchKeys[i]);
//         }
//      } catch(Exception e) {
//         e.printStackTrace();
//      }
//      return array;
//   }
//
//   /**
//    * @param joinedMatchKeys the joinedMatchKeys to set
//    */
//   public void setJoinedMatchKeys(JSONArray joinedMatchKeys) {
//      this.joinedMatchKeys = new String[joinedMatchKeys.length()];
//      try{
//         for(int i=0;i<joinedMatchKeys.length();i++) {
//            this.hobbies[i] = joinedMatchKeys.getString(i);
//         }
//      } catch(Exception e) {
//         e.printStackTrace();
//      }
//   }
//
//   /**
//    * @return the openedMatchKeys
//    */
//   public JSONArray getOpenedMatchKeys() {
//      JSONArray array = new JSONArray();
//      try{
//         for(int i=0;i<openedMatchKeys.length;i++) {
//            array.put(openedMatchKeys[i]);
//         }
//      } catch(Exception e) {
//         e.printStackTrace();
//      }
//      return array;
//   }
//
//   /**
//    * @param openedMatchKeys the openedMatchKeys to set
//    */
//   public void setOpenedMatchKeys(JSONArray openedMatchKeys) {
//      this.openedMatchKeys = new String[openedMatchKeys.length()];
//      try{
//         for(int i=0;i<openedMatchKeys.length();i++) {
//            this.hobbies[i] = openedMatchKeys.getString(i);
//         }
//      } catch(Exception e) {
//         e.printStackTrace();
//      }
//   }
//   
//   public void appendOpenedMatchKeys(String key) {
//      List<String> ret = new ArrayList();
//      for(int i=0; i< this.openedMatchKeys.length;i++){
//         ret.add(this.openedMatchKeys[i]);
//      }
//      ret.add(key);
//   }
}
