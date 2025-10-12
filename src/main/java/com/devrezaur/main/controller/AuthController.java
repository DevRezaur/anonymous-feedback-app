package com.devrezaur.main.controller;

import jakarta.annotation.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

  @GetMapping("/login")
  public String loginPage(@RequestParam @Nullable String logout, @RequestParam @Nullable String error, Model model) {
    if (logout != null && logout.equals("true")) {
      model.addAttribute("message", "Logout successful");
    } else if (error != null && error.equals("true")) {
      model.addAttribute("error", "Invalid credentials!");
    }

    return "login-page";
  }

}
