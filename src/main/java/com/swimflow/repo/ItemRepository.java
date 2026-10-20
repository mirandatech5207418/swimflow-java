package com.swimflow.repo; import com.swimflow.model.SwimItem; import org.springframework.data.jpa.repository.JpaRepository; public interface ItemRepository extends JpaRepository<SwimItem,Long>{}
