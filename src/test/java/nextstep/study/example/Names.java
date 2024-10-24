package nextstep.study.example;

import java.util.List;

public class Names {
    private final List<Name> names;

    public Names(List<Name> names) {
        this.names = names;
    }

    public List<Name> names() {
        return names;
    }
}
