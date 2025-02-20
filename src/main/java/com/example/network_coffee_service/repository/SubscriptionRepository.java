package com.example.network_coffee_service.repository;

import com.example.network_coffee_service.model.Subscription;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;

import java.util.UUID;

public interface SubscriptionRepository extends R2dbcRepository<Subscription, UUID> {

    Flux<Subscription> findBySubscriberId(UUID subscriberId);
    Flux<Subscription> findByTargetId(UUID targetId);
}
