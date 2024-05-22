package nextstep.session.domain;

import java.util.List;

public interface StudentEnrollmentInfomationRepository {
	int save(StudentEnrollmentInfomation studentEnrollmentInfomation);

	List<StudentEnrollmentInfomation> findAllByEnrollmentId(long id);

	int updateByEnrollmentId(StudentEnrollmentInfomation studentEnrollmentInfomation);

	int deleteByEnrollmentId(long id);
}
