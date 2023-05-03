package com.sparks.api.services;

public interface IRequestServiceTests {

    public void shouldCreateRequest() throws Exception;

    public void shouldFindAllRequests() throws Exception;

    public void shouldFindRequestById() throws Exception;

    public void shouldThrowNotFoundExceptionWhenFindingRequestByIdAndRequestDoesNotExist() throws Exception;

}
