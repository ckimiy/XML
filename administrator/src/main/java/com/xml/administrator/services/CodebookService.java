package com.xml.administrator.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xml.administrator.dto.ContentDTO;
import com.xml.administrator.interfaces.CodebookServiceInt;
import com.xml.administrator.model.ObjectCategory;
import com.xml.administrator.model.ObjectType;
import com.xml.administrator.model.Services;
import com.xml.administrator.repositories.ObjectCategoryRepository;
import com.xml.administrator.repositories.ObjectTypeRepository;
import com.xml.administrator.repositories.ServicesRepository;

@Service
public class CodebookService implements CodebookServiceInt {

	@Autowired
	private ObjectCategoryRepository objCateRep;
	
	@Autowired
	private ObjectTypeRepository objTypeRep;
	
	@Autowired
	private ServicesRepository servRep;
	
	@Override
	public ArrayList<ObjectCategory> getAllObjCategories() {
		ArrayList<ObjectCategory> objCatList = (ArrayList<ObjectCategory>) objCateRep.findAll();

		return objCatList;
	}

	@Override
	public ArrayList<ObjectType> getAllObjTypes() {
		ArrayList<ObjectType> objTypeList = (ArrayList<ObjectType>) objTypeRep.findAll();
		
		return objTypeList;
	}

	@Override
	public ArrayList<Services> getAllServices() {
		ArrayList<Services> servList = (ArrayList<Services>) servRep.findAll();

		return servList;
	}
	

	@Override
	public ObjectCategory addObjCate(ContentDTO content) {
		ObjectCategory temp = new ObjectCategory();
		temp.setCategory(content.getContent());
		objCateRep.save(temp);
		return temp;
	}

	@Override
	public ObjectType addObjType(ContentDTO content) {
		ObjectType temp = new ObjectType();
		temp.setType(content.getContent());
		objTypeRep.save(temp);
		return temp;
	}

	@Override
	public Services addServ(ContentDTO content) {
		Services temp = new Services();
		temp.setService(content.getContent());
		servRep.save(temp);
		return temp;
	}

	@Override
	public ObjectCategory editObjCate(Long id, String content) {
		ObjectCategory temp = objCateRep.findById(id).get();
		if(temp == null) return null;
		temp.setCategory(content);
		objCateRep.save(temp);
		return temp;
	}

	@Override
	public ObjectType editObjType(Long id, String content) {
		ObjectType temp = objTypeRep.findById(id).get();
		if(temp == null) return null;
		temp.setType(content);
		objTypeRep.save(temp);
		return temp;
	}

	@Override
	public Services editServices(Long id, String content) {
		Services temp = servRep.findById(id).get();
		if(temp == null) return null;
		temp.setService(content);
		servRep.save(temp);
		return temp;
	}

	@Override
	public ObjectCategory deleteObjCate(Long id) {
		ObjectCategory temp = objCateRep.findById(id).get();
		if(temp == null) return null;
		objCateRep.delete(temp);
		return temp;
	}

	@Override
	public ObjectType deleteObjType(Long id) {
		ObjectType temp = objTypeRep.findById(id).get();
		if(temp == null) return null;
		objTypeRep.delete(temp);
		return temp;
	}

	@Override
	public Services deleteServices(Long id) {
		Services temp = servRep.findById(id).get();
		if(temp == null) return null;
		servRep.delete(temp);
		return temp;
	}
}
