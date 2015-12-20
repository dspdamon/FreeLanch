/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.octopus.freelanch.execute;


import org.neo4j.ogm.session.SessionFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.server.Neo4jServer;
import org.springframework.data.neo4j.server.RemoteServer;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
/**
 *
 * @author yorg
 */
@EnableTransactionManagement
@Import(RepositoryRestMvcConfiguration.class)
@EnableScheduling
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.octopus.freelanch.service"})
@Configuration
@EnableNeo4jRepositories(basePackages = "com.octopus.freelanch.repository")
public class MyConfiguration extends Neo4jConfiguration{
   
    @Override
    public Neo4jServer neo4jServer() {
        return new RemoteServer("http://localhost:7474","neo4j","jisuanji111");
    }

    @Override
    public SessionFactory getSessionFactory() {
        // with domain entity base package(s)
        return new SessionFactory("com.octopus.freelanch.entity");
    }
    
}
