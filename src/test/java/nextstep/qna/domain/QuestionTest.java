package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static nextstep.qna.domain.AnswerTest.A1;
import static nextstep.qna.domain.AnswerTest.A2;
import static org.assertj.core.api.Assertions.*;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");
    private Question Q1_CLONE;
    @BeforeEach
    void setUp() {
        Q1_CLONE = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    }

    @Test
    @DisplayName("질문자가 로그인한 본인이 아닐 경우에 삭제가 불가능")
    void is_not_login_user() {
        assertThatThrownBy(() -> {
            Q1_CLONE.delete(NsUserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("질문자와 다른 사람이 작성한 답변글이 있을 경우 삭제가 불가능")
    void has_other_user_answer() {
        assertThatThrownBy(() -> {
            Q1_CLONE.addAnswer(A1);
            Q1_CLONE.addAnswer(A2);
            Q1_CLONE.delete(NsUserTest.JAVAJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("질문자가 본인이며 답변이 없으면 삭제가 가능")
    void owner_and_has_no_answers() {
        Q1_CLONE.delete(NsUserTest.JAVAJIGI);
        assertThat(new Question(NsUserTest.JAVAJIGI, "title1", "contents1", true, new ArrayList<>())).isEqualTo(Q1_CLONE);
    }

    @Test
    @DisplayName("답변이 있으며 질문자와 답변글이 모두 로그인한 사용자이면 삭제가 가능")
    void owner_as_has_owned_answers() {
        Q1_CLONE.addAnswer(A1);
        Q1_CLONE.delete(NsUserTest.JAVAJIGI);
        assertThat(Q1_CLONE).isEqualTo(new Question(NsUserTest.JAVAJIGI, "title1", "contents1", true, List.of(A1)));
    }

    @Test
    @DisplayName("질문 삭제 이력에 대한 정보를 DeleteHistory를 활용해 남긴다.")
    void check_delete_history() {
        Q1_CLONE.delete(NsUserTest.JAVAJIGI);
        assertThat(Q1_CLONE.generateDeleteHistory()).isEqualTo(List.of(new DeleteHistory(ContentType.QUESTION, 0L, NsUserTest.JAVAJIGI)));
    }
}
