package com.example.skillswap.service;

import com.example.skillswap.model.Skill;
import com.example.skillswap.model.User;
import com.example.skillswap.repository.SkillRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillService {

    private final SkillRepository skillRepository;

    public SkillService(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    public void addSkill(Skill skill) {
        skillRepository.save(skill);
    }

    public List<Skill> getUserSkills(User user) {
        return skillRepository.findByUser(user);
    }

    public List<Skill> getOtherUsersSkills(User user) {
        return skillRepository.findByUserNot(user);
    }
}
