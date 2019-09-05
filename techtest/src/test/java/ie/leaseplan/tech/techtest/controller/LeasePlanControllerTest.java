package ie.leaseplan.tech.techtest.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class LeasePlanControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void paymentCalc() throws Exception {
        this.mockMvc.perform(post("/").content("[{\"id\" : 1, \"peril\" : 2, \"region\": 1, \"lossFromEvent\": 1000}]").header(
                HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().string(containsString("[{\"eventId\":1,\"dealId\":1,\"loss\":500}]")))
                    .andDo(print());

    }

    @Test
    public void dealList() throws Exception {
        this.mockMvc.perform(get("/deal-list"))
                    .andExpect(status().isOk())
                    .andExpect(content().string(containsString("{\"id\":1,\"retention\":100,\"limit\":500,\"perils\":[2],\"regions\":[1]}")))
                    .andDo(print());
    }
}