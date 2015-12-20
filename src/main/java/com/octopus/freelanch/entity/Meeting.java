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
public class Meeting {
   @GraphId private Long id;
   
   @Index(unique = true)
   private String key;
   private String createDate;
   
   private String time;
   private String place;
   private String topic;
   
   @Relationship(type = "HOSTED", direction=Relationship.INCOMING)
   private User host;
   
   @Relationship(type = "JOINED", direction=Relationship.INCOMING)
   private User guest;
   
   @Relationship(type = "CHECKED", direction=Relationship.INCOMING)
   private Set<User> user;
   
   
   public static Meeting create(User host){
      Meeting meeting = new Meeting();
      meeting.setHost(host);
      meeting.createDate = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
      meeting.key = host.getAccount() + meeting.createDate;
      
      meeting.user = new HashSet();
      
      
      return meeting;
   }
   
   public static Meeting create(User host, String info){
      Meeting meeting = Meeting.create(host);
      meeting.setInfo(info);
      return meeting;
   }
   
   
   public String toJSON(){
      try{
         JSONObject obj = new JSONObject();
         obj.put("key", this.getKey());
         obj.put("host", this.getHost());
         if(this.getGuest() != null) obj.put("guest", this.getGuest());
         obj.put("time", this.getTime());
         obj.put("place", this.getPlace());
         obj.put("topic", this.getTopic());
         return obj.toString();
      }catch (Exception e){
         e.printStackTrace();
      }
      return null;
   }
   
   public Boolean setInfo(String info){
      try{
         JSONObject obj = new JSONObject(info);
         this.setTime(obj.getString("time"));
         this.setPlace(obj.getString("place"));
         this.setTopic(obj.getString("topic"));
      } catch (Exception e){
         e.printStackTrace();
      }
      return false;
   }
   
   /**
    * @return the id
    */
   public Long getId() {
      return id;
   }

   /**
    * @return the time
    */
   public String getTime() {
      return time;
   }

   /**
    * @param time the time to set
    */
   public void setTime(String time) {
      this.time = time;
   }

   /**
    * @return the place
    */
   public String getPlace() {
      return place;
   }

   /**
    * @param place the place to set
    */
   public void setPlace(String place) {
      this.place = place;
   }

   /**
    * @return the topic
    */
   public String getTopic() {
      return topic;
   }

   /**
    * @param topic the topic to set
    */
   public void setTopic(String topic) {
      this.topic = topic;
   }

   /**
    * @return the key
    */
   public String getKey() {
      return key;
   }

   /**
    * @return the createDate
    */
   public String getCreateDate() {
      return createDate;
   }

   /**
    * @return the host
    */
   public User getHost() {
      return host;
   }

   /**
    * @param host the host to set
    */
   public void setHost(User host) {
      this.host = host;
   }

   /**
    * @return the guest
    */
   public User getGuest() {
      return guest;
   }

   /**
    * @param guest the guest to set
    */
   public void setGuest(User guest) {
      this.guest = guest;
   }
   
   
}
