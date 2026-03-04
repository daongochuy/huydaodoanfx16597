package com.company.edu.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "categories")

public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message = "Tên danh mục không được để trống")
	@Size(min = 3, max = 100, message = "Tên danh mục phải từ 3 - 100 ký tự")
	@Column(length = 100)
	private String name;
	@NotBlank(message = "Loại không được để trống")
	@Size(max = 30)
	@Column(length = 30)
	private String type;

	@Column(columnDefinition = "TEXT")
	private String description;
	@NotBlank(message = "Vui lòng chọn trạng thái")
	@Size(max = 20)
	@Column(length = 20)
	private String status;
	@OneToMany(mappedBy = "category")
	private List<Program> programs;
	public List<Program> getPrograms() {
		return programs;
	}

	public void setPrograms(List<Program> programs) {
		this.programs = programs;
	}

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	public Category() {
		super();
	}

	public Category(Long id, String name, String type, String description, String status, LocalDateTime createdAt) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.description = description;
		this.status = status;
		this.createdAt = createdAt;
	}

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

}
