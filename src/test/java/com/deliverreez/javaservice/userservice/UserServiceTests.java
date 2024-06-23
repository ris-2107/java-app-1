//package com.deliverreez.javaservice.userservice;
//import com.deliverreez.javaservice.services.userservice.src.model.User;
//import com.deliverreez.javaservice.services.userservice.src.repository.UserRepository;
//import com.deliverreez.javaservice.services.userservice.src.service.UserService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//public class UserServiceTests {
//
//    @Mock
//    private UserRepository userRepository;
//
//    @InjectMocks
//    private UserService userService;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void testRegisterUser() {
//        // Mock user data
//        User user = new User();
//        user.setUsername("testuser");
//        user.setEmail("testuser@example.com");
//        user.setPassword("password");
//
//        // Mock repository behavior
//        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.empty());
//        when(userRepository.save(user)).thenReturn(user);
//
//        // Call the service method
//        User registeredUser = userService.registerUser(user);
//
//        // Verify the result
//        assertEquals(user.getUsername(), registeredUser.getUsername());
//        assertEquals(user.getEmail(), registeredUser.getEmail());
//
//        // Verify that save method was called once
//        verify(userRepository, times(1)).findByEmail(user.getEmail());
//        verify(userRepository, times(1)).save(user);
//    }
//}