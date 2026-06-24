	package com.cityComplaint.demo.Entity;

	import java.time.LocalDateTime;
	import jakarta.persistence.Entity;
	import jakarta.persistence.EnumType;
	import jakarta.persistence.Enumerated;
	import jakarta.persistence.GeneratedValue;
	import jakarta.persistence.GenerationType;
	import jakarta.persistence.Id;
	import jakarta.persistence.JoinColumn;
	import jakarta.persistence.ManyToOne;
	import jakarta.persistence.OneToOne;
	import lombok.AllArgsConstructor;
	import lombok.Getter;
	import lombok.NoArgsConstructor;
	import lombok.Setter;

	@Entity
	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	public class ComplaintUpdates {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long updateId;

		@ManyToOne
		@JoinColumn(name="complaint_id")
		private Complaint complaint;




		@ManyToOne
		@JoinColumn(nullable= false, name="officer_id")
		private Officer officer;

		public enum Status {
			OPEN,
			IN_PROGRESS,
			RESOLVED
		}
		@Enumerated(EnumType.STRING)
		private Status status;

		private String comment;

		LocalDateTime updatedAt;


		@Override
		public String toString() {
			return "ComplaintUpdates [updateId=" + updateId + ", complaint=" + complaint + ", officer=" + officer
					+ ", status=" + status + ", comment=" + comment + ", updatedAt=" + updatedAt + "]";
		}






	}
