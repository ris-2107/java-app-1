//package com.deliverreez.javaservice.services.userservice.test;
//
//import com.deliverreez.javaservice.services.userservice.src.model.User;
//import com.deliverreez.javaservice.services.userservice.src.repository.UserRepository;
//import com.deliverreez.javaservice.services.userservice.src.service.UserService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.RepeatedTest;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import javax.validation.ValidationException;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//public class UserServiceTest {
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
//    @RepeatedTest(10)
//    public void testRegisterUserThreadSafety() {
//        // Setup mock behavior for userRepository
//        User mockUser = new User();
//        mockUser.setEmail("testuser@example.com");
//        mockUser.setUsername("testuser");
//        mockUser.setPassword("password");
//
//        when(userRepository.findByEmail(mockUser.getEmail())).thenReturn(Optional.empty());
//        when(userRepository.save(mockUser)).thenReturn(mockUser);
//
//        // Call the service method under test
//        User registeredUser = userService.registerUser(mockUser);
//
//        // Verify userRepository interactions
//        verify(userRepository, times(1)).findByEmail(mockUser.getEmail());
//        verify(userRepository, times(1)).save(mockUser);
//
//        // Assert the result
//        assertEquals(mockUser.getEmail(), registeredUser.getEmail());
//    }
//
//    @RepeatedTest(10)
//    public void testRegisterUserThreadSafetyExistingUser() {
//        // Setup mock behavior for userRepository
//        User existingUser = new User();
//        existingUser.setEmail("testuser@example.com");
//        existingUser.setUsername("testuser");
//        existingUser.setPassword("password");
//
//        when(userRepository.findByEmail(existingUser.getEmail())).thenReturn(Optional.of(existingUser));
//
//        // Call the service method under test
//        try {
//            userService.registerUser(existingUser);
//        } catch (ValidationException e) {
//            assertEquals("Username already exists", e.getMessage());
//        }
//
//        // Verify userRepository interactions
//        verify(userRepository, times(1)).findByEmail(existingUser.getEmail());
//        verify(userRepository, never()).save(existingUser);
//    }
//}
