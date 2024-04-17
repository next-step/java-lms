package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class JdbcSelectionRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DataSource dataSource;

    private SelectionRepository selectionRepository;

    @BeforeEach
    void setUp() {
        selectionRepository = new JdbcSelectionRepository(jdbcTemplate, dataSource);
    }

    @Test
    @DisplayName("세션-유저 선발 CRUD")
    void crud() {
        Selection selection = Selection.createNewInstance(1L, 1L);
        Long savedId = selectionRepository.saveAndGetId(selection);

        Optional<Selection> findSelection = selectionRepository.findById(savedId);
        assertThat(findSelection.isPresent()).isTrue();
    }

    @Test
    @DisplayName("세션에 선발된 유저인 지 확인")
    void isExistSelection() {
        // given
        long userId = 1L;
        long sessionId = 1L;
        Selection selection = Selection.createNewInstance(userId, sessionId);

        // when
        Long savedId = selectionRepository.saveAndGetId(selection);
        Payment payment = new Payment("test", userId, sessionId, 1000L);
        Payment unselectedPayment = new Payment("test", userId, 2L, 1000L);

        // then
        assertThat(selectionRepository.isSelected(payment)).isTrue();
        assertThat(selectionRepository.isSelected(unselectedPayment)).isFalse();
    }

}