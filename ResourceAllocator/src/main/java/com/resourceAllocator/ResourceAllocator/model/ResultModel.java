package com.resourceAllocator.ResourceAllocator.model;

import java.util.List;

import com.resourceAllocator.ResourceAllocator.entity.ResultEntity;

/**
 * @author USHANANDHINI K
 *
 */
public class ResultModel {
	
	private List<ResultEntity> serverDetails;

	public List<ResultEntity> getServerDetails() {
		return serverDetails;
	}

	public void setServerDetails(List<ResultEntity> serverDetails) {
		this.serverDetails = serverDetails;
	}
}