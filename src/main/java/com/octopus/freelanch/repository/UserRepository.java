/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.octopus.freelanch.repository;

import com.octopus.freelanch.entity.User;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 *
 * @author yorg
 */
@RepositoryRestResource(collectionResourceRel = "user", path = "user")
public interface UserRepository extends GraphRepository<User>{
   User findByAccount(@Param("account") String account);
}
