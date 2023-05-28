package nextstep.qna.domain;

import static java.time.LocalDateTime.now;
import static nextstep.qna.domain.ContentType.ANSWER;
import static nextstep.users.domain.NsUserTest.JAVAJIGI;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import nextstep.users.domain.NsUserTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AnswersTest {

    public static final Answers ANSWERS = new Answers(Arrays
            .asList(AnswerTest.JAVAJIGI_ANSWER,AnswerTest.SANJIGI_ANSWER));

    public static final Answers SINGLE_JAVAJIGI_ANSWERS = new Answers(
            List.of(AnswerTest.JAVAJIGI_ANSWER));

    private Answers singleAnswer;

    @BeforeEach
    void init() {
        singleAnswer = new Answers(
                List.of(new Answer(JAVAJIGI, QuestionTest.JAVAJIGI_QUESTION, "Answers Contents1")));
    }

    @Test
    @DisplayName("질문 목록중 로그인 유저와 다른 유저가 배열에 존재할 경우 true 를 리턴한다.")
    void checkHasOtherUser() {
        assertThat(SINGLE_JAVAJIGI_ANSWERS.hasOtherUserAnswer(NsUserTest.SANJIGI))
                .isTrue();
    }

    @Test
    @DisplayName("질문 전체를 삭제했을 경우 Answer 의 삭제값이 전체 True 로 상태값이 변경된다.")
    void deleteAllTest() {
        ANSWERS.deleteAll();

        final Optional<Answer> notDeletedAnswer = ANSWERS.immutableGet()
                .stream()
                .filter(answer -> !answer.isDeleted())
                .findFirst();

        assertThat(notDeletedAnswer.isEmpty())
                .isTrue();
    }

    @Test
    @DisplayName("삭제되지 않은 답변에 대해 서는 비어있는 내역을 반환한다.")
    void historiesNotYetDeletedTest() {
        assertThat(singleAnswer.deleteHistories().isEmpty())
                .isTrue();
    }

    @Test
    @DisplayName("답변에 대해 삭제 내역을 리턴한다.")
    void deleteHistoriesTest() {
        singleAnswer.deleteAll();
        assertThat(singleAnswer.deleteHistories())
                .isEqualTo(new ArrayList<>(List.of(
                        new DeleteHistory(ANSWER,null,JAVAJIGI, now())
                )));
    }
}
