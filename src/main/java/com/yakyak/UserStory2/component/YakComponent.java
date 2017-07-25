package com.yakyak.UserStory2.component;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.stereotype.Component;

import com.yakyak.UserStory2.data.Herd;

@Component
public class YakComponent {

	
	public Herd getBeanAfterConfiguringFromXml() throws JAXBException {
		File file = new File("src/main/resources/herd.xml");
		JAXBContext jaxbContext = JAXBContext.newInstance(Herd.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();  
		Herd herd= (Herd) jaxbUnmarshaller.unmarshal(file); 
		
		return herd;
	}
}
