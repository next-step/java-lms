package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;

import java.time.LocalDateTime;

public class DeleteHistoryTest {
    public static final DeleteHistory a1DeleteHistory = new DeleteHistory(ContentType.ANSWER, null, NsUserTest.JAVAJIGI, LocalDateTime.now());
    public static final DeleteHistory a2DeleteHistory = new DeleteHistory(ContentType.ANSWER, null, NsUserTest.SANJIGI, LocalDateTime.now());
}
