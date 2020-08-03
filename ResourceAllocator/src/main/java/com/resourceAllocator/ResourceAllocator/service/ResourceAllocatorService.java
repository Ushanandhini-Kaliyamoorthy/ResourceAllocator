package com.resourceAllocator.ResourceAllocator.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.resourceAllocator.ResourceAllocator.entity.ResultEntity;
import com.resourceAllocator.ResourceAllocator.model.ResourceAllocatorModel;

/**
 * @author USHANANDHINI K
 *
 */
@Service
public interface ResourceAllocatorService {
	
	/*
	 * getServerDetails used to fetch the CPU details with minimum cost
	 */
	List<ResultEntity> getServerDetails(ResourceAllocatorModel resourceAllocatorModel);
	
}