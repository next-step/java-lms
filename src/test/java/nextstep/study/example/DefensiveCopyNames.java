package nextstep.study.example;

import java.util.ArrayList;
import java.util.List;

public class DefensiveCopyNames {
    private final List<Name> names;

    public DefensiveCopyNames(List<Name> names) {
        this.names = new ArrayList<>(names);
    }

    public List<Name> names() {
        return names;
    }
}
