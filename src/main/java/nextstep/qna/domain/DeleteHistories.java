package nextstep.qna.domain;

import java.util.ArrayList;
import java.util.List;

public class DeleteHistories {
	private List<DeleteHistory> deleteHistories = new ArrayList<>();

	public List<DeleteHistory> add(DeleteHistory deleteHistory) {
		this.deleteHistories.add(deleteHistory);
		return deleteHistories;
	}
}
