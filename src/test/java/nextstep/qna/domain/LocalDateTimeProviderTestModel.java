package nextstep.qna.domain;

import java.time.LocalDateTime;

public class LocalDateTimeProviderTestModel {

    public static final LocalDateTime CREATED_DATE_TIME = LocalDateTime.of(2024,1,1,0,0);
    public static final TimeStrategy LOCAL_DATE_TIME_PROVIDER = new FixedDateTimeProvider(CREATED_DATE_TIME);

    static class FixedDateTimeProvider implements TimeStrategy {
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
