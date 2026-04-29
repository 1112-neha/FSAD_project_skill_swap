package com.example.skillswap.repository;

import com.example.skillswap.model.Skill;
import com.example.skillswap.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {
    List<Skill> findByUser(User user);
    List<Skill> findByUserNot(User user);
}
