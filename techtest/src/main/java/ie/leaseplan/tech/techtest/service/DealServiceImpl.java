package ie.leaseplan.tech.techtest.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import ie.leaseplan.tech.techtest.dto.Deal;
import org.springframework.stereotype.Service;

@Service
public class DealServiceImpl implements DealService {

    @Override
    public List<Deal> getCurrentDeals() {
        ArrayList<Deal> deals = new ArrayList<>();
        deals.add(Deal.builder()
                      .id(1)
                      .retention(100)
                      .limit(500)
                      .perils(Collections.singletonList(2))
                      .regions(Collections.singletonList(1))
                      .build());
        deals.add(Deal.builder()
                      .id(2)
                      .retention(1000)
                      .limit(3000)
                      .perils(Collections.singletonList(1))
                      .regions(Collections.singletonList(3))
                      .build());
        deals.add(Deal.builder()
                      .id(3)
                      .retention(250)
                      .limit(250)
                      .perils(Arrays.asList(1, 3))
                      .regions(Arrays.asList(2, 3))
                      .build());
        return deals;
    }
}
