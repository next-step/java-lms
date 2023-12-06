package nextstep.qna.domain;

import nextstep.users.domain.NsUser;
import org.springframework.util.StringUtils;

import java.util.Objects;

public class QuestionContent {

    private String title;

    private String contents;

    private NsUser writer;

    public QuestionContent(NsUser writer, String title, String contents) {
        validateQuestionContent(writer, title, contents);
        this.writer = writer;
        this.title = title;
        this.contents = contents;
    }

    public NsUser writer() {
        return writer;
    }

    public String title() {
        return title;
    }

    public boolean equalsWriter(NsUser loginUser) {
        return writer.equals(loginUser);
    }

    private void validateQuestionContent(NsUser writer, String title, String contents) {
        if (Objects.isNull(writer)) {
            throw new IllegalArgumentException("작성자는 빈 값일 수 없습니다.");
        }

        if (!StringUtils.hasText(title)) {
            throw new IllegalArgumentException("제목은 빈 값일 수 없습니다.");
        }

        if (!StringUtils.hasText(contents)) {
            throw new IllegalArgumentException("내용은 빈 값일 수 없습니다.");
        }
    }
}

