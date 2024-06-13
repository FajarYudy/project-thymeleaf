package com.project.controller;

import com.project.model.User;
import com.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * @author fajaryudi
 * @created 2024/06/13 - 10.27
 */
@Controller
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping
    public String index(){
        return "index";
    }

    @GetMapping("/users")
    public String getUser(Model model){
        try {
            List<User> users = userRepository.findAll();
            model.addAttribute("users", users);
        } catch (Exception e){
            model.addAttribute("message", e.getMessage());
        }
        return "user";
    }

    @GetMapping("/users/add")
    public String getUserAdd(Model model){
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("pageTitle", "Create New User");
        return "user-form";
    }

    @GetMapping("/users/{id}")
    public String getUserEdit(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes){
        try {
            User user = userRepository.findById(id).get();
            model.addAttribute("user", user);
            model.addAttribute("pageTitle", "Edit Data (ID: " + id + ")");
            return "user-form";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/users";
        }
    }

    @PostMapping("/users/save")
    public String setUserSave(User user, RedirectAttributes redirectAttributes){
        try {
            userRepository.save(user);
            redirectAttributes.addFlashAttribute("message", "Data has been saved successfully!");
        } catch (Exception e){
            redirectAttributes.addAttribute("message", e.getMessage());
        }
        return "redirect:/users";
    }

    @GetMapping("/users/delete/{id}")
    public String deleteTutorial(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            userRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("message", "Data with id=" + id + " has been deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }

        return "redirect:/users";
    }
}
