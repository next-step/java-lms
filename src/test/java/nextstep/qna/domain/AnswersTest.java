package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.domain.fixture.QuestionFixture;
import nextstep.users.domain.fixture.NsUserFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswersTest {
    Answer A1;
    Answer A2;
    Answers A1s;
    Answers A1_A2s;

    @BeforeEach
    void setUp() {
        A1 = new Answer(NsUserFixture.JAVAJIGI, QuestionFixture.Q1, "Answers Contents1");
        A2 = new Answer(NsUserFixture.SANJIGI, QuestionFixture.Q1, "Answers Contents2");
        A1s = new Answers(List.of(A1));
        A1_A2s = new Answers(List.of(A1, A2));
    }

    @Test
    @DisplayName("삭제유효성검사 / 로그인사용자와 모든 질문등록자가 같다 / 삭제성공")
    void deleteSameLoginAnswerUser() {
        // when then
        A1s.deleteAll(NsUserFixture.JAVAJIGI);
    }

    @Test
    @DisplayName("삭제유효성검사 / 로그인사용자와 질문등록자가 다르다 / CannotDeleteException")
    void deleteDiffLoginAnswerUser() {
        // when then
        assertThatThrownBy(() -> A1_A2s.deleteAll(NsUserFixture.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }
}