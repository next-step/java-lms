package nextstep.courses.domain.session;

import nextstep.courses.domain.course.Course;
import nextstep.courses.entity.SessionEntity;

public class SessionFactory {
	public Session createSession(SessionEntity sessionEntity) {
		if(sessionEntity.getFee() > 0) {
			return new PaidSession(sessionEntity.getId(),
					new Course(sessionEntity.getCourseId()),
					SessionState.valueOf(sessionEntity.getState()),
					new SessionImage(sessionEntity.getImageId()),
					sessionEntity.getStartDate(),
					sessionEntity.getEndDate(),
					sessionEntity.getNumberOfStudent(),
					sessionEntity.getMaxNumberOfStudent(),
					sessionEntity.getFee());
		}

		return new FreeSession(sessionEntity.getId(),
				new Course(sessionEntity.getCourseId()),
				SessionState.valueOf(sessionEntity.getState()),
				new SessionImage(sessionEntity.getImageId()),
				sessionEntity.getStartDate(),
				sessionEntity.getEndDate(),
				sessionEntity.getNumberOfStudent());
	}
}
