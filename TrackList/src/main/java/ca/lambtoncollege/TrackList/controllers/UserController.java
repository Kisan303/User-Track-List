package ca.lambtoncollege.TrackList.controllers;

import ca.lambtoncollege.TrackList.models.User;
import ca.lambtoncollege.TrackList.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // Display form to create a new user
    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("user", new User());
        return "form";
    }

    // Submit the form to create a new user
    @PostMapping("/form")
    public String submitForm(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        // Check if the email already exists
        if (userRepository.existsByEmail(user.getEmail())) {
            result.rejectValue("email", "email.exists", "This email is already in use.");
            return "form"; // Return to the form page with the error
        }

        // If no validation errors, save the user
        if (result.hasErrors()) {
            return "form";
        }

        userRepository.save(user);
        return "redirect:/list";
    }

    // Display list of users with optional filters
    @GetMapping("/list")
    public String listUsers(Model model,
                            @RequestParam(required = false) String name,
                            @RequestParam(required = false) String email,
                            @RequestParam(required = false) Integer age) {

        // Initialize users list
        Iterable<User> users;

        // Apply filters based on provided query parameters with checks for non-empty values
        if (name != null && !name.isEmpty() && email != null && !email.isEmpty() && age != null) {
            users = userRepository.findByNameIgnoreCaseAndEmailAndAge(name, email, age);  // Case-insensitive for name only
        } else if (name != null && !name.isEmpty() && email != null && !email.isEmpty()) {
            users = userRepository.findByNameIgnoreCaseAndEmail(name, email); // Case-insensitive for name only
        } else if (name != null && !name.isEmpty() && age != null) {
            users = userRepository.findByNameIgnoreCaseAndAge(name, age);  // Case-insensitive for name only
        } else if (email != null && !email.isEmpty() && age != null) {
            users = userRepository.findByEmailAndAge(email, age); // Case-sensitive for email and age
        } else if (name != null && !name.isEmpty()) {
            users = userRepository.findByNameIgnoreCase(name);  // Case-insensitive for name only
        } else if (email != null && !email.isEmpty()) {
            users = userRepository.findByEmail(email); // Case-sensitive for email
        } else if (age != null) {
            users = userRepository.findByAge(age); // Case-sensitive for age
        } else {
            users = userRepository.findAll(); // No filters applied
        }

        // Check if no users are found and show a message
        if (!users.iterator().hasNext()) {
            model.addAttribute("message", "No users found matching the given criteria.");
        }

        // Add the users to the model to display on the list page
        model.addAttribute("users", users);
        return "list";
    }
}
