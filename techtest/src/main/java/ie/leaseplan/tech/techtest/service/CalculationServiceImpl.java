package ie.leaseplan.tech.techtest.service;

import java.util.ArrayList;
import java.util.List;

import ie.leaseplan.tech.techtest.dto.Deal;
import ie.leaseplan.tech.techtest.dto.Event;
import ie.leaseplan.tech.techtest.dto.PaymentResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
@Service
public class CalculationServiceImpl implements CalculationService {
    private static final Logger logger = LoggerFactory.getLogger(CalculationServiceImpl.class);

    @Override
    public List<PaymentResult> calcPaymentResults(List<Event> eventsToCheck, List<Deal> currentDeals) {
        List<PaymentResult> results = new ArrayList<>();
        if (CollectionUtils.isEmpty(eventsToCheck) || CollectionUtils.isEmpty(currentDeals)) {
            logger.debug("Required parameters empty");
        } else {
            for (Event event : eventsToCheck) {
                for (Deal currentDeal : currentDeals) {
                    if (CollectionUtils.isEmpty(currentDeal.getPerils()) ||
                        CollectionUtils.isEmpty(currentDeal.getRegions())) {
                        logger.debug("No perils or regions, ignore this deal");
                    } else if (currentDeal.getPerils().contains(event.getPeril()) &&
                               currentDeal.getRegions().contains(event.getRegion())) {
                        int toPay;
                        int possibleMax = event.getLossFromEvent() - currentDeal.getRetention();
                        toPay = Math.min(possibleMax, currentDeal.getLimit());
                        results.add(PaymentResult.builder()
                                                 .eventId(event.getId())
                                                 .dealId(currentDeal.getId())
                                                 .loss(toPay)
                                                 .build());
                        logger.debug("Event {} affect Deal {} and the reinsurance company's loss is {}", event.getId(),
                                     currentDeal.getId(), toPay);
                    }

                }
            }
        }



        return results;
    }
}
