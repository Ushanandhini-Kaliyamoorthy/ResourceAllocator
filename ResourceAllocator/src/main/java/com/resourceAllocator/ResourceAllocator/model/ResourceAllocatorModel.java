package com.resourceAllocator.ResourceAllocator.model;

/**
 * @author USHANANDHINI K
 *
 */
public class ResourceAllocatorModel {
	
	private int cpuCount;
	
	private float serverCost;
	
	private int hours;
	
	public int getCpuCount() {
		return cpuCount;
	}

	public void setCpuCount(int cpuCount) {
		this.cpuCount = cpuCount;
	}

	public float getServerCost() {
		return serverCost;
	}

	public void setServerCost(float serverCost) {
		this.serverCost = serverCost;
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}
}