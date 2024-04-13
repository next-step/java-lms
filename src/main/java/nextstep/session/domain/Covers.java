package nextstep.session.domain;

import nextstep.common.domain.DeleteHistoryTargets;
import nextstep.users.domain.NsUser;

import java.util.List;

public class Covers {

    private final List<Cover> covers;

    public Covers(List<Cover> covers) {
        this.covers = covers;
    }

    public int size() {
        return this.covers.size();
    }

    public DeleteHistoryTargets deleteAll(NsUser requestUser) {
        DeleteHistoryTargets deleteHistoryTargets = new DeleteHistoryTargets();
        for (Cover cover : covers) {
            deleteHistoryTargets.addFirst(cover.delete(requestUser));
        }
        return deleteHistoryTargets;
    }

    public List<Cover> asList() {
        return List.copyOf(this.covers);
    }
}
