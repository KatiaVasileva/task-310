package com.vasileva.task310.model.writer;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WriterIn {
    @Positive
    Long id;

    @Size(min = 2, max = 64)
    String login;

//    @Size(min = 8, max = 128)
    String password;

    @Size(min = 2, max = 64)
    @JsonProperty("firstname")
    String firstName;

    @Size(min = 2, max = 64)
    @JsonProperty("lastname")
    String lastName;
}
