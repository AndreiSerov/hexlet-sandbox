package org.example.controller.api.request;

import java.util.Collection;

public record PersonRequest(
    String firstName,
    String lastName,
    Collection<String> phone
) {
}
