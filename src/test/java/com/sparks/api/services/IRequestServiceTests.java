package com.sparks.api.services;

public interface IRequestServiceTests {

    public void shouldCreateRequest() throws Exception;

    public void shouldFindAllRequests() throws Exception;

    public void shouldFindRequestById() throws Exception;

    public void shouldThrowNotFoundExceptionWhenFindingRequestByIdAndRequestDoesNotExist() throws Exception;

    public void shouldUpdateRequestById() throws Exception;

    public void shouldThrowNotFoundExceptionWhenUpdatingRequestByIdAndRequestDoesNotExist() throws Exception;

    public void shouldDeleteRequestById() throws Exception;

    public void shouldThrowNotFoundExceptionWhenDeletingRequestByIdAndRequestDoesNotExist() throws Exception;

}
