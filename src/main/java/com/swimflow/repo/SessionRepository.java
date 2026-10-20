package com.swimflow.repo;
import com.swimflow.model.SwimSession; import org.springframework.data.jpa.repository.*;import org.springframework.data.repository.query.Param;import jakarta.persistence.LockModeType;import java.util.*;
public interface SessionRepository extends JpaRepository<SwimSession,Long>{@Lock(LockModeType.PESSIMISTIC_WRITE) @Query("select s from SwimSession s where s.id = :id") Optional<SwimSession> lockById(@Param("id") Long id);}
