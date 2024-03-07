package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.manufacture.expertservice.controller.EvaluationController;
import com.manufacture.expertservice.model.Evaluation;
import com.manufacture.expertservice.service.EvaluationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EvaluationController.class)
public class EvaluationControllerTests {

    public static String baseUrl = "/api/evaluations";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EvaluationService service;
    private Evaluation evaluation;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void init() {
        evaluation = evaluationToTest();

    }

    public Evaluation evaluationToTest() {

        Evaluation evaluation = new Evaluation();
        evaluation.setComment("somecomment");
        evaluation.setPrice("100");
        evaluation.setExpertName("anyname");

        return evaluation;
    }
    @Test
    public void saveTrainingTypeSuccessful() throws Exception {


        ObjectMapper mapper = new ObjectMapper();
        String jsonRequest = mapper.writeValueAsString(evaluation);
        when(service.addEvaluation(ArgumentMatchers.any(Evaluation.class)))
                .thenReturn(evaluationToTest());

        this.mockMvc.perform(MockMvcRequestBuilders
                        .post(baseUrl)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.expertName")
                        .value("anyname"));

    }
}
