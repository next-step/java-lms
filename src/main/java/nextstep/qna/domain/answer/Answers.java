package nextstep.qna.domain.answer;

import nextstep.qna.domain.Question;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Answers {
    private final List<Answer> values = new ArrayList<>();

    public Answers() {
    }

    public void add(Answer answer, Question question) {
        answer.toQuestion(question);
        values.add(answer);
    }

    public List<Answer> values() {
        return Collections.unmodifiableList(this.values);
    }

    public boolean isAllSameAnswererWith(NsUser requestUser) {
        return this.values.stream()
                .allMatch(answer -> answer.isOwner(requestUser));
    }
}
