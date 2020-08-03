package com.resourceAllocator.ResourceAllocator.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * @author USHANANDHINI K
 *
 */
@Entity
@Table(name="server_details")
public class ServerDetailsEntity{
	
	@Id
	@Column(name = "server_id", updatable = false, nullable = false)
	private int serverId;

	@Column(name = "region")
	private String region;
	
	public int getServerId() {
		return serverId;
	}

	public void setServerId(int serverId) {
		this.serverId = serverId;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}
}