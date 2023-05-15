package nextstep;

import nextstep.courses.domain.*;
import nextstep.qna.domain.Answer;
import nextstep.qna.domain.Question;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Fixtures {
    public static Question createQuestion(NsUser nsUser) {
        return new Question(nsUser, "title1", "contents1");
    }

    public static Answer createAnswer1(NsUser nsUser) {
        return new Answer(nsUser, Fixtures.createQuestion(NsUserTest.JAVAJIGI), "Answers Contents1");
    }

    public static Answer createAnswer2(NsUser nsUser) {
        return new Answer(nsUser, Fixtures.createQuestion(NsUserTest.JAVAJIGI), "Answers Contents2");
    }

    public static CourseBuilder aCourse() {
        return CourseBuilder.aCourse()
                            .withId(1L)
                            .withSessions(new ArrayList<>())
                            .withCreatorId(1L)
                            .withCreatedAt(LocalDateTime.now());
    }

    public static SessionBuilder aSession() {
        return SessionBuilder.aSession()
                             .withSessionPeriod(new SessionPeriod(LocalDateTime.now(), LocalDateTime.now().plusDays(1)))
                             .withSessionBillType(SessionBillType.FREE)
                             .withId(1L);
    }

}
