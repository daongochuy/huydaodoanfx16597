package com.company.edu.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "programs")

public class Program {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull(message = "Vui lòng chọn danh mục")
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	@NotBlank(message = "Tiêu đề không được để trống")
	@Size(max = 200, message = "Tiêu đề tối đa 200 ký tự")
	@Column(length = 200)
	private String title;
	@NotBlank(message = "Quốc gia không được để trống")
	@Size(max = 50, message = "Quốc gia tối đa 50 ký tự")
	@Column(length = 50)
	private String country;
	@NotBlank(message = "Ngành nghề không được để trống")
	@Size(max = 100, message = "Ngành nghề tối đa 100 ký tự")
	@Column(name = "job_name", length = 100)
	private String jobName;
	@NotNull(message = "Vui lòng nhập lương")
	@Positive(message = "Lương phải lớn hơn 0")
	@Column(precision = 12, scale = 2)
	private BigDecimal salary;
	@NotBlank(message = "Vui lòng nhập đơn vị tiền (USD, JPY...)")
	@Size(max = 10, message = "Đơn vị tiền tối đa 10 ký tự")
	@Column(length = 10)
	private String currency;
	@NotNull(message = "Vui lòng nhập số năm hợp đồng")
	@Positive(message = "Số năm hợp đồng phải > 0")
	@Column(name = "contract_years")
	private Integer contractYears;
	@NotNull(message = "Vui lòng nhập tuổi tối thiểu")
	@Min(value = 16, message = "Tuổi tối thiểu phải >= 16")
	@Column(name = "age_min")
	private Integer ageMin;
	@NotNull(message = "Vui lòng nhập tuổi tối đa")
	@Min(value = 17, message = "Tuổi tối đa phải >= 17")
	@Column(name = "age_max")
	private Integer ageMax;
	@Size(max = 10, message = "Giới tính tối đa 10 ký tự")
	@Column(length = 10)
	private String gender;
	@Size(max = 100)
	@Column(name = "education_required", length = 100)
	private String educationRequired;
	@Size(max = 100)
	@Column(name = "language_required", length = 100)
	private String languageRequired;
	@Size(max = 100)
	@Column(name = "experience_required", length = 100)
	private String experienceRequired;
	@NotNull(message = "Vui lòng nhập số lượng tuyển")
	@Positive(message = "Số lượng tuyển phải > 0")
	private Integer quantity;
	@NotNull(message = "Vui lòng nhập chi phí")
	@PositiveOrZero(message = "Chi phí phải >= 0")
	@Column(precision = 12, scale = 2)
	private BigDecimal cost;

	@Column(columnDefinition = "TEXT")
	private String benefits;

	@Column(name = "training_content", columnDefinition = "TEXT")
	private String trainingContent;

	@Column(columnDefinition = "TEXT")
	private String description;

	@Column(name = "interview_date")
	private LocalDate interviewDate;

	@Column(name = "start_work_date")
	private LocalDate startWorkDate;
	@NotBlank(message = "Vui lòng chọn trạng thái")
	@Size(max = 20)
	@Column(length = 20)
	private String status;

	@Column(name = "created_at")
	private LocalDateTime createdAt;
	@OneToMany(mappedBy = "program")
	private List<ProgramImage> ProgramImages;
	public List<ProgramImage> getProgramImages() {
		return ProgramImages;
	}

	public void setProgramImages(List<ProgramImage> programImages) {
		ProgramImages = programImages;
	}

	public Program() {
		super();
	}

	public Program(Long id, Category category, String title, String country, String jobName, BigDecimal salary,
			String currency, Integer contractYears, Integer ageMin, Integer ageMax, String gender,
			String educationRequired, String languageRequired, String experienceRequired, Integer quantity,
			BigDecimal cost, String benefits, String trainingContent, String description, LocalDate interviewDate,
			LocalDate startWorkDate, String status, LocalDateTime createdAt) {
		super();
		this.id = id;
		this.category = category;
		this.title = title;
		this.country = country;
		this.jobName = jobName;
		this.salary = salary;
		this.currency = currency;
		this.contractYears = contractYears;
		this.ageMin = ageMin;
		this.ageMax = ageMax;
		this.gender = gender;
		this.educationRequired = educationRequired;
		this.languageRequired = languageRequired;
		this.experienceRequired = experienceRequired;
		this.quantity = quantity;
		this.cost = cost;
		this.benefits = benefits;
		this.trainingContent = trainingContent;
		this.description = description;
		this.interviewDate = interviewDate;
		this.startWorkDate = startWorkDate;
		this.status = status;
		this.createdAt = createdAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Integer getContractYears() {
		return contractYears;
	}

	public void setContractYears(Integer contractYears) {
		this.contractYears = contractYears;
	}

	public Integer getAgeMin() {
		return ageMin;
	}

	public void setAgeMin(Integer ageMin) {
		this.ageMin = ageMin;
	}

	public Integer getAgeMax() {
		return ageMax;
	}

	public void setAgeMax(Integer ageMax) {
		this.ageMax = ageMax;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEducationRequired() {
		return educationRequired;
	}

	public void setEducationRequired(String educationRequired) {
		this.educationRequired = educationRequired;
	}

	public String getLanguageRequired() {
		return languageRequired;
	}

	public void setLanguageRequired(String languageRequired) {
		this.languageRequired = languageRequired;
	}

	public String getExperienceRequired() {
		return experienceRequired;
	}

	public void setExperienceRequired(String experienceRequired) {
		this.experienceRequired = experienceRequired;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public String getBenefits() {
		return benefits;
	}

	public void setBenefits(String benefits) {
		this.benefits = benefits;
	}

	public String getTrainingContent() {
		return trainingContent;
	}

	public void setTrainingContent(String trainingContent) {
		this.trainingContent = trainingContent;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getInterviewDate() {
		return interviewDate;
	}

	public void setInterviewDate(LocalDate interviewDate) {
		this.interviewDate = interviewDate;
	}

	public LocalDate getStartWorkDate() {
		return startWorkDate;
	}

	public void setStartWorkDate(LocalDate startWorkDate) {
		this.startWorkDate = startWorkDate;
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

	@AssertTrue(message = "Tuổi tối thiểu phải nhỏ hơn tuổi tối đa")
	public boolean isValidAgeRange() {
		if (ageMin == null || ageMax == null)
			return true;
		return ageMin <= ageMax;
	}
}
