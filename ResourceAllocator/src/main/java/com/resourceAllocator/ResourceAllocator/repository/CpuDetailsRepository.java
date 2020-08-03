package com.resourceAllocator.ResourceAllocator.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.resourceAllocator.ResourceAllocator.entity.CpuDetailsEntity;


/**
 * @author USHANANDHINI K
 *
 */
@Repository
public interface CpuDetailsRepository extends JpaRepository<CpuDetailsEntity,Integer> {

	List<CpuDetailsEntity> findByServerId(int serverId);
	
}