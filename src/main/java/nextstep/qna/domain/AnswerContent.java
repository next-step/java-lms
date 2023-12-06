package nextstep.qna.domain;

import nextstep.users.domain.NsUser;
import org.springframework.util.StringUtils;

import java.util.Objects;

public class AnswerContent {

    private NsUser writer;

    private String contents;

    public AnswerContent(NsUser writer, String contents) {
        validateAnswerContent(writer, contents);
        this.writer = writer;
        this.contents = contents;
    }

    public NsUser writer() {
        return writer;
    }

    public boolean equalsWriter(NsUser loginUser) {
        return writer.equals(loginUser);
    }

    private void validateAnswerContent(NsUser writer, String contents) {
        if (Objects.isNull(writer)) {
            throw new IllegalArgumentException("작성자는 빈 값일 수 없습니다.");
        }
        if (!StringUtils.hasText(contents)) {
            throw new IllegalArgumentException("내용은 빈 값일 수 없습니다.");
        }
    }
}

