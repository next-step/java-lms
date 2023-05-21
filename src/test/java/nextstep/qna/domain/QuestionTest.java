package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    void 질문_삭제() {

        // when
        Q2.delete();

        // then
        Assertions.assertThat(Q2.isDeleted()).isTrue();
    }

    @Test
    void 질문_삭제_후_DeleteHistory에_기록된다() {

        // when
        List<DeleteHistory> deleteHistories = Q2.delete();

        // then
        Assertions.assertThat(deleteHistories).contains(new DeleteHistory(ContentType.QUESTION, Q2.getId(), NsUserTest.SANJIGI, LocalDateTime.now()));
    }
}
