package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class AnswersTest {

    private final Answers AS1 = new Answers();

    private final Answers AS2 = new Answers();
    public static final Answer A1 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    private final Answers AS3 = new Answers();
    public static final Answer A3 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents3");
    public static final Answer A4 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents4");


    @BeforeEach
    void init(){
        AS2.add(A1);
        AS2.add(A2);

        AS3.add(A3);
        AS3.add(A4);
    }

    @DisplayName("빈 값 검증")
    @Test
    void isEmpty(){
        boolean AS1result = AS1.isEmpty();
        boolean AS2result = AS2.isEmpty();

        assertAll(
                ()->assertThat(AS1result).isTrue(),
                ()->assertThat(AS2result).isFalse()
        );
    }

    @DisplayName("답변 전체 삭제 시 히스토리 리스트 반환 사이즈 검증")
    @Test
    void delete(){
        List<DeleteHistory> deleteHistories = AS2.delete();

        assertThat(deleteHistories).hasSize(2);
    }


}
