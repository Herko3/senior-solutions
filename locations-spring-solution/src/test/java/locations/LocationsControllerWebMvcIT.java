package locations;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = LocationsController.class)
public class LocationsControllerWebMvcIT {

    @MockBean
    LocationsService service;

    @Autowired
    MockMvc mockMvc;

    @Test
    void testGetLocations() throws Exception {
        when(service.getLocations(any(), any(), any(), any(), any()))
                .thenReturn(List.of(
                        new LocationDto(1, "Eindhoven", 51.4537, 5.4702),
                        new LocationDto(2, "London", 51.5368, -0.1308)
                ));

        mockMvc.perform(get("/api/locations"))
//                .andDo(print());
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", equalTo("Eindhoven")));
    }
}
