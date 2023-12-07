package nextstep.courses.domain.session.registration;

import java.time.LocalDateTime;

import nextstep.courses.domain.BaseTimeEntity;
import nextstep.courses.domain.session.Session;
import nextstep.users.domain.NsUser;

public class Registration extends BaseTimeEntity {
	private Long id;
	private final NsUser user;
	private final Session session;

	public Registration(
		NsUser user, Session session
	) {
		this.user = user;
		this.session = session;
	}

	public Registration(
		Long id, NsUser user, Session session,
		LocalDateTime createdAt, LocalDateTime updatedAt
	) {
		super(createdAt, updatedAt);
		this.id = id;
		this.user = user;
		this.session = session;
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
}
