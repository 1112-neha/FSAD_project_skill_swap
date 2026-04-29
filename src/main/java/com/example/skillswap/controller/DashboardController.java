package com.example.skillswap.controller;

import com.example.skillswap.model.Skill;
import com.example.skillswap.model.User;
import com.example.skillswap.service.SkillService;
import com.example.skillswap.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class DashboardController {

    private final SkillService skillService;
    private final UserService userService;

    public DashboardController(SkillService skillService, UserService userService) {
        this.skillService = skillService;
        this.userService = userService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, Authentication authentication) {
        String email = authentication.getName();
        User user = userService.findByEmail(email);

        List<Skill> skills = skillService.getUserSkills(user);
        model.addAttribute("skills", skills);
        model.addAttribute("newSkill", new Skill());

        return "dashboard";
    }

    @PostMapping("/dashboard/add-skill")
    public String addSkill(@ModelAttribute("newSkill") Skill skill, Authentication authentication) {
        String email = authentication.getName();
        User user = userService.findByEmail(email);
        skill.setUser(user);
        skillService.addSkill(skill);

        return "redirect:/dashboard";
    }
}
