package nextstep.courses.domain.model;

import java.util.*;

public enum ApplicantStatus {
    NOT_APPLIED,
    APPLIED,
    SELECTED,
    APPROVED,
    CANCELLED;

    private static final Map<ApplicantStatus, Set<ApplicantStatus>> ALLOWED_TRANSITIONS;

    static {
        Map<ApplicantStatus, Set<ApplicantStatus>> transitions = new EnumMap<>(ApplicantStatus.class);
        for (ApplicantStatus status : values()) {
            transitions.put(status, EnumSet.noneOf(ApplicantStatus.class));
        }

        transitions.put(NOT_APPLIED, EnumSet.of(APPLIED));
        transitions.put(APPLIED, EnumSet.of(SELECTED, CANCELLED));
        transitions.put(SELECTED, EnumSet.of(APPROVED));

        ALLOWED_TRANSITIONS = Collections.unmodifiableMap(transitions);
    }

    public boolean canChangeTo(ApplicantStatus to) {
        return ALLOWED_TRANSITIONS.get(this).contains(to);
    }

}
