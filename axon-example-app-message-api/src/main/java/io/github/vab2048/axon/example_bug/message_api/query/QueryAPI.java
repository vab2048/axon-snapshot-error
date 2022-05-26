package io.github.vab2048.axon.example_bug.message_api.query;

import java.util.UUID;

/**
 * The query messages.
 */
public class QueryAPI {
    private QueryAPI() { /* Non instantiable class */ }

    public record GetAccountView(UUID id) {}

}
