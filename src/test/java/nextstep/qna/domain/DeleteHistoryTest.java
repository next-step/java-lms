package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;

import java.time.LocalDateTime;

public class DeleteHistoryTest {
    public static final DeleteHistory A1DeleteHistory = new DeleteHistory(ContentType.ANSWER, null, NsUserTest.JAVAJIGI, LocalDateTime.now());
    public static final DeleteHistory A2DeleteHistory = new DeleteHistory(ContentType.ANSWER, null, NsUserTest.SANJIGI, LocalDateTime.now());
    public static final DeleteHistory Q1DeleteHistory = new DeleteHistory(ContentType.QUESTION, 0L, NsUserTest.JAVAJIGI, LocalDateTime.now());
}
