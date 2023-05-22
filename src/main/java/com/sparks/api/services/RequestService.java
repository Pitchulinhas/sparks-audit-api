package com.sparks.api.services;

import com.sparks.api.entities.Request;
import com.sparks.api.exceptions.BadRequestException;
import com.sparks.api.exceptions.NotFoundException;
import com.sparks.api.producers.RequestProducer;
import com.sparks.api.responses.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestService {

    @Autowired
    private RequestProducer requestProducer;

    public Request createRequest(Request createRequestInput) throws Exception {
        ServiceResponse<Request> response = this.requestProducer.createRequest(createRequestInput);

        if (response.getErrorMessage() != null) {
            throw new BadRequestException(response.getErrorMessage());
        }

        return response.getData();
    }

    public List<Request> findAllRequests() throws Exception {
        ServiceResponse<List<Request>> response = this.requestProducer.findAllRequests();

        if (response.getErrorMessage() != null) {
            throw new BadRequestException(response.getErrorMessage());
        }

        return response.getData();
    }

    public Request findRequestById(String id) throws Exception {
        ServiceResponse<Request> response = this.requestProducer.findRequestById(id);

        if (response.getErrorMessage() != null) {
            throw new BadRequestException(response.getErrorMessage());
        }

        if (response.getData() == null) {
            throw new NotFoundException("Request não encontrada");
        }

        return response.getData();
    }

}
