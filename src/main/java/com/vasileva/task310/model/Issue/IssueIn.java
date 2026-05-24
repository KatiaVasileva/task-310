package com.vasileva.task310.model.Issue;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class IssueIn {

    @Positive
    Long id;

    @Positive
    Long writerId;

    @Size(min = 2, max = 64)
    String title;

    @Size(min = 4, max = 2048)
    String content;

    LocalDateTime created;

    LocalDateTime modified;
}
