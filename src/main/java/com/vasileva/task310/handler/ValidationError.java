package com.vasileva.task310.handler;

public record ValidationError(String object,
                              String field,
                              String message) {
}
