package com.yakyak.UserStory2.data;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
//@XmlAccessorType(XmlAccessType.FIELD)
public class Herd{
	Herd(){}
	private ArrayList<LabYak> yakList;

	
	public ArrayList<LabYak> getYakList() {
		return yakList;
	}
	@XmlElement(name="labyak")
	public void setYakList(ArrayList<LabYak> yakList) {
		this.yakList = yakList;
	}
}
