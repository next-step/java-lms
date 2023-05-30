package nextstep.qna.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DeleteHistories {
	private final List<DeleteHistory> deleteHistories = new ArrayList<>();

	public DeleteHistories() {
	}

	public void add(DeleteHistory deleteHistory) {
		deleteHistories.add(deleteHistory);
	}

	public int size() {
		return deleteHistories.size();
	}

	public void addAll(List<DeleteHistory> deleteHistories) {
		this.deleteHistories.addAll(deleteHistories);
	}

	public List<DeleteHistory> toList() {
		return deleteHistories;
	}
}
