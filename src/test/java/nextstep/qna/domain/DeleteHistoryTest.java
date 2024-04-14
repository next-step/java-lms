package nextstep.qna.domain;

import java.time.LocalDateTime;

public class DeleteHistoryTest {

    public static final DeleteHistory H1 = new DeleteHistory(ContentType.ANSWER,
        AnswerTest.A1.getId(),
        AnswerTest.A1.getWriter(),
        LocalDateTime.now());

}
