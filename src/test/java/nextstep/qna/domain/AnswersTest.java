package nextstep.qna.domain;

import nextstep.qna.UnAuthorizedException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswersTest {

    List<Answer> answerList;

    @BeforeEach
    void create() {
        answerList = new ArrayList<>();
        answerList.add(new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1"));
    }

    @Test
    @DisplayName("답변 목록 중 질문자와 답변자 다른경우가 있으면 에러를 던짐")
    void delete_답변_중_다른답변자_존재() {
        answerList.add(new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2"));
        Answers answers = new Answers(answerList);

        assertThatThrownBy(() -> answers.delete(NsUserTest.JAVAJIGI, LocalDateTime.now())).isInstanceOf(UnAuthorizedException.class);
    }

    @Test
    @DisplayName("답변 목록을 모두 삭제 상태로 바꾼 후 삭제 히스토리 리스트 리턴")
    void delete_성공() {
        Answers answers = new Answers(answerList);

        assertThat(answers.delete(NsUserTest.JAVAJIGI, LocalDateTime.now()))
                .containsOnly(DeleteHistory.ofAnswer(null, NsUserTest.JAVAJIGI, LocalDateTime.now()));
    }

    @Test
    @DisplayName("새로운 답변을 추가할 수 있는지 확인")
    void add_성공() {
        Answers answers = new Answers(answerList);
        Answers result = answers.add(new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents2"));

        answerList.add(new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents2"));
        Answers matchList = new Answers(answerList);

        assertThat(result).isEqualTo(matchList);
    }

}
