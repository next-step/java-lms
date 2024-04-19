package nextstep.users.domain;

import nextstep.qna.UnAuthorizedException;
import nextstep.sessions.domain.Session;
import nextstep.sessions.domain.Sessions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

public class NsUser {
	public static final GuestNsUser GUEST_USER = new GuestNsUser();

	private Long id;

	private String userId;

	private String password;

	private String name;

	private String email;

	private Sessions sessions;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;

	public NsUser() {
	}

	public NsUser(Long id, String userId, String password, String name, String email) {
		this(id, userId, password, name, email, LocalDateTime.now(), null);
	}

	public NsUser(Long id, String userId, String password, String name, String email, LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.id = id;
		this.userId = userId;
		this.password = password;
		this.name = name;
		this.email = email;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.sessions = new Sessions(new ArrayList<>());
	}

	public Long getId() {
		return id;
	}

	public String getUserId() {
		return userId;
	}

	public NsUser setUserId(String userId) {
		this.userId = userId;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public NsUser setPassword(String password) {
		this.password = password;
		return this;
	}

	public String getName() {
		return name;
	}

	public NsUser setName(String name) {
		this.name = name;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public NsUser setEmail(String email) {
		this.email = email;
		return this;
	}

	public void update(NsUser loginUser, NsUser target) {
		if (!matchUserId(loginUser.getUserId())) {
			throw new UnAuthorizedException();
		}

		if (!matchPassword(target.getPassword())) {
			throw new UnAuthorizedException();
		}

		this.name = target.name;
		this.email = target.email;
	}

	public boolean matchUser(NsUser target) {
		return matchUserId(target.getUserId());
	}

	private boolean matchUserId(String userId) {
		return this.userId.equals(userId);
	}

	public boolean matchPassword(String targetPassword) {
		return password.equals(targetPassword);
	}

	public boolean equalsNameAndEmail(NsUser target) {
		if (Objects.isNull(target)) {
			return false;
		}

		return name.equals(target.name) &&
				email.equals(target.email);
	}

	public boolean isGuestUser() {
		return false;
	}

	public void registerSession(final Session session, final long paymentAmount) {
		if (session.isPaidSession() && session.getTuitionFee() != paymentAmount) {
			throw new IllegalArgumentException("수강료가 일치하지 않습니다.");
		}
		addSession(session);
	}

	private void addSession(final Session session) {
		session.validateOpeningSession();
		sessions.add(session);
	}

	public Sessions getSessions() {
		return sessions;
	}

	private static class GuestNsUser extends NsUser {
		@Override
		public boolean isGuestUser() {
			return true;
		}
	}

	@Override
	public String toString() {
		return "NsUser{" +
				"id=" + id +
				", userId='" + userId + '\'' +
				", name='" + name + '\'' +
				", email='" + email + '\'' +
				", createdAt=" + createdAt +
				", updatedAt=" + updatedAt +
				'}';
	}
}
