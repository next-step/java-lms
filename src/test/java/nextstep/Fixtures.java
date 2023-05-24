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
                            .withTitle("title")
                            .withSessions(new ArrayList<>())
                            .withCreatorId(1L)
                            .withCreatedAt(LocalDateTime.now())
                            .withUpdatedAt(LocalDateTime.now());
    }

    public static SessionBuilder aSession() {
        return SessionBuilder.aSession()
                             .withSessionStatus(SessionStatus.READY)
                             .withSessionBillType(SessionBillType.FREE)
                             .withSessionRegistration(aSessionRegistration().build())
                             .withSessionCoverImage(new SessionCoverImage("http://edu.nexystep.camp"))
                             .withSessionPeriod(new SessionPeriod(LocalDateTime.now(), LocalDateTime.now().plusDays(1)))
                             .withCreatedAt(LocalDateTime.now())
                             .withUpdatedAt(LocalDateTime.now());
    }

    public static SessionRegistrationBuilder aSessionRegistration() {
        return SessionRegistrationBuilder.aSessionRegistration()
                                         .withSessionRecruitStatus(SessionRecruitStatus.RECRUIT)
                                         .withMaxUserCount(100);
    }

}
