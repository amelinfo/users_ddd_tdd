package com.ameltaleb.users.rest;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.ameltaleb.users.domain.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnUsersFromDatabase() throws Exception {
        mockMvc.perform(get("/users"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray());
    }

   @Disabled
    @Test
    void shouldCreateUserSuccessfully() throws Exception {
        User newUser = new User(null, "Leo", "leo@mail.com", false);

        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newUser)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Leo"))
                .andExpect(jsonPath("$.email").value("leo@mail.com"));
    }

    @Disabled
    @Test
    void shouldReturnConflictIfEmailExists() throws Exception {
        //"ana@mail.com" already exists in data.sql
        User existingUser = new User(null, "Joan", "joan@mail.com", false);

    mockMvc.perform(post("/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(existingUser)))
            .andExpect(status().isCreated());

    // REPOST with the same mail
    mockMvc.perform(post("/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(existingUser)))
            .andExpect(status().isConflict())
            .andExpect(content().string("Email already used: ana@mail.com"));
    }

    @Test
    void shouldActivateUserSuccessfully() throws Exception {
        // Ajoute un utilisateur initialement inactif
        User newUser = new User(null, "InactiveUser", "inactive@mail.com", false);
        
        String json = objectMapper.writeValueAsString(newUser);

        // Création
        String response = mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                        .andExpect(status().isCreated())
                        .andReturn().getResponse().getContentAsString();
        System.out.println("Response: " + response);
        // Récupération de l'id généré
        Long userId = objectMapper.readTree(response).get("id").asLong();

        // Activation
        mockMvc.perform(patch("/users/" + userId + "/activate"))
                .andExpect(status().isOk());
        System.out.println("Response body: " + response);
        // Vérifie que l'utilisateur est maintenant actif
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[?(@.id==" + userId + ")].active").value(true));
    }

    @Test
    void shouldReturnNotFoundIfUserDoesNotExist() throws Exception {
        mockMvc.perform(patch("/users/9999/activate"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("User not found with id: 9999"));
    }
}
