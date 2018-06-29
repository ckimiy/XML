package com.xml.administrator.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name = "Permission")
@Entity
public class Permission {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;
	
    @XmlElement(name = "name", required = true)
    protected String name;
    
    @XmlTransient
    @ManyToMany(fetch = FetchType.LAZY,
            mappedBy = "permissions")
    protected List<TUser> tusers;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TUser> getTusers() {
		if (tusers == null) {
			tusers = new ArrayList<TUser>();
        }
		return tusers;
	}
	
	
}
