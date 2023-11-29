package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.fixture.NsUserFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public Answer A1;
    public Answer A2;
    public Question Q1;
    public Question Q2;

    @BeforeEach
    void setup() {
        Q1 = new Question(NsUserFixture.JAVAJIGI, "title1", "contents1");
        Q2 = new Question(NsUserFixture.SANJIGI, "title2", "contents2");

        A1 = new Answer(NsUserFixture.JAVAJIGI, Q1, "Answers Contents1");
        A2 = new Answer(NsUserFixture.JAVAJIGI, Q1, "Answers Contents2");
    }

    @Test
    @DisplayName("삭제유효성검사 / 로그인사용자와 질문한사람이 같다 / 통과")
    void deleteSameLoginQuestionUser() {
        // expect
        Q1.delete(NsUserFixture.JAVAJIGI);
    }

    @Test
    @DisplayName("삭제유효성검사 / 로그인사용자와 질문한사람이 다르다 / CannotDeleteException")
    void deleteDiffLoginQuestionUser() {
        // expect
        assertThatThrownBy(() -> Q1.delete(NsUserFixture.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("삭제 / Question과 Answer을 모두 삭제한다 / 성공")
    void delete() {
        // given
        Q1.addAnswer(A1);

        // when
        Q1.delete(NsUserFixture.JAVAJIGI);

        // then
        assertThat(Q1.isDeleted()).isTrue();
        assertThat(A1.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("DeleteHistory 생성 / Question과 Answer / 생성")
    void makeDeleteHistories() {
        // given
        Q1.addAnswer(A1);
        Q1.addAnswer(A2);

        // expect
        assertThat(Q1.delete(NsUserFixture.JAVAJIGI)).hasSize(3);
    }
}
