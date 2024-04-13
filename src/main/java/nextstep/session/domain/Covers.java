package nextstep.session.domain;

import java.util.List;

public class Covers {

    private final List<Cover> covers;

    public Covers(List<Cover> covers) {
        this.covers = covers;
    }

    public int size() {
        return this.covers.size();
    }
}
