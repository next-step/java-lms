package nextstep.courses.domain.session;

import nextstep.courses.domain.cover.CoverImage;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class SessionDomainTestHelper {

    public static CoverImage getSingleCoverImage(List<CoverImage> coverImages) {
        assertThat(coverImages).isNotNull();
        assertThat(coverImages).hasSize(1);
        return coverImages.get(0);
    }

    public static Student getSingleStudent(Session session) {
        Set<Student> enrolledStudents = session.getEnrolledStudents();
        assertThat(enrolledStudents).hasSize(1);
        return enrolledStudents.iterator().next();
    }

    public static Student getSingleNsUser(SessionEnrollment sessionEnrollment) {
        Set<Student> enrolledStudents = sessionEnrollment.getEnrolledStudents();
        assertThat(enrolledStudents).hasSize(1);
        return enrolledStudents.iterator().next();
    }
}
