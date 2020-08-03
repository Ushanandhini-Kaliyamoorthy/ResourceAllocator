package com.resourceAllocator.ResourceAllocator;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.resourceAllocator.ResourceAllocator.controller.ResourceAllocatorController;
import com.resourceAllocator.ResourceAllocator.entity.ResultEntity;
import com.resourceAllocator.ResourceAllocator.model.ResourceAllocatorModel;
import com.resourceAllocator.ResourceAllocator.service.ResourceAllocatorService;

@RunWith(SpringRunner.class)
@SpringBootTest
class ResourceAllocatorApplicationTests {
	
	@Mock
	public ResourceAllocatorService resourceAllocatorServiceMock;
	
	@InjectMocks
	public ResourceAllocatorController resourceAllocatorController;
	
	@Test
	public void getServerByCountAndHours() {
		ResourceAllocatorModel resourceAllocatorModel = new ResourceAllocatorModel();
		resourceAllocatorModel.setCpuCount(115);
		resourceAllocatorModel.setHours(24);
		List<ResultEntity> listEntity = new ArrayList<ResultEntity>();
		Map<String, Integer> resultMap = new HashMap<String, Integer>();
		resultMap.put("large", 1);
		resultMap.put("4xlarge", 17);
		ResultEntity resultEntity = new ResultEntity("us-west", "$29", resultMap);
		listEntity.add(resultEntity);
		when(resourceAllocatorServiceMock.getServerDetails(resourceAllocatorModel)).thenReturn(listEntity);
		Assert.assertEquals(listEntity, resourceAllocatorController.getResourceDetails(resourceAllocatorModel).getServerDetails());
		Mockito.verify(resourceAllocatorServiceMock, times(1)).getServerDetails(resourceAllocatorModel);
	}
	
	@Test
	public void getServerByCountAndHoursAndCost() {
		ResourceAllocatorModel resourceAllocatorModel = new ResourceAllocatorModel();
		resourceAllocatorModel.setCpuCount(115);
		resourceAllocatorModel.setHours(24);
		resourceAllocatorModel.setServerCost(225);
		List<ResultEntity> listEntity = new ArrayList<ResultEntity>();
		Map<String, Integer> resultMap = new HashMap<String, Integer>();
		resultMap.put("large", 2);
		resultMap.put("4xlarge", 17);
		ResultEntity resultEntity = new ResultEntity("us-west", "$29", resultMap);
		listEntity.add(resultEntity);
		when(resourceAllocatorServiceMock.getServerDetails(resourceAllocatorModel)).thenReturn(listEntity);
		Assert.assertEquals(listEntity, resourceAllocatorController.getResourceDetails(resourceAllocatorModel).getServerDetails());
		Mockito.verify(resourceAllocatorServiceMock, times(1)).getServerDetails(resourceAllocatorModel);
	}
	
	@Test
	public void getServerByHoursAndCost() {
		ResourceAllocatorModel resourceAllocatorModel = new ResourceAllocatorModel();
		resourceAllocatorModel.setHours(24);
		resourceAllocatorModel.setServerCost(225);
		List<ResultEntity> listEntity = new ArrayList<ResultEntity>();
		Map<String, Integer> resultMap = new HashMap<String, Integer>();
		resultMap.put("large", 2);
		resultMap.put("4xlarge", 18);
		ResultEntity resultEntity = new ResultEntity("us-west", "$29", resultMap);
		listEntity.add(resultEntity);
		when(resourceAllocatorServiceMock.getServerDetails(resourceAllocatorModel)).thenReturn(listEntity);
		Assert.assertEquals(listEntity, resourceAllocatorController.getResourceDetails(resourceAllocatorModel).getServerDetails());
		Mockito.verify(resourceAllocatorServiceMock, times(1)).getServerDetails(resourceAllocatorModel);
	}

}
