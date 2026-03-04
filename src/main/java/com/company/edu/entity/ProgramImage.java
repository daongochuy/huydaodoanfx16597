package com.company.edu.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "program_images")

public class ProgramImage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "program_id")
	private Program program;

	@Column(name = "image_url", length = 255)
	private String imageUrl;

	@Column(name = "is_thumbnail")
	private Boolean isThumbnail;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Program getProgram() {
		return program;
	}

	public void setProgram(Program program) {
		this.program = program;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Boolean getIsThumbnail() {
		return isThumbnail;
	}

	public void setIsThumbnail(Boolean isThumbnail) {
		this.isThumbnail = isThumbnail;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public ProgramImage(Long id, Program program, String imageUrl, Boolean isThumbnail, LocalDateTime createdAt) {
		super();
		this.id = id;
		this.program = program;
		this.imageUrl = imageUrl;
		this.isThumbnail = isThumbnail;
		this.createdAt = createdAt;
	}

	public ProgramImage() {
		super();
	}

}
