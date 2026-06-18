package com.vasileva.task310.model.Issue;

import com.vasileva.task310.model.note.Note;
import com.vasileva.task310.model.tag.Tag;
import com.vasileva.task310.model.writer.Writer;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String title;
    String content;

    @CreationTimestamp
    @Builder.Default
    LocalDateTime created = LocalDateTime.now();

    LocalDateTime modified;

    @ManyToOne
    Writer writer;

    @OneToMany(mappedBy = "issue")
    List<Note> notes;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "issue_tag",
            joinColumns = @JoinColumn(name = "issue_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    @Builder.Default
    Set<Tag> tags = new HashSet<>();
}
