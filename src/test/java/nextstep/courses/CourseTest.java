package nextstep.courses;

import nextstep.courses.domain.*;
import nextstep.courses.enumeration.SessionProgressType;
import nextstep.courses.enumeration.SessionRecruitStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CourseTest {

    @Test
    @DisplayName("과정은 여러개의 강의를 가질 수 있다.")
    void courseWithMultipleSessionTest() {
        Session freeSession = FreeSession.of(1L,
                1L,
                "무료강의",
                new SessionImages(List.of(SessionImage.of(1L, 1L,"url", "GIF", 1000L, 300L, 200L))),
                SessionRecruitStatus.NOT_RECRUITING,
                SessionProgressType.READY,
                LocalDateTime.of(2023, Month.DECEMBER, 3, 12, 0, 0),
                LocalDateTime.of(2023, Month.DECEMBER, 10, 15, 0, 0));

        Session costMoneySession = CostMoneySession.of(1L,
                1L,
                "유료강의",
                new SessionImages(List.of(SessionImage.of(1L, 1L,"url", "GIF", 1000L, 300L, 200L))),
                10,
                SessionRecruitStatus.NOT_RECRUITING,
                SessionProgressType.READY,
                2000,
                LocalDateTime.of(2023, Month.DECEMBER, 3, 12, 0, 0),
                LocalDateTime.of(2023, Month.DECEMBER, 10, 15, 0, 0));

        Course course = new Course("넥스트스텝 1강", 1L);
        course.addSession(freeSession);
        course.addSession(costMoneySession);

        assertThat(course.getSessions().getSessions()).isEqualTo(Arrays.asList(freeSession, costMoneySession));
    }
}
