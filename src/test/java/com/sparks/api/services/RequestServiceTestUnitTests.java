package com.sparks.api.services;

import com.sparks.api.producers.RequestProducer;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

public class RequestServiceTestUnitTests implements IRequestServiceTests {

    @Autowired
    private RequestService requestService;

    @MockBean
    private RequestProducer requestProducer;

    @BeforeEach
    public void Setup() throws Exception {

        ////////////////////
        // Create request //
        ////////////////////

        ///////////////////////
        // Find all requests //
        ///////////////////////

        ////////////////////////
        // Find request by id //
        ////////////////////////

        ////////////////////////////////////////////
        // Find request that does not exist by id //
        ////////////////////////////////////////////

        //////////////////////////
        // Update request by id //
        //////////////////////////

        //////////////////////////////////////////////
        // Update request that does not exist by id //
        //////////////////////////////////////////////

        //////////////////////////
        // Delete request by id //
        //////////////////////////

        //////////////////////////////////////////////
        // Delete request that does not exist by id //
        //////////////////////////////////////////////

    }

    @Override
    public void shouldCreateRequest() throws Exception {

    }

    @Override
    public void shouldFindAllRequests() throws Exception {

    }

    @Override
    public void shouldFindRequestById() throws Exception {

    }

    @Override
    public void shouldThrowNotFoundExceptionWhenFindingRequestByIdAndRequestDoesNotExist() throws Exception {

    }

    @Override
    public void shouldUpdateRequestById() throws Exception {

    }

    @Override
    public void shouldThrowNotFoundExceptionWhenUpdatingRequestByIdAndRequestDoesNotExist() throws Exception {

    }

    @Override
    public void shouldDeleteRequestById() throws Exception {

    }

    @Override
    public void shouldThrowNotFoundExceptionWhenDeletingRequestByIdAndRequestDoesNotExist() throws Exception {

    }
}
