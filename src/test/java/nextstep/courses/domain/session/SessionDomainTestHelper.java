package nextstep.courses.domain.session;

import nextstep.courses.domain.cover.CoverImage;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class SessionDomainTestHelper {

    public static CoverImage getSingleCoverImage(List<CoverImage> coverImages) {
        assertThat(coverImages).isNotNull();
        assertThat(coverImages).hasSize(1);
        return coverImages.get(0);
    }

    public static NsUser getSingleUser(Session session) {
        Set<NsUser> enrolledUsers = session.getEnrolledUsers();
        assertThat(enrolledUsers).contains(NsUserTest.SANJIGI);
        return enrolledUsers.iterator().next();
    }

    public static NsUser getSingleNsUser(SessionEnrollment sessionEnrollment) {
        Set<NsUser> enrolledUsers = sessionEnrollment.getEnrolledUsers();
        assertThat(enrolledUsers).hasSize(1);
        return enrolledUsers.iterator().next();
    }
}
