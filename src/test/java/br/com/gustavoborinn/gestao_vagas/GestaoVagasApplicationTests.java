package br.com.gustavoborinn.gestao_vagas;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class GestaoVagasApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void contextLoads() {
    }

    @Test
    void createCandidateReturnsOkForValidPayload() throws Exception {
        String payload = """
                {
                  "name": "Gustavo",
                  "username": "gustavo",
                  "email": "gustavo@example.com",
                  "password": "1234567890",
                  "description": "dev",
                  "curriculum": "cv"
                }
                """;

        mockMvc.perform(post("/candidate/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isOk());
    }

    @Test
    void createCandidateReturnsBadRequestForInvalidPayload() throws Exception {
        String payload = """
                {
                  "name": "Gustavo",
                  "username": "gus tavo",
                  "email": "email-invalido",
                  "password": "123",
                  "description": "dev",
                  "curriculum": "cv"
                }
                """;

        mockMvc.perform(post("/candidate/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }
}
