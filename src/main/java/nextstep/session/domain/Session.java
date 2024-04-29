package nextstep.session.domain;

import nextstep.courses.domain.Course;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUsers;

public abstract class Session {
	private static final int INCREASE_STUDENT = 1;
	private Long id;
	private SessionStatus sessionStatus;
	private Course course;
	private ImageInfo imageType;
	private Period period;
	private final int maximumNumberOfParticipants;
	private NsUsers nsUsers;


	public Session(Course course, ImageInfo imageInfo, Period period, int maximumNumberOfParticipants) {
		this(0L, SessionStatus.PREPARING, course, imageInfo, period, maximumNumberOfParticipants);
	}

	public Session(Long id, SessionStatus sessionStatus, Course course, ImageInfo imageInfo, Period period, int maximumNumberOfParticipants) {
		this.id = id;
		this.sessionStatus = sessionStatus;
		this.course = course;
		this.imageType = imageInfo;
		this.period = period;
		this.nsUsers = new NsUsers();
		this.maximumNumberOfParticipants = maximumNumberOfParticipants;
	}

	public boolean isMaximumNumberOfParticipantsLimited(int numberOfParticipants) {
		return numberOfParticipants <= maximumNumberOfParticipants;
	}

	abstract boolean isSamePaymentAndSessionPrice(int price);

	private boolean isSessionRegister() {
		return sessionStatus == SessionStatus.PREPARING;
	}
	private boolean isParticipantsSession() {
		return maximumNumberOfParticipants >= nsUsers.getNumberOfStudent() + INCREASE_STUDENT;
	}

	public boolean applySession(NsUser student) {
		if(!isSessionRegister() || !isParticipantsSession())
			return false;
		nsUsers.addStudent(student);
		return true;
	}

}
