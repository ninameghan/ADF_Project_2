package com.example.adf_project_2;

import com.example.adf_project_2.entities.Property;
import com.example.adf_project_2.repositories.IPropertyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class AdfProject2ApplicationTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    IPropertyRepository propertyRepository;

    @Test
    void findPropertyByIdExists() throws Exception {
        Property property = new Property("123 Main St", "EIR123", 5, 1500.0);
        given(propertyRepository.findById(1)).willReturn(Optional.of(property));
        mockMvc.perform(get("/properties/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.propertyAddress").value("123 Main St"));
    }

    @Test
    void findPropertyByIdNotExists() throws Exception {
        mockMvc.perform(get("/properties/100"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Property with ID: 100 was not found!"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"MANAGER"})
    void deletePropertyByIdExistAndAuthorised() throws Exception {
        when(propertyRepository.existsById(1)).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/properties/{id}", 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser(username = "user", roles = {"OFFICE"})
    void deletePropertyByIdAuthenticatedNotAuthorized() throws Exception {
        when(propertyRepository.existsById(1)).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/properties/{id}", 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @Test
    void deletePropertyByIdNotAuthenticated() throws Exception {
        when(propertyRepository.existsById(1)).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/properties/{id}", 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }
}
