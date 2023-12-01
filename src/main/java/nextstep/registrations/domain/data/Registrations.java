package nextstep.registrations.domain.data;

import java.util.ArrayList;
import java.util.List;

public class Registrations {

    List<Registration> registrations;

    public Registrations(List<Registration> registrations) {
        this.registrations = new ArrayList<>(registrations);
    }

    public int size() {
        return registrations.size();
    }
}
