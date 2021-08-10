package hellojpa;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity {

	private String createBy;
	private LocalDateTime createDate;
	private String LastModifiedBy;
	private LocalDateTime LastModifiedDate;
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public LocalDateTime getCreateDate() {
		return createDate;
	}
	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}
	public String getLastModifiedBy() {
		return LastModifiedBy;
	}
	public void setLastModifiedBy(String lastModifiedBy) {
		LastModifiedBy = lastModifiedBy;
	}
	public LocalDateTime getLastModifiedDate() {
		return LastModifiedDate;
	}
	public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
		LastModifiedDate = lastModifiedDate;
	}
}
