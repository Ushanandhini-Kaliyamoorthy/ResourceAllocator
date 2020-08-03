package com.resourceAllocator.ResourceAllocator.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Stack;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resourceAllocator.ResourceAllocator.entity.CpuDetailsEntity;
import com.resourceAllocator.ResourceAllocator.entity.ResultEntity;
import com.resourceAllocator.ResourceAllocator.entity.ServerDetailsEntity;
import com.resourceAllocator.ResourceAllocator.model.ResourceAllocatorModel;
import com.resourceAllocator.ResourceAllocator.repository.CpuDetailsRepository;
import com.resourceAllocator.ResourceAllocator.repository.ServerDetailsRepository;
import com.resourceAllocator.ResourceAllocator.service.ResourceAllocatorService;

/**
 * @author USHANANDHINI K
 *
 */
@Service
public class ResourceAllocatorServiceImpl implements ResourceAllocatorService{

	@Autowired
	ServerDetailsRepository serverDetailsRepository;
	
	@Autowired
	CpuDetailsRepository cpuDetailsRepository;
	
	/* serverCapacityMap used to map serverCapacity to serverType */
	private Map<String, Integer> serverCapacityMap ;
	
	/* serverCapacityMap used to map serverType to serverCapacity*/
	private Map<Integer, String> serverTypeMap ;
	
	/* The constant DECIMAL_FORMAT */
	private static final String DECIMAL_FORMAT = "#.00";
	
	/* The constant MESSAGE*/
	private static final String MESSAGE = "Could not allocate for your requirement and minimum cost $";
	
	/* The constant DOLLAR*/
	private static final String DOLLAR = "$";
	
	/* The constant ZERO*/
	private static final int ZERO = 0;
	
	/* The constant INDEX*/
	private static final int INDEX = 0;
	
	@Override
	public List<ResultEntity> getServerDetails(ResourceAllocatorModel resourceAllocatorModel) {
		List<ServerDetailsEntity> serverEntity = serverDetailsRepository.findAll();
		List<ResultEntity> listEntity = new ArrayList<ResultEntity>();
		DecimalFormat dFormat = new DecimalFormat(DECIMAL_FORMAT);
		final float[] serverCost = new float[1];
		serverEntity.forEach(serverAction -> {
			List<CpuDetailsEntity> cpuEntity = cpuDetailsRepository.findByServerId(serverAction.getServerId());
			Map<String,Integer> resultMap = new HashMap<>();
			cpuEntity.forEach(cpuAction -> {
				if(resourceAllocatorModel.getCpuCount() != ZERO &&  resourceAllocatorModel.getHours() != ZERO) {
					Stack<Integer> cpuStack= new Stack<Integer>();
					Stack<Float> priceStack = new Stack<Float>();
					getSortedMap(cpuEntity, cpuStack, priceStack);
					int remCpuCount= resourceAllocatorModel.getCpuCount();
					int totalCpuCount = ZERO;
					float totalCost = ZERO;
					Map<Integer,Integer> countMap = new HashMap<>();
					while(remCpuCount > ZERO) {
						if((remCpuCount % cpuStack.peek()) != remCpuCount) {
							int typeCpuCount = (int)Math.abs(remCpuCount/(cpuStack.peek()));
							totalCpuCount += typeCpuCount*cpuStack.peek();
							float cost = priceStack.peek()*resourceAllocatorModel.getHours()*typeCpuCount*cpuStack.peek();
							totalCost = totalCost + cost;
							serverCost[INDEX] = totalCost;
							remCpuCount = resourceAllocatorModel.getCpuCount() - totalCpuCount;
							countMap.put(cpuStack.peek(), typeCpuCount);
						}
						priceStack.pop();
						cpuStack.pop();
					}
					countMap.forEach((type,count)->{
						resultMap.put(serverTypeMap.get(type), count);
					});
				} else if (resourceAllocatorModel.getCpuCount() == ZERO) {
					float perHourCost = resourceAllocatorModel.getServerCost() / resourceAllocatorModel.getHours();
					Stack<Integer> cpuStack= new Stack<Integer>();
					Stack<Float> priceStack = new Stack<Float>();
					getSortedMap(cpuEntity,cpuStack,priceStack);
					Map<Integer,Integer> countMap = new HashMap<>();
					float remainingCost = perHourCost;
					while(remainingCost > ZERO && (!priceStack.isEmpty() && !cpuStack.isEmpty())) {
						if(priceStack.peek()*cpuStack.peek()<remainingCost) {
							int oneValue = (int)Math.abs(remainingCost/(priceStack.peek()*cpuStack.peek()));
							remainingCost = remainingCost-(oneValue*(priceStack.peek()*cpuStack.peek()));
							countMap.put(cpuStack.peek(), oneValue);
						}
						priceStack.pop();
						cpuStack.pop();
					}
					float totalCost = resourceAllocatorModel.getServerCost()-(remainingCost*resourceAllocatorModel.getHours());
					serverCost[INDEX] = totalCost;
					countMap.forEach((type,count)->{
						resultMap.put(serverTypeMap.get(type), count);
					});
				}
			});
			String costSpecification;
			if(serverCost[INDEX] > resourceAllocatorModel.getServerCost() && resourceAllocatorModel.getServerCost() != ZERO) {
				costSpecification = MESSAGE + dFormat.format(serverCost[INDEX]);
			} else {
				costSpecification = DOLLAR + dFormat.format(serverCost[INDEX]);
			}
			ResultEntity resultEntity = new ResultEntity(serverAction.getRegion(), costSpecification, resultMap);
			listEntity.add(resultEntity);
		});
		return listEntity;
	}
	
	/*
	 * getSortedMap used to sort the serveType based on serverCost
	 */
	public void getSortedMap(List<CpuDetailsEntity> cpuEntity,Stack<Integer> cpuStack, Stack<Float> priceStack) {
		Map<String, Float> costPerHourMap = new HashMap<>();
		serverTypeMap = new LinkedHashMap<Integer,String>();
		serverCapacityMap = new LinkedHashMap<String,Integer>();
		setServerType(cpuEntity, serverCapacityMap, serverTypeMap);
		cpuEntity.forEach(action -> {
			int cpuCount = action.getServerCapacity();
			costPerHourMap.put(action.getServerType(), action.getServerCostPerHour()/cpuCount);
		});
		Map<String, Float> costCpuPerHrSortedMap = costPerHourMap.entrySet().stream()
				.sorted(Entry.comparingByValue(Comparator.reverseOrder()))
				.collect(Collectors.toMap(Entry::getKey, Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
		costCpuPerHrSortedMap.forEach((type, cost) ->{
			cpuStack.push(serverCapacityMap.get(type));
			priceStack.push(cost);
		});
	}
	
	/*
	 * setServerType used to map the serverType and serverCapacity
	 */
	public void setServerType(List<CpuDetailsEntity> cpuEntity, Map<String, Integer> serverCapacity, 
			Map<Integer, String> serverType) {
		cpuEntity.forEach(action -> {
			if(Objects.nonNull(action.getServerType()) && Objects.nonNull(action.getServerCapacity())) {
				serverCapacity.put(action.getServerType(), action.getServerCapacity());
				serverType.put(action.getServerCapacity(), action.getServerType());
			}
		});
	}
	
}