package com.example.restapplicationjpa.survivor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(SecurityConfig.class)

public class SurvivorServiceTest {
    @InjectMocks
    private SurvivorService survivorService;
    @Mock
    private SurvivorRepository survivorRepository;

    @WithMockUser(value = "Pandano")
    @Test
    public void giveanEntity_getSurvivorbyId_shouldReturnValidDto() {
        SurvivorDTO survivorDTO = new SurvivorDTO();
        survivorDTO.setId(3);
        Survivor survivor = new Survivor();
        survivor.setId(3);
        when(survivorRepository.findById((long) 3)).thenReturn(Optional.of(survivor));

        SurvivorDTO survivorById = survivorService.getSurvivorById(3);

        assertEquals(survivorDTO.getId(), survivorById.getId());

    }

}