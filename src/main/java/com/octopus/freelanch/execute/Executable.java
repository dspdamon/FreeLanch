/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.octopus.freelanch.execute;

import com.octopus.freelanch.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 *
 * @author yorg
 */
@Configuration
@Import(MyConfiguration.class)
public class Executable extends WebMvcConfigurerAdapter {

   @Autowired
   UserService userService;
   
   @Autowired
   MeetingService meetingService;
   
//   @Autowired
//   HostedService hostedService;
   
   public Executable() {
      AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MyConfiguration.class);
      this.userService = (UserService)ctx.getBean(UserService.class);
      this.meetingService = (MeetingService)ctx.getBean(MeetingService.class);
//      this.hostedService = (HostedService)ctx.getBean(HostedService.class);
   }
   
   
   public static void main(String[] args) {
      SpringApplication.run(Executable.class,args);
      
//      Executable exe = new Executable();
      
//      exe.userService.signup("zhangyuhan", "jisuanji");
//      exe.userService.signup("yuki", "jisuanji");
//      
//      exe.meetingService.create("zhangyuhan", null);
//      exe.meetingService.create("zhangyuhan", null);
//      exe.meetingService.create("zhangyuhan", null);
      
//      
//      User user = exe.userService.findByAccount("zhangyuhan");
//      System.out.println(user);
//      System.out.println(user.hostedMeeting.size());
      
//      Meeting meeting = exe.meetingService.findByKey("zhangyuhan20151218165543");
//      System.out.println(meeting.getHost().getAccount());
   }
   

}
