package com.sparks.api.controllers;

public interface IRequestControllerTests {

    public void shouldCreateRequest() throws Exception;

    public void shouldFindAllRequests() throws Exception;

    public void shouldFindRequestById() throws Exception;

    public void shouldReturnNotFoundStatusCodeWhenFindingRequestByIdAndRequestDoesNotExist() throws Exception;

}
