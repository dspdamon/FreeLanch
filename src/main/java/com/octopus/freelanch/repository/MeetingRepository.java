/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.octopus.freelanch.repository;

import com.octopus.freelanch.entity.Meeting;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 *
 * @author yorg
 */
@RepositoryRestResource(collectionResourceRel = "meeting", path = "meeting")
public interface MeetingRepository extends GraphRepository<Meeting>{
   public Meeting findByKey(String key);
}
