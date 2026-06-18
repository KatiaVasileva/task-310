package com.vasileva.task310.model.Issue;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class IssueOut {
    Long id;
    Long writerId;
    String title;
    String content;
    LocalDateTime created;
    LocalDateTime modified;

    Set<String> tags = new HashSet<>();
}
