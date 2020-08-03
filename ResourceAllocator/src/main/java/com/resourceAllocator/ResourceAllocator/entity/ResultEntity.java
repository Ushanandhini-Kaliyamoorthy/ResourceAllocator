package com.resourceAllocator.ResourceAllocator.entity;

import java.util.Map;

public class ResultEntity{
	
	private String region;
	
	private String totalCost;
	
	private Map<String,Integer> server;
	
	public ResultEntity(String region, String totalCost,Map<String, Integer> server) {
		super();
		this.region = region;
		this.totalCost = totalCost;
		this.server = server;
	}
	
	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}
	
	public String getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(String totalCost) {
		this.totalCost = totalCost;
	}

	public Map<String, Integer> getServer() {
		return server;
	}

	public void setServer(Map<String, Integer> server) {
		this.server = server;
	}
}