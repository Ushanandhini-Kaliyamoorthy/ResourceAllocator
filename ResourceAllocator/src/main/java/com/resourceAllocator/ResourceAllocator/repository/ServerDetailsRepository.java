package com.resourceAllocator.ResourceAllocator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.resourceAllocator.ResourceAllocator.entity.ServerDetailsEntity;

/**
 * @author USHANANDHINI K
 *
 */
@Repository
public interface ServerDetailsRepository extends JpaRepository<ServerDetailsEntity,Integer> {
	
}