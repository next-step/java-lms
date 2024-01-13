package nextstep.qna.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DeleteHistories {
	private List<DeleteHistory> deleteHistories;

	public DeleteHistories() {
		this.deleteHistories = new ArrayList<>();
	}

	public List<DeleteHistory> add(DeleteHistory deleteHistory) {
		this.deleteHistories.add(deleteHistory);
		return this.deleteHistories;
	}

	public List<DeleteHistory> addAll(Answers answers) {
		this.deleteHistories.addAll(answers.deleteAll());
		return this.deleteHistories;
	}
}
