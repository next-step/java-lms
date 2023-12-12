package nextstep.courses.domain.session.registration;

import java.time.LocalDateTime;

import nextstep.courses.domain.BaseTimeEntity;
import nextstep.courses.domain.enums.ApprovalStatus;
import nextstep.courses.domain.session.Session;
import nextstep.users.domain.NsUser;

public class Registration extends BaseTimeEntity {
	private Long id;
	private final NsUser user;
	private final Session session;
	private ApprovalStatus approvalStatus;

	public Registration(
		NsUser user, Session session, ApprovalStatus status
	) {
		this.user = user;
		this.session = session;
		this.approvalStatus = status;
	}

	public Registration(
		Long id, NsUser user, Session session, ApprovalStatus status,
		LocalDateTime createdAt, LocalDateTime updatedAt
	) {
		super(createdAt, updatedAt);
		this.id = id;
		this.user = user;
		this.session = session;
		this.approvalStatus = status;
	}

	public Long getId() {
		return id;
	}

	public NsUser getUser() {
		return user;
	}

	public Session getSession() {
		return session;
	}

	public ApprovalStatus getApprovalStatus() {
		return approvalStatus;
	}

	public boolean isApproval() {
		return approvalStatus.isApproval();
	}

	public void cancel() {
		this.approvalStatus = ApprovalStatus.CANCEL;
	}

	public void approve() {
		this.approvalStatus = ApprovalStatus.APPROVAL;
	}
}
