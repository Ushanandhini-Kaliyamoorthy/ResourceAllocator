package com.resourceAllocator.ResourceAllocator.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

/**
 * @author USHANANDHINI K
 *
 */
@Entity
@Table(name="cpu_details")
public class CpuDetailsEntity{
	
	@Id
	@Column(name="cpu_id")
	private int cpuId;

	@Column(name="server_id")
	private int serverId;
	
	@Column(name="server_type")
	private String serverType;
	
	@Column(name="server_capacity")
	private int serverCapacity;
	
	@Column(name="cost_per_hour")
	private float serverCostPerHour;
	
	public int getCpuId() {
		return cpuId;
	}

	public void setCpuId(int cpuId) {
		this.cpuId = cpuId;
	}

	public int getServerId() {
		return serverId;
	}

	public void setServerId(int serverId) {
		this.serverId = serverId;
	}
	
	public String getServerType() {
		return serverType;
	}

	public void setServerType(String serverType) {
		this.serverType = serverType;
	}
	
	public int getServerCapacity() {
		return serverCapacity;
	}

	public void setServerCapacity(int serverCapacity) {
		this.serverCapacity = serverCapacity;
	}

	public float getServerCostPerHour() {
		return serverCostPerHour;
	}

	public void setServerCostPerHour(float serverCostPerHour) {
		this.serverCostPerHour = serverCostPerHour;
	}
}