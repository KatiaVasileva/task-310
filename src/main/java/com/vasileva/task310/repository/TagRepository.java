package com.vasileva.task310.repository;

import com.vasileva.task310.model.tag.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {

    Optional<Tag> findByName(String name);

    @Modifying
    @Query(value = "DELETE FROM tbl_tag t WHERE t.id IN (:tagIdsToCheck) AND t.id NOT IN (SELECT tag_id FROM tbl_issue_tag)", nativeQuery = true)
    void deleteOrphanTags(@Param("tagIdsToCheck") Collection<Long> tagIdsToCheck);
}
