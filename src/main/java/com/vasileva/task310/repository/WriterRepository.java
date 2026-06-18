package com.vasileva.task310.repository;

import com.vasileva.task310.model.writer.Writer;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WriterRepository extends JpaRepository<Writer, Long> {

    @Query("select w from Writer w where w.login = :login")
    Writer findByLogin(@Size(min = 2, max = 64) @Param("login") String login);
}
