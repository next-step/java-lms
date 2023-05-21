package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    void 질문_삭제() throws CannotDeleteException {

        // when
        Q2.delete(NsUserTest.SANJIGI);

        // then
        assertThat(Q2.isDeleted()).isTrue();
    }

    @Test
    void 질문_삭제_후_DeleteHistory에_기록된다() throws CannotDeleteException {

        // when
        List<DeleteHistory> deleteHistories = Q2.delete(NsUserTest.SANJIGI);

        // then
        assertThat(deleteHistories).contains(new DeleteHistory(ContentType.QUESTION, Q2.getId(), NsUserTest.SANJIGI, LocalDateTime.now()));
    }

    @Test
    void 로그인_유저와_질문자_불일치() throws CannotDeleteException {

        assertThatThrownBy(() -> Q2.delete(NsUser.GUEST_USER))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("질문을 삭제할 권한이 없습니다.");
    }
}
