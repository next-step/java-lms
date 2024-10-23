package nextstep.qna.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DeleteHistories {
	private List<DeleteHistory> deleteHistoryList = new ArrayList<>();

	public void write(Question question, LocalDateTime time) {
		this.deleteHistoryList.add(DeleteHistory.from(question,time));
		Answers answers = question.getAnswers();
		for (Answer answer : answers.getAnswers()) {
			this.deleteHistoryList.add(DeleteHistory.from(answer,time));
		}
	}

	public List<DeleteHistory> getDeleteHistoryList() {
		return new ArrayList<>(deleteHistoryList);
	}
}
