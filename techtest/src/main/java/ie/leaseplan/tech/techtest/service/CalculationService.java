package ie.leaseplan.tech.techtest.service;

import java.util.List;

import ie.leaseplan.tech.techtest.dto.Deal;
import ie.leaseplan.tech.techtest.dto.Event;
import ie.leaseplan.tech.techtest.dto.PaymentResult;

public interface CalculationService {
    List<PaymentResult> calcPaymentResults(List<Event> eventsToCheck, List<Deal> currentDeals);
}
