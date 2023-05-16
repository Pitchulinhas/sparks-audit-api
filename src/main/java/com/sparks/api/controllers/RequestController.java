package com.sparks.api.controllers;

import com.sparks.api.entities.Request;
import com.sparks.api.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/requests")
public class RequestController {

    @Autowired
    private RequestService requestService;

    @PostMapping
    public Request createRequest(@RequestBody() Request createRequestInput) throws Exception {
        return this.requestService.createRequest(createRequestInput);
    }

    @GetMapping
    public List<Request> findAllRequests() throws Exception {
        return this.requestService.findAllRequests();
    }

    @GetMapping("/{id}")
    public Request findRequestById(@PathVariable("id") String id) throws Exception {
        return this.requestService.findRequestById(id);
    }

}
