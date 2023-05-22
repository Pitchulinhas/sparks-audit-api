package com.sparks.api.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Request {

    private String id;
    private String url;
    private String method;
    private String body;
    private String headers;
    private String cookies;

    public Request(Request request) {
        this.id = request.id;
        this.url = request.url;
        this.method = request.method;
        this.body = request.body;
        this.headers = request.headers;
        this.cookies = request.cookies;
    }

}
