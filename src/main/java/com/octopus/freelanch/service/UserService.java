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
@RestController
@RequestMapping("/user")
public class UserService {
   @Autowired
   UserRepository userRepo;
   
   @Autowired
   MeetingRepository meetingRepo;
   
   
   @RequestMapping("/signup")
   public String signup(@RequestParam(value = "account") String account, @RequestParam(value = "password") String password) {
      User user = userRepo.findByAccount(account);
      
      if (user != null) {
         return "user exists";
      }
      
      user = User.create(account, password);
      return userRepo.save(user).toJSONPersonalInfo();

   }
   
   @RequestMapping("/login")
   public String login(@RequestParam(value = "account") String account, @RequestParam(value = "password") String password) {
      User user = userRepo.findByAccount(account);

      if (user == null) {
         return "user does not exist";
      }

      if (!user.getPassword().equals(password)) {
         return "password not correct";
      }

      return user.toJSONPersonalInfo();
   }

   
   public User findByAccount(String account) {
      return userRepo.findByAccount(account);
   }
//   
//   public void hostMeeting(String account) {
//      User user = userRepo.findByAccount(account);
//      user.
//      Meeting meeting = meetingRepo.findOne(meeingID); 
//   }
   
   
   
   
}
