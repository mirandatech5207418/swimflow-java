package com.swimflow.repo;
import com.swimflow.model.*;import org.springframework.data.jpa.repository.JpaRepository;import java.util.*;
public interface EnrollmentRepository extends JpaRepository<Enrollment,Long>{Optional<Enrollment> findBySessionIdAndSwimmerEmail(Long sessionId,String swimmerEmail);long countBySessionIdAndStatus(Long sessionId,EnrollmentStatus status);}
