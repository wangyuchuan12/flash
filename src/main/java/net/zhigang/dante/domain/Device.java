package net.zhigang.dante.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
@Entity(name="device")
public class Device {
	@Id@GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id")
    private Long id;
	@Column(name="brand_id")
	private Long brand_id;
	@Column(name="name", length = 100)
	private String name;
	@CreatedDate
	@Column(name="created_at")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime created_at;
	@LastModifiedDate
	@Column(name="updated_at")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime updated_at;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public long getBrand_id() {
		return brand_id;
	}
	public void setBrand_id(long brand_id) {
		this.brand_id = brand_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public DateTime getCreated_at() {
		return created_at;
	}
	public void setCreated_at(DateTime created_at) {
		this.created_at = created_at;
	}
	public DateTime getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(DateTime updated_at) {
		this.updated_at = updated_at;
	}
}
