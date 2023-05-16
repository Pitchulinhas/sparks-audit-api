package com.sparks.api.producers;

import com.google.gson.Gson;
import com.sparks.api.entities.Request;
import com.sparks.api.responses.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.kafka.requestreply.RequestReplyTypedMessageFuture;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class RequestProducer {
    @Value("${spring.kafka.topics.request.create}")
    private String createRequestTopic;
    @Value("${spring.kafka.reply-topics.request.create}")
    private String createRequestReplyTopic;
    @Value("${spring.kafka.topics.request.find-all}")
    private String findAllRequestsTopic;
    @Value("${spring.kafka.reply-topics.request.find-all}")
    private String findAllRequestsReplyTopic;
    @Value("${spring.kafka.topics.request.find-by-id}")
    private String findRequestByIdTopic;
    @Value("${spring.kafka.reply-topics.request.find-by-id}")
    private String findRequestByIdReplyTopic;

    @Autowired
    private ReplyingKafkaTemplate<String, String, String> kafkaTemplate;

    private Gson gson;

    public RequestProducer() {
        this.gson = new Gson();
    }

    public ServiceResponse<Request> createRequest(Request createRequestInput) throws Exception {
        Message<String> message = MessageBuilder.withPayload(this.gson.toJson(createRequestInput))
                .setHeader(KafkaHeaders.TOPIC, createRequestTopic)
                .setHeader(KafkaHeaders.REPLY_TOPIC, createRequestReplyTopic).build();

        ParameterizedTypeReference<ServiceResponse<Request>> messageReturnType = new ParameterizedTypeReference<ServiceResponse<Request>>() {
        };

        RequestReplyTypedMessageFuture<String, String, ServiceResponse<Request>> requestFut = kafkaTemplate.sendAndReceive(message, messageReturnType);

        requestFut.getSendFuture().get(10, TimeUnit.SECONDS);

        Message<ServiceResponse<Request>> typedRequest = requestFut.get(30, TimeUnit.SECONDS);

        return typedRequest.getPayload();
    }
    public ServiceResponse<List<Request>> findAllRequests() throws Exception {
        Message<String> message = MessageBuilder.withPayload("getProducts")
                .setHeader(KafkaHeaders.TOPIC, findAllRequestsTopic)
                .setHeader(KafkaHeaders.REPLY_TOPIC, findAllRequestsReplyTopic).build();

        ParameterizedTypeReference<ServiceResponse<List<Request>>> messageReturnType = new ParameterizedTypeReference<ServiceResponse<List<Request>>>() {
        };

        RequestReplyTypedMessageFuture<String, String, ServiceResponse<List<Request>>> requestsFut = kafkaTemplate.sendAndReceive(message, messageReturnType);

        requestsFut.getSendFuture().get(10, TimeUnit.SECONDS);

        Message<ServiceResponse<List<Request>>> typedRequests = requestsFut.get(30, TimeUnit.SECONDS);

        return typedRequests.getPayload();
    }

    public ServiceResponse<Request> findRequestById(String id) throws Exception {
        Message<String> message = MessageBuilder.withPayload(id)
                .setHeader(KafkaHeaders.TOPIC, findRequestByIdTopic)
                .setHeader(KafkaHeaders.REPLY_TOPIC, findRequestByIdReplyTopic).build();

        ParameterizedTypeReference<ServiceResponse<Request>> messageReturnType = new ParameterizedTypeReference<ServiceResponse<Request>>() {
        };

        RequestReplyTypedMessageFuture<String, String, ServiceResponse<Request>> requestFut = kafkaTemplate.sendAndReceive(message, messageReturnType);

        requestFut.getSendFuture().get(10, TimeUnit.SECONDS);

        Message<ServiceResponse<Request>> typedRequest = requestFut.get(30, TimeUnit.SECONDS);

        return typedRequest.getPayload();
    }
}
