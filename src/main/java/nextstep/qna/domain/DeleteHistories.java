package nextstep.qna.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DeleteHistories {

    private final List<DeleteHistory> deleteHistories = new ArrayList<>();

    private DeleteHistories(Question question) {
        addQuestionDeleteHistory(question);
    }

    public static DeleteHistories from(Question question) {

        return new DeleteHistories(question);
    }

    public void addQuestionDeleteHistory(Question question) {
        deleteHistories.add(new DeleteHistory(ContentType.QUESTION, question.getId(), question.getWriter(), LocalDateTime.now()));
        for (Answer answer : question.getAnswers()) {
            deleteHistories.add(new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()));
        }

    }

    public List<DeleteHistory> getDeleteHistories() {
        return deleteHistories;
    }


}
