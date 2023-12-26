package nextstep.courses.domain.course.session.apply;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Applies implements Iterable<Apply> {
    private final List<Apply> applies;

    public Applies() {
        this(new ArrayList<>());
    }

    public Applies(List<Apply> applicants) {
        this.applies = applicants;
    }

    public int size() {
        return this.applies.size();
    }

    public List<Apply> getApplies() {
        return applies;
    }

    @Override
    public String toString() {
        return "Applicants{" +
                "applicants=" + applies +
                '}';
    }

    @Override
    public Iterator<Apply> iterator() {
        return this.applies.iterator();
    }
}
