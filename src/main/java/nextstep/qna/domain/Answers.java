package nextstep.qna.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class Answers {
    private final List<Answer> list;

    public Answers(List<Answer> list) {
        this.list = list;
    }

    public void add(Answer answer) {
        list.add(answer);
    }

    public List<Answer> getAnswers() {
        return list;
    }

    public List<DeleteHistory> deleteAll() {
        return list.stream()
                .peek(Answer::isDeletedStatus)
                .map(answer -> new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()))
                .collect(Collectors.toList());
    }
}
