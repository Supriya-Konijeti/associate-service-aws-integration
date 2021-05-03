package com.example.projectassociates.repo;

import com.example.projectassociates.model.Associate;
import com.example.projectassociates.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Integer> {
    @Query(value="select * from Skills e where e.skill_title like %:keyword%", nativeQuery = true)
    List<Skill> findBySkill_title(@Param("keyword") String keyword);
}