package com.example.restapplicationjpa.survivor;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(SecurityConfig.class)
public class SurvivorControllerTest {

    private MockMvc mockMvc;
    @InjectMocks
    private SurvivorController survivorController;
    @Mock
    private SurvivorService survivorService;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(survivorController).build();
    }

    @WithMockUser(value = "Pandano")
    @Test
    public void getAllSurvivors() throws Exception {
        List<SurvivorDTO> survivorDTOList = new ArrayList<>();
        SurvivorDTO survivorDTO = new SurvivorDTO();
        survivorDTO.setId(9);
        survivorDTO.setFirstName("no");
        survivorDTO.setLastName("yes");
        survivorDTOList.add(survivorDTO);
        when(survivorService.getAllSurvivors()).thenReturn(survivorDTOList);

        mockMvc.perform(MockMvcRequestBuilders.get("/survivors")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", Matchers.is(9)))
                .andExpect(jsonPath("$[0].firstName", Matchers.is(survivorDTO.getFirstName())))
                .andExpect(jsonPath("$[0].lastName", Matchers.is(survivorDTO.getLastName())))
                .andReturn();
    }

    @WithMockUser(value = "Pandano")
    @Test
    public void getSurvivorById() throws Exception {

        when(survivorService.getSurvivorById(9)).thenThrow(new SurvivorNotFoundException(9L));

        mockMvc.perform(MockMvcRequestBuilders.get("/survivors" + "9")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(HttpStatus.NOT_FOUND.value()))
                .andReturn();
    }


}