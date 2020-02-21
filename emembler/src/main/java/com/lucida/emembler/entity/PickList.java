package com.lucida.emembler.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Stage Schema
 * 
 * @author Ravindra
 *
 */

@Entity
@Table(name = "pick_list")
public class PickList {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "stage")
	private String stages;

	@Column(name = "order_id")
	private int orderId;

	@Column(name = "description")
	private String description;
	
	@Column(name = "stageColor")
	private String stageColor;

	@JsonIgnore 
	@OneToMany(mappedBy = "stages", cascade = CascadeType.ALL)
	private List<Opportunity> opportunities;

	public PickList() {

	}

	public PickList(int id, String stages, int orderId, String description, List<Opportunity> opportunities) {
		this.id = id;
		this.stages = stages;
		this.orderId = orderId;
		this.description = description;
		this.opportunities = opportunities;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStage() {
		return stages;
	}

	public void setStage(String stages) {
		this.stages = stages;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Opportunity> getOpportunities() {
		return opportunities;
	}

	public void setOpportunities(List<Opportunity> opportunities) {
		this.opportunities = opportunities;
	}
	
	

	public String getStages() {
		return stages;
	}

	public void setStages(String stages) {
		this.stages = stages;
	}

	public String getStageColor() {
		return stageColor;
	}

	public void setStageColor(String stageColor) {
		this.stageColor = stageColor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result + ((opportunities == null) ? 0 : opportunities.hashCode());
		result = prime * result + orderId;
		result = prime * result + ((stageColor == null) ? 0 : stageColor.hashCode());
		result = prime * result + ((stages == null) ? 0 : stages.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PickList other = (PickList) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (opportunities == null) {
			if (other.opportunities != null)
				return false;
		} else if (!opportunities.equals(other.opportunities))
			return false;
		if (orderId != other.orderId)
			return false;
		if (stageColor == null) {
			if (other.stageColor != null)
				return false;
		} else if (!stageColor.equals(other.stageColor))
			return false;
		if (stages == null) {
			if (other.stages != null)
				return false;
		} else if (!stages.equals(other.stages))
			return false;
		return true;
	}

	

}
