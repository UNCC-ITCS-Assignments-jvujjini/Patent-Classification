package com.data.patents;

import java.util.ArrayList;

public class Patent {
	
	String PatentID;
	String PatentName;
	ArrayList<String> Description;
	String Target;
	
	public Patent(Object object, Object object2, ArrayList<String> description,
			Object object3) {
		// TODO Auto-generated constructor stub
		
		this.PatentID = ((String) object).trim();
		this.PatentName = (String) object2;
		this.Description = description;
		this.Target = (String) object3;
		
	}
	public String getPatentID() {
		return PatentID;
	}
	public void setPatentID(String patentID) {
		PatentID = patentID;
	}
	public String getPatentName() {
		return PatentName;
	}
	public void setPatentName(String patentName) {
		PatentName = patentName;
	}
	public ArrayList<String> getDescription() {
		return Description;
	}
	public void setDescription(ArrayList<String> description) {
		Description = description;
	}
	public String getTarget() {
		return Target;
	}
	public void setTarget(String target) {
		Target = target;
	}
	
	@Override
	public String toString() {
		return "Patent [PatentID=" + PatentID + ", PatentName=" + PatentName
				+ ", Description=" + Description + ", Target=" + Target + "]";
	}

}
