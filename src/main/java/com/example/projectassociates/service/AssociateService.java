package com.example.projectassociates.service;

import com.example.projectassociates.model.Associate;
import com.example.projectassociates.model.Skill;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AssociateService {
    public List<Associate> getAllAssociates();
    public Associate createAssociate(Associate associate);
    public Associate findById(int theId);
    public Associate deleteAssociateById(int theId);
    @Query(value="select * from associate_detail e where e.name like %:keyword% or e.email like %:keyword% or e.skill like %:keyword% or e.mobile_number like %:keyword%", nativeQuery = true)
    List<Associate> findByKeyword(@Param("keyword") String keyword);
    @Query(value="select * from Skills e where e.skill_title like %:keyword%", nativeQuery = true)
    List<Skill> findBySkill_title(@Param("keyword") String keyword);
    public Associate findBySkills(String skill_title);
}