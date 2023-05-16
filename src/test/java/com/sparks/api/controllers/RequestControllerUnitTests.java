package com.sparks.api.controllers;

import com.google.gson.Gson;
import com.sparks.api.entities.Request;
import com.sparks.api.exceptions.NotFoundException;
import com.sparks.api.responses.ServiceResponse;
import com.sparks.api.services.RequestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RequestController.class)
public class RequestControllerUnitTests implements IRequestControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RequestService requestService;

    private Gson gson;

    public RequestControllerUnitTests() {
        this.gson = new Gson();
    }

    @BeforeEach
    public void setup() throws Exception {
        Request previouslyCreatedRequest = new Request();

        previouslyCreatedRequest.setId("64441357327a68740d94ac26");
        previouslyCreatedRequest.setUrl("");
        previouslyCreatedRequest.setMethod("");
        previouslyCreatedRequest.setBody("");
        previouslyCreatedRequest.setHeaders("");
        previouslyCreatedRequest.setCookies("");

        ////////////////////
        // Create request //
        ////////////////////

        Request createRequestInput = new Request();

        createRequestInput.setUrl("");
        createRequestInput.setMethod("");
        createRequestInput.setBody("");
        createRequestInput.setHeaders("");
        createRequestInput.setCookies("");

        Request requestCreated = new Request(createRequestInput);

        requestCreated.setId("64441357327a68740d94ac27");

        Mockito.when(requestService.createRequest(createRequestInput)).thenReturn(requestCreated);

        ///////////////////////
        // Find all requests //
        ///////////////////////

        List<Request> requestsFound = Arrays.asList(previouslyCreatedRequest, requestCreated);

        Mockito.when(requestService.findAllRequests()).thenReturn(requestsFound);

        ////////////////////////
        // Find request by id //
        ////////////////////////

        Request requestFoundById = new Request(requestCreated);

        Mockito.when(requestService.findRequestById(requestCreated.getId())).thenReturn(requestFoundById);

        ////////////////////////////////////////////
        // Find request that does not exist by id //
        ////////////////////////////////////////////

        Mockito.when(requestService.findRequestById("64441357327a68740d94ac28")).thenThrow(new NotFoundException("Request não encontrada"));

    }

    @Test
    @Override
    public void shouldCreateRequest() throws Exception {
        Request createRequestInput = new Request();

        createRequestInput.setBody("");
        createRequestInput.setHeaders("");
        createRequestInput.setCookies("");
        createRequestInput.setMethod("");
        createRequestInput.setUrl("");

        this.mockMvc
                .perform(post("/requests").contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(createRequestInput)))
                .andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue()));
    }

    @Test
    @Override
    public void shouldFindAllRequests() throws Exception {
        this.mockMvc.perform(get("/requests").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    @Override
    public void shouldFindRequestById() throws Exception {
        this.mockMvc.perform(get("/requests/{id}", "64441357327a68740d94ac27").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue()));
    }

    @Test
    @Override
    public void shouldReturnNotFoundStatusCodeWhenFindingRequestByIdAndRequestDoesNotExist() throws Exception {
        this.mockMvc.perform(get("/requests/{id}", "64441357327a68740d94ac28").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound()).andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.error", notNullValue()))
                .andExpect(jsonPath("$.error", is("Request não encontrada")));
    }
}
