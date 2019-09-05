package ie.leaseplan.tech.techtest.controller;

import java.util.List;

import ie.leaseplan.tech.techtest.dto.Deal;
import ie.leaseplan.tech.techtest.dto.Event;
import ie.leaseplan.tech.techtest.dto.PaymentResult;
import ie.leaseplan.tech.techtest.service.CalculationService;
import ie.leaseplan.tech.techtest.service.DealService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LeasePlanController {
    private static final Logger logger = LoggerFactory.getLogger(LeasePlanController.class);

    private final CalculationService calculationService;
    private final DealService        dealService;

    @Autowired
    public LeasePlanController(CalculationService calculationService, DealService dealService) {
        this.calculationService = calculationService;
        this.dealService = dealService;
    }

    @PostMapping(consumes = { "application/json" })
    @CrossOrigin(origins = "http://localhost:4200")
    public List<PaymentResult> paymentCalc(@RequestBody List<Event> eventsToCheck) {
        List<Deal> currentDeals = this.dealService.getCurrentDeals();
        logger.debug("Test events = {}", eventsToCheck);
        return this.calculationService.calcPaymentResults(eventsToCheck, currentDeals);
    }

    @GetMapping("deal-list")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Deal> dealList() {
        return this.dealService.getCurrentDeals();
    }


}
