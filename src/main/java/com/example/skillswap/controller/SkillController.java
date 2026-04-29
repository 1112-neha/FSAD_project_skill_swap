package com.example.skillswap.controller;

import com.example.skillswap.model.Skill;
import com.example.skillswap.model.User;
import com.example.skillswap.service.SkillService;
import com.example.skillswap.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class SkillController {

    private final SkillService skillService;
    private final UserService userService;

    public SkillController(SkillService skillService, UserService userService) {
        this.skillService = skillService;
        this.userService = userService;
    }

    @GetMapping("/browse")
    public String browseSkills(Model model, Authentication authentication) {
        String email = authentication.getName();
        User currentUser = userService.findByEmail(email);

        List<Skill> otherUsersSkills = skillService.getOtherUsersSkills(currentUser);
        model.addAttribute("skills", otherUsersSkills);

        return "browse";
    }
}
