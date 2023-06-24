package nextstep.qna.domain;

import nextstep.qna.domain.generator.SimpleIdGenerator;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class AnswersTest {


    private Question question;
    private Answer answer;
    private Answers answers;

    @BeforeEach
    public void initialize() {
        SimpleIdGenerator.initialize();
        question = Question.of(NsUserTest.JAVAJIGI.getUserId(), "title1", "contents1");
        answer = Answer.of(NsUserTest.JAVAJIGI.getUserId(), question.getId(), "Answers Contents1");
        answers = Answers.of(List.of(answer));
    }

    @Test
    @DisplayName("Answers 객체 생성 테스트")
    void Answers_객체_생성_테스트() {
        Answers answers = Answers.of(List.of(answer));

        assertAll(
                () -> assertThat(answers).isInstanceOf(Answers.class),
                () -> assertThat(answers).isNotNull(),
                () -> assertThat(answers).isEqualTo(this.answers)
        );

    }

    @Test
    @DisplayName("답변목록에 다른 답변작성자가 존재하는지 확인")
    void 답변목록에_다른_답변작성자가_존재하는지_확인() {
        answer = Answer.of(NsUserTest.SANJIGI.getUserId(), question.getId(), "Answers Contents1");
        answers = Answers.of(List.of(answer));
        assertThat(answers.hasAnotherOwner(NsUserTest.JAVAJIGI.getUserId())).isTrue();
    }

    @DisplayName("답변 삭제 시 삭제 이력이 반환되는지 확인")
    @Test
    void 삭제_이력() {
        DeleteHistories deleteHistories = answers.removeAll();
        DeleteHistory deleteHistory = DeleteHistory.of(1L, ContentType.ANSWER, answer.getId(), answer.getWriter(), null);

        assertThat(deleteHistories.getDeleteHistories()).containsExactly(deleteHistory);
    }


}