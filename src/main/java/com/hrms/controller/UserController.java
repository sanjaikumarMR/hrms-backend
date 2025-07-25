package com.hrms.controller;

import com.hrms.model.User;
import com.hrms.repository.UserRepository;
import com.hrms.service.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "https://sanjaikumarmr.github.io")  // Allow frontend origin
@RestController
@RequestMapping("/api/users")

public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SequenceGeneratorService sequenceGenerator;

    @PostMapping("/login")
    
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> credentials) {
    String id = credentials.get("id");
    String password = credentials.get("password");

    User user = userRepository.findById(id).orElse(null);

    if (user != null) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (encoder.matches(password, user.getPassword())) {
            // Password matches, return user data (without password)
            user.setPassword(null);
            return ResponseEntity.ok(user);
        }
    }

    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invaliddf ID or password");
}
 

    // ✅ Create a new user with custom ID

@PostMapping
public ResponseEntity<User> createUser(@RequestBody User user) {
    user.setId(sequenceGenerator.generateSequence("user_sequence"));
    
    // Encrypt the password before saving
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    user.setPassword(encoder.encode(user.getPassword()));

    return new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED);
}
    // ✅ Get all users
@GetMapping("/employees")

public List<User> getAllEmployees() {
    return userRepository.findAll();
}


    // ✅ Get user by ID
    @GetMapping("/{id}")
    public User getUserById(@PathVariable String id) {
        return userRepository.findById(id).orElse(null);
    }

    // ✅ Update user
    @PutMapping("/{id}")
    public User updateUser(@PathVariable String id, @RequestBody User userDetails) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setName(userDetails.getName());
            user.setEmail(userDetails.getEmail());
            user.setPhone(userDetails.getPhone());
            user.setPosition(userDetails.getPosition());
            user.setDepartment(userDetails.getDepartment());
            user.setRole(userDetails.getRole());
            user.setSalary(userDetails.getSalary());
            user.setDateOfJoining(userDetails.getDateOfJoining());
            user.setStatus(userDetails.getStatus());
            user.setAddress(userDetails.getAddress());
            user.setDob(userDetails.getDob());
            return userRepository.save(user);
        }
        return null;
    }
    // ✅ PATCH: Partial Update (role, manager assignment, etc.)
@PatchMapping("/{id}")
public ResponseEntity<?> updateUserFields(@PathVariable String id, @RequestBody Map<String, Object> updates) {
    return userRepository.findById(id).map(user -> {
        if (updates.containsKey("role")) user.setRole((String) updates.get("role"));
        if (updates.containsKey("managerId")) user.setManagerId((String) updates.get("managerId"));
        return ResponseEntity.ok(userRepository.save(user));
    }).orElse(ResponseEntity.notFound().build());
}


    // ✅ Delete user
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id) {
        userRepository.deleteById(id);
    }

    // ✅ Get users by role (e.g., Normal, HR, RM)
    @GetMapping("/role/{role}")
    public List<User> getUsersByRole(@PathVariable String role) {
        return userRepository.findByRole(role);
    }
//   @GetMapping("/api/users")
// public List<User> getAllUsers() {
//     return userRepository.findAll();
// }

    
  

    
}
