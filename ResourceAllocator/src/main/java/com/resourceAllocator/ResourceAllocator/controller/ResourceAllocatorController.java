package com.resourceAllocator.ResourceAllocator.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.resourceAllocator.ResourceAllocator.entity.ResultEntity;
import com.resourceAllocator.ResourceAllocator.model.ResourceAllocatorModel;
import com.resourceAllocator.ResourceAllocator.model.ResultModel;
import com.resourceAllocator.ResourceAllocator.service.ResourceAllocatorService;

/**
 * @author USHANANDHINI K
 *
 */
@RestController
@RequestMapping(path = "/resource")
public class ResourceAllocatorController {
	
	@Autowired
	ResourceAllocatorService resourceAllocatorService;
	
	/**
	 * getResourceDetails used to get the server details for given requirement
	 * 
	 * @param resourceAllocatorModel holds input data
	 * @return resultModel
	 */
	@GetMapping(value= "/getResource")
	public ResultModel getResourceDetails(@RequestBody ResourceAllocatorModel resourceAllocatorModel) {
		ResultModel resultModel = new ResultModel();
		List<ResultEntity> entityList = resourceAllocatorService.getServerDetails(resourceAllocatorModel);
		Comparator<ResultEntity> compareByTotalCost = (ResultEntity b1, ResultEntity b2) -> b1.getTotalCost().compareTo( b2.getTotalCost());
		Collections.sort(entityList, compareByTotalCost);
		resultModel.setServerDetails(entityList);
		return  resultModel;
	}
}