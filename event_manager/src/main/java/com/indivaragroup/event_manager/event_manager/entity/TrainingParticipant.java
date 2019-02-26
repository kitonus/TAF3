package com.indivaragroup.event_manager.event_manager.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name="trx_training_participant")
@ApiModel("Training participant")
public class TrainingParticipant implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private UUID id;
	
	@Column(name="nama_peserta")
	private String name;
	
	private Date dateOfBirth;
	
	@ApiModelProperty("Any comment given by this participant")
	@Column(columnDefinition="text")
	private String comment;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
}

