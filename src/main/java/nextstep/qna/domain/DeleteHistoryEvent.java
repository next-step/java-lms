package nextstep.qna.domain;

import nextstep.qna.service.QnAService;
import org.springframework.context.ApplicationEvent;

import java.util.Objects;

public class DeleteHistoryEvent extends ApplicationEvent {

    private final Question question;

    public DeleteHistoryEvent(QnAService qnAService, Question question) {
        super(qnAService);
        this.question = question;
    }

    public Question getQuestion() {
        return this.question;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeleteHistoryEvent that = (DeleteHistoryEvent) o;
        return Objects.equals(question, that.question);
    }

    @Override
    public int hashCode() {
        return Objects.hash(question);
    }
}
