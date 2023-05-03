package com.sparks.api.services;

import com.sparks.api.entities.Request;
import com.sparks.api.exceptions.NotFoundException;
import com.sparks.api.producers.RequestProducer;
import com.sparks.api.responses.ServiceResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

@SpringBootTest(classes = RequestService.class)
public class RequestServiceTestUnitTests implements IRequestServiceTests {

    @Autowired
    private RequestService requestService;

    @MockBean
    private RequestProducer requestProducer;

    @BeforeEach
    public void Setup() throws Exception {
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

        ServiceResponse<Request> createRequestResponse = new ServiceResponse<>();

        createRequestResponse.setData(requestCreated);

        Mockito.when(requestProducer.createRequest(createRequestInput)).thenReturn(createRequestResponse);

        ///////////////////////
        // Find all requests //
        ///////////////////////

        List<Request> requestsFound = Arrays.asList(previouslyCreatedRequest, requestCreated);

        ServiceResponse<List<Request>> findAllRequestsResponse = new ServiceResponse<>();

        findAllRequestsResponse.setData(requestsFound);

        Mockito.when(requestProducer.findAllRequests()).thenReturn(findAllRequestsResponse);

        ////////////////////////
        // Find request by id //
        ////////////////////////

        Request requestFoundById = new Request(requestCreated);

        ServiceResponse<Request> findRequestByIdResponse = new ServiceResponse<>();

        findRequestByIdResponse.setData(requestFoundById);

        Mockito.when(requestProducer.findRequestById(requestCreated.getId())).thenReturn(findRequestByIdResponse);

        ////////////////////////////////////////////
        // Find request that does not exist by id //
        ////////////////////////////////////////////

        ServiceResponse<Request> findRequestThatDoesNotExistByIdResponse = new ServiceResponse<>();

        Mockito.when(requestProducer.findRequestById("64441357327a68740d94ac28")).thenReturn(findRequestThatDoesNotExistByIdResponse);

    }

    @Test
    @Override
    public void shouldCreateRequest() throws Exception {

        Request createRequestInput = new Request();

        createRequestInput.setUrl("");
        createRequestInput.setMethod("");
        createRequestInput.setBody("");
        createRequestInput.setHeaders("");
        createRequestInput.setCookies("");

        Request requestCreated = this.requestService.createRequest(createRequestInput);

        Assertions.assertNotNull(requestCreated);

    }

    @Test
    @Override
    public void shouldFindAllRequests() throws Exception {

        List<Request> requestsFound = this.requestService.findAllRequests();

        Assertions.assertEquals(requestsFound.size(), 2);

    }

    @Test
    @Override
    public void shouldFindRequestById() throws Exception {

        Request requestFound = this.requestService.findRequestById("64441357327a68740d94ac27");

        Assertions.assertNotNull(requestFound);

    }

    @Test
    @Override
    public void shouldThrowNotFoundExceptionWhenFindingRequestByIdAndRequestDoesNotExist() throws Exception {

        Assertions.assertThrows(NotFoundException.class, () -> {
            this.requestService.findRequestById("64441357327a68740d94ac28");
        });

    }

}
