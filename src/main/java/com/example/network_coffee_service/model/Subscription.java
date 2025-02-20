package com.example.network_coffee_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("subscriptions")
public class Subscription {

    @Id
    private UUID id;
    private UUID subscriberId;
    private UUID targetId;
}
