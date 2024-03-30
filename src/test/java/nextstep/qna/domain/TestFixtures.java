package nextstep.qna.domain;

import java.time.LocalDateTime;

public class TestFixtures {

    public static final LocalDateTime FIXED_DATE_TIME = LocalDateTime.of(2010, 1, 1, 13, 45);
    public static final CurrentDateTimeProvider CURRENT_DATE_TIME_PROVIDER = new FixedDateTimeProvider(
        FIXED_DATE_TIME);

    private TestFixtures() {
        throw new AssertionError();
    }

    static class FixedDateTimeProvider implements CurrentDateTimeProvider {

        private final LocalDateTime fixedDateTime;

        public FixedDateTimeProvider(LocalDateTime fixedDateTime) {
            this.fixedDateTime = fixedDateTime;
        }

        @Override
        public LocalDateTime now() {
            return fixedDateTime;
        }
    }

}
