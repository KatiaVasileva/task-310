package com.vasileva.task310.model.writer;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Writer {
    Long id;
    String login;
    String password;
    String firstName;
    String lastName;
}
