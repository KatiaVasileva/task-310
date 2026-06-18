package com.vasileva.task310.model.writer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WriterOut {
    Long id;
    String login;
    @JsonProperty("firstname")
    String firstname;
    @JsonProperty("lastname")
    String lastname;
}
