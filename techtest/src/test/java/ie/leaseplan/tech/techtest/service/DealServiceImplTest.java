package ie.leaseplan.tech.techtest.service;

import java.util.List;

import ie.leaseplan.tech.techtest.dto.Deal;
import org.assertj.core.groups.Tuple;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class DealServiceImplTest {
    private static final Logger logger = LoggerFactory.getLogger(DealServiceImplTest.class);
    private DealService dealService;

    @Before
    public void setUp() {
        this.dealService = new DealServiceImpl();
    }

    @Test
    public void getCurrentDeals() {
        // Given
        // When
        List<Deal> result = this.dealService.getCurrentDeals();
        // Then
        logger.debug("Result = '{}'", result);
        assertThat(result).isNotEmpty();
        assertThat(result).extracting("id", "retention", "limit")
                          .contains(Tuple.tuple(1, 100, 500), Tuple.tuple(2, 1000, 3000), Tuple.tuple(3, 250, 250));

    }
}