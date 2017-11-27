package edu.asu.luminosity.atlas.model;

import java.util.ArrayList;

public class EntityModel {
	
	
	
	Boolean  automatedExpansion;
	Boolean  isEnum;
	Boolean  isOverridable;
	String name;
	String id;
	ArrayList<EntriesSubModel> entries;

	
	public Boolean getAutomatedExpansion() {
		return automatedExpansion;
	}
	public void setAutomatedExpansion(Boolean automatedExpansion) {
		this.automatedExpansion = automatedExpansion;
	}
	public Boolean getIsEnum() {
		return isEnum;
	}
	public void setIsEnum(Boolean isEnum) {
		this.isEnum = isEnum;
	}
	public Boolean getIsOverridable() {
		return isOverridable;
	}
	public void setIsOverridable(Boolean isOverridable) {
		this.isOverridable = isOverridable;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public ArrayList<EntriesSubModel> getEntries() {
		return this.entries;
	}
	public void setEntries(ArrayList<EntriesSubModel> entries) {
		this.entries = entries;
	}
	public void addEntries(EntriesSubModel entries) {
		this.entries.add(entries);
	}

}




