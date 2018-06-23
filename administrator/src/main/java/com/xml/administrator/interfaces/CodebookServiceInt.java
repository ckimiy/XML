package com.xml.administrator.interfaces;

import java.util.ArrayList;

import com.xml.administrator.dto.ContentDTO;
import com.xml.administrator.model.ObjectCategory;
import com.xml.administrator.model.ObjectType;
import com.xml.administrator.model.Services;

public interface CodebookServiceInt {

	public ArrayList<ObjectCategory> getAllObjCategories();
	public ArrayList<ObjectType> getAllObjTypes();
	public ArrayList<Services> getAllServices();
	
	public ObjectCategory addObjCate(ContentDTO content);
	public ObjectType addObjType(ContentDTO content);
	public Services addServ(ContentDTO content);
	
	public ObjectCategory editObjCate(Long id, String content);
	public ObjectType editObjType(Long id, String content);
	public Services editServices(Long id, String content);
	
	public ObjectCategory deleteObjCate(Long id);
	public ObjectType deleteObjType(Long id);
	public Services deleteServices(Long id);
}
