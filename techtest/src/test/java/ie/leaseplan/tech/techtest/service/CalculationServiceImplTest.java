package ie.leaseplan.tech.techtest.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import ie.leaseplan.tech.techtest.dto.Deal;
import ie.leaseplan.tech.techtest.dto.Event;
import ie.leaseplan.tech.techtest.dto.PaymentResult;
import org.assertj.core.groups.Tuple;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class CalculationServiceImplTest {
    private static final Logger             logger = LoggerFactory.getLogger(CalculationServiceImplTest.class);
    private              CalculationService calculationService;

    @Before
    public void setUp() {
        this.calculationService = new CalculationServiceImpl();
    }

    @Test
    public void calcPaymentResultsBothNull() {
        // Given
        // When
        List<PaymentResult> result = this.calculationService.calcPaymentResults(null, null);
        // Then
        logger.debug("Result = '{}'", result);
        assertThat(result).isEmpty();
    }

    @Test
    public void calcPaymentResultsEventsNull() {
        // Given
        List<Deal> deals = Collections.singletonList(Deal.builder().build());
        // When
        List<PaymentResult> result = this.calculationService.calcPaymentResults(null, deals);
        // Then
        logger.debug("Result = '{}'", result);
        assertThat(result).isEmpty();
    }

    @Test
    public void calcPaymentResultsDealsNull() {
        // Given
        List<Event> events = Collections.singletonList(Event.builder().build());
        // When
        List<PaymentResult> result = this.calculationService.calcPaymentResults(events, null);
        // Then
        logger.debug("Result = '{}'", result);
        assertThat(result).isEmpty();
    }

    @Test
    public void calcPaymentResultsBothBlank() {
        // Given
        List<Event> events = Collections.singletonList(Event.builder().build());
        List<Deal> deals = Collections.singletonList(Deal.builder().build());
        // When
        List<PaymentResult> result = this.calculationService.calcPaymentResults(events, deals);
        // Then
        logger.debug("Result = '{}'", result);
        assertThat(result).isEmpty();
    }

    @Test
    public void calcPaymentResults() {
        // Given
        List<Event> events = Arrays.asList(Event.builder().id(1).peril(2).region(1).lossFromEvent(1000).build(),
                                           Event.builder().id(2).peril(3).region(2).lossFromEvent(500).build(),
                                           Event.builder().id(3).peril(3).region(3).lossFromEvent(750).build(),
                                           Event.builder().id(4).peril(1).region(3).lossFromEvent(2000).build());
        List<Deal> deals = Arrays.asList(Deal.builder()
                                             .id(1)
                                             .retention(100)
                                             .limit(500)
                                             .perils(Collections.singletonList(2))
                                             .regions(Collections.singletonList(1))
                                             .build(), Deal.builder()
                                                           .id(2)
                                                           .retention(1000)
                                                           .limit(3000)
                                                           .perils(Collections.singletonList(1))
                                                           .regions(Collections.singletonList(3))
                                                           .build(), Deal.builder()
                                                                         .id(3)
                                                                         .retention(250)
                                                                         .limit(250)
                                                                         .perils(Arrays.asList(1, 3))
                                                                         .regions(Arrays.asList(2, 3))
                                                                         .build());
        // When
        List<PaymentResult> result = this.calculationService.calcPaymentResults(events, deals);
        // Then
        logger.debug("Result = '{}'", result);
        assertThat(result).isNotEmpty();
        assertThat(result).extracting("eventId", "dealId", "loss")
                          .contains(Tuple.tuple(1, 1, 500), Tuple.tuple(2, 3, 250), Tuple.tuple(3, 3, 250),
                                    Tuple.tuple(4, 2, 1000));
    }
}