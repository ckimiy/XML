//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.05.24 at 10:10:04 PM CEST 
//


package com.xml.administrator.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonIgnore;



/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Owner" type="{http://www.holiday.com/system}Agent"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Type" type="{http://www.holiday.com/system}objectType"/>
 *         &lt;element name="Category" type="{http://www.holiday.com/system}objectCategory"/>
 *         &lt;element name="picture" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="maxPerson" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element ref="{http://www.holiday.com/system}Comment" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.holiday.com/system}Location"/>
 *         &lt;element ref="{http://www.holiday.com/system}Raiting" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.holiday.com/system}services" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.holiday.com/system}Price_shedule"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
	"id",
    "name",
    "owner",
    "description",
    "type",
    "category",
    "picture",
    "maxPerson",
    "comment",
    "location",
    "rating",
    "services",
    "priceShedule"
})
@XmlRootElement(name = "Accomodation")
@Entity
public class Accomodation implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;
	
    @XmlElement(required = true)
    protected String name;
    @ManyToOne	
    @XmlElement(name = "Owner", required = true)
    protected Agent owner;
    
    @XmlElement(required = true)
    protected String description;
    
    @XmlElement(name = "Type", required = true)
    @ManyToOne
    protected ObjectType type;
    
    @ManyToOne
    @XmlElement(name = "Category", required = true)
    protected ObjectCategory category;
    
    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @XmlElement(name = "Picture", required = false)
    
    protected List<Picture> picture;
    
    @Column(nullable=false)
    protected int maxPerson;
    
    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @XmlElement(name = "Comment")
    protected List<Comment> comment;
    
    @ManyToOne
    @XmlElement(name = "Location", required = true)
    protected Location location;
    
    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @XmlElement(name = "Rating")
    protected List<Rating> rating;
    
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @XmlElement(name = "Services")
    protected List<Services> services;
    
    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @XmlElement(name = "Price_shedule", required = true)
    protected PriceShedule priceShedule;

    public Long getId(){
    	return id;
    }
    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the owner property.
     * 
     * @return
     *     possible object is
     *     {@link Agent }
     *     
     */
    public Agent getOwner() {
        return owner;
    }

    /**
     * Sets the value of the owner property.
     * 
     * @param value
     *     allowed object is
     *     {@link Agent }
     *     
     */
    public void setOwner(Agent value) {
        this.owner = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link ObjectType }
     *     
     */
    public ObjectType getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link ObjectType }
     *     
     */
    public void setType(ObjectType value) {
        this.type = value;
    }

    /**
     * Gets the value of the category property.
     * 
     * @return
     *     possible object is
     *     {@link ObjectCategory }
     *     
     */
    public ObjectCategory getCategory() {
        return category;
    }

    /**
     * Sets the value of the category property.
     * 
     * @param value
     *     allowed object is
     *     {@link ObjectCategory }
     *     
     */
    public void setCategory(ObjectCategory	 value) {
        this.category = value;
    }

    /**
     * Gets the value of the picture property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the picture property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPicture().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * byte[]
     * 
     */
    
    public List<Picture> getPicture() {
        if (picture == null) {
            picture = new ArrayList<Picture>();
        }
        return this.picture;
    }

    /**
     * Gets the value of the maxPerson property.
     * 
     */
    public int getMaxPerson() {
        return maxPerson;
    }

    /**
     * Sets the value of the maxPerson property.
     * 
     */
    public void setMaxPerson(int value) {
        this.maxPerson = value;
    }

    /**
     * Gets the value of the comment property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the comment property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getComment().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Comment }
     * 
     * 
     */
    public List<Comment> getComment() {
        if (comment == null) {
            comment = new ArrayList<Comment>();
        }
        return this.comment;
    }

    /**
     * Gets the value of the location property.
     * 
     * @return
     *     possible object is
     *     {@link Location }
     *     
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Sets the value of the location property.
     * 
     * @param value
     *     allowed object is
     *     {@link Location }
     *     
     */
    public void setLocation(Location value) {
        this.location = value;
    }

    /**
     * Gets the value of the raiting property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the raiting property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRaiting().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Rating }
     * 
     * 
     */
    public List<Rating> getRaiting() {
        if (rating == null) {
        	rating = new ArrayList<Rating>();
        }
        return this.rating;
    }

    /**
     * Gets the value of the services property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the services property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getServices().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Services }
     * 
     * 
     */
    public List<Services> getServices() {
        if (services == null) {
            services = new ArrayList<Services>();
        }
        return this.services;
    }

    /**
     * Gets the value of the priceShedule property.
     * 
     * @return
     *     possible object is
     *     {@link PriceShedule }
     *     
     */
   
    public PriceShedule getPriceShedule() {
        return priceShedule;
    }

    /**
     * Sets the value of the priceShedule property.
     * 
     * @param value
     *     allowed object is
     *     {@link PriceShedule }
     *     
     */
    public void setPriceShedule(PriceShedule value) {
        this.priceShedule = value;
    }
    public Double averageRating() {
		return rating.stream()
				.mapToDouble(rating -> rating.getRating())
				.average()
				.orElse(Double.NaN);
	}
	
}
