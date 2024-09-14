package com.example.fmt2.Controllers;


import com.example.fmt2.Entities.Asset;
import com.example.fmt2.Entities.AssetDto;
import com.example.fmt2.Entities.User;
import com.example.fmt2.Entities.UserDto;
import com.example.fmt2.Repositories.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping({"","/"})
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping({"","/"})
    public String showLoginPage(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("userDto",userDto);
        return "login/index"; // Assuming login.html is the login page template
    }

    @GetMapping("/signup")
    public String showSignupPage(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("userDto",userDto);
        return "login/signup";
    }

    @PostMapping("/signup")
    public String newuser(
            @Valid @ModelAttribute UserDto userDto,
            BindingResult result,
            Model model
    ){
        if (result.hasErrors()) {
            return "login/signup";
        }

        User user = new User();
        user.setPassword(userDto.getPassword());
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());

        userRepository.save(user);

        return "/login/index";
    }



    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, HttpSession session, HttpServletRequest request, Model model) {

        UserDto userDto = new UserDto();
        model.addAttribute("userDto",userDto);

        session.setAttribute("email", email);
        ModelAndView modelAndView = new ModelAndView();
        // Check if the provided username and password match any records in the database
        User user = userRepository.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {

            request.getSession().setAttribute("loggedInUserId", user.getUid());

            return "/dashboard/index";
        } else {
            //place a warning to display invalid credentials
            return "/login/index";
        }
    }

    @GetMapping("/home")
    public String home() {
        // Perform logout actions if any
        return "/dashboard/index";
    }

    @GetMapping("/logout")
    public String logout() {
        // Perform logout actions if any
        return "/login/index"; // Redirect to the login page after logout
    }
}