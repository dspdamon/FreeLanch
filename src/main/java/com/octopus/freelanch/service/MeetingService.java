/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.octopus.freelanch.service;

import com.octopus.freelanch.entity.Meeting;
import com.octopus.freelanch.entity.User;
import com.octopus.freelanch.repository.MeetingRepository;
import com.octopus.freelanch.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author yorg
 */
@Service
@RestController()
@RequestMapping("/meeting")
public class MeetingService {

   @Autowired
   MeetingRepository meetingRepo;
   
   @Autowired
   UserRepository userRepo;

   @RequestMapping("/create")
   public String create(@RequestParam("hostAccount") String hostAccount, @RequestParam(value = "info", required = false) String info) {
      User host = userRepo.findByAccount(hostAccount);
      
      if(host == null) {
         return "user does not exist";
      }
      
      Meeting meeting = Meeting.create(host);
      
//      host.hostedMeeting.add(meeting);
      
      if(info != null) {
         meeting.setInfo(info);
      }
      return meetingRepo.save(meeting).toJSON();
   }
   
   @RequestMapping("/join")
   public String join(@RequestParam("guestAccount") String guestAccount, @RequestParam("key") String key) {
      User guest = userRepo.findByAccount(guestAccount);
      
      if(guest == null) {
         return "user does not exist";
      }
      
      Meeting meeting = meetingRepo.findByKey(key);
      
      if(meeting == null) {
         return "meeting does not exist";
      }
      
      meeting.setGuest(guest);
      meetingRepo.save(meeting);
      
      return meeting.toJSON();
   }
   
   public Meeting findByKey(String key) {
      return meetingRepo.findByKey(key);
   }
}
