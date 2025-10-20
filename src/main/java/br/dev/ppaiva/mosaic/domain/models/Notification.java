package br.dev.ppaiva.mosaic.domain.models;

import java.time.OffsetDateTime;
import java.util.Objects;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "notification", indexes = {
		@Index(name = "ix_notification_status_created", columnList = "status, created_at DESC") }, uniqueConstraints = {
				@UniqueConstraint(name = "ux_notification_message_key", columnNames = { "message_key" }) })
public class Notification extends AbstractEntity {
	private static final long serialVersionUID = 2857767414691707457L;

	@Column(nullable = false, length = 16)
	private String type;

	@Column(name = "to_address", nullable = false, length = 180)
	private String to;

	@Column(length = 255)
	private String subject;

	@Column(length = 120)
	private String template;

	@JdbcTypeCode(SqlTypes.JSON)
	@Column(columnDefinition = "jsonb")
	private String payload;

	@Column(name = "message_key", length = 180)
	private String messageKey;

	@Column(nullable = false, length = 16)
	private String status = "QUEUED";

	@Column(columnDefinition = "text")
	private String error;

	@Column(name = "sent_at")
	private OffsetDateTime sentAt;

	@PrePersist
	void pre() {
		if (status == null)
			status = "QUEUED";
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	public String getMessageKey() {
		return messageKey;
	}

	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public OffsetDateTime getSentAt() {
		return sentAt;
	}

	public void setSentAt(OffsetDateTime sentAt) {
		this.sentAt = sentAt;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(messageKey);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Notification other = (Notification) obj;
		return Objects.equals(messageKey, other.messageKey);
	}

}
