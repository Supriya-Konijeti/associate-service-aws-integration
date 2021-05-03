package com.example.projectassociates.repo;

import com.example.projectassociates.model.Associate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssociateRepo extends JpaRepository<Associate, Integer> {
    @Query(value="select * from associate_detail e where e.name like %:keyword% or e.email like %:keyword% or e.mobile_number like %:keyword%", nativeQuery = true)
    List<Associate> findByKeyword(@Param("keyword") String keyword);
    @Query
    public Associate findBySkills(String skill_title);
}