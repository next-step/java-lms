package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class QuestionTest {

    private Question javajigiQuestion;
    private Question sanjigiQuestion;

    @BeforeEach
    void setUp() {
        javajigiQuestion = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        sanjigiQuestion = new Question(NsUserTest.SANJIGI, "title2", "contents2");
    }

    @Test
    @DisplayName("로그인 사용자와 질문한 사람이 같을 경우 삭제가 가능하다.")
    void test01() {
        javajigiQuestion.delete(NsUserTest.JAVAJIGI);
        assertThat(javajigiQuestion.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("로그인 사용자와 질문한 사람이 다를 경우 삭제가 불가능하다.")
    void test02() {
        assertThatThrownBy(() -> sanjigiQuestion.delete(NsUserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("질문 삭제시 이력에 대한 정보를 DeleteHistory를 활용해 반환한다.")
    void test03() {
        List<DeleteHistory> deleteHistories = javajigiQuestion.delete((NsUserTest.JAVAJIGI));
        assertThat(deleteHistories).hasSize(1);
    }

    @Test
    @DisplayName("질문 삭제시 답변이 존재한다면 질문과 답변에 대한 정보를 DeleteHistory를 활용해 반환한다.")
    void test04() {
        Answer javajigiAnswer1 = new Answer(NsUserTest.JAVAJIGI, javajigiQuestion, "Answers Contents1");
        Answer javajigiAnswer2 = new Answer(NsUserTest.JAVAJIGI, javajigiQuestion, "Answers Contents2");
        javajigiQuestion.addAnswer(javajigiAnswer1);
        javajigiQuestion.addAnswer(javajigiAnswer2);

        List<DeleteHistory> deleteHistories = javajigiQuestion.delete((NsUserTest.JAVAJIGI));
        assertThat(deleteHistories).hasSize(3);
    }

}
