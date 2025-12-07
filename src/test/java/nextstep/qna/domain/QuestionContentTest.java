package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QuestionContentTest {
    public static final QuestionContent QC1 = new QuestionContent("title1", "contents1");
    public static final QuestionContent QC2 = new QuestionContent("title2", "contents2");

    @DisplayName("QuestionContent 생성")
    @Test
    void createQuestionContent() {
        assertThat(QC1.title()).isEqualTo("title1");
        assertThat(QC1.contents()).isEqualTo("contents1");
    }

}