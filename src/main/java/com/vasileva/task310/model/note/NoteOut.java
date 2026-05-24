package com.vasileva.task310.model.note;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NoteOut {
    Long id;
    Long issueId;
    String content;
}
