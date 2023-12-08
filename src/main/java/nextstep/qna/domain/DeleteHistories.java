package nextstep.qna.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeleteHistories {

    private final List<DeleteHistory> deleteHistories;

    public DeleteHistories(final Question question) {
        this.deleteHistories = create(question);
    }

    public List<DeleteHistory> getDeleteHistories() {
        return Collections.unmodifiableList(deleteHistories);
    }

    private List<DeleteHistory> create(final Question question) {
        ArrayList<DeleteHistory> deleteHistories = new ArrayList<>();

        deleteHistories
            .add(new DeleteHistory(ContentType.QUESTION, question));

        question.getAnswers()
            .getAnswers()
            .stream()
            .forEach(answer -> deleteHistories.add(new DeleteHistory(ContentType.ANSWER, answer)));

        return deleteHistories;
    }
}
