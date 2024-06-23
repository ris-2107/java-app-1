//package com.deliverreez.javaservice.userservice;
//
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
//import java.util.concurrent.*;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//public class UserServiceThreadSafetyTests {
//
//    @Mock
//    private UserRepository userRepository;
//
//    @InjectMocks
//    private UserService userService;
//
//    private ExecutorService executorService;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//        executorService = Executors.newFixedThreadPool(10); // Create a thread pool with 10 threads
//    }
//
//    @Test
//    public void testRegisterUserThreadSafety() throws InterruptedException, ExecutionException {
//        String email = "testuser@example.com";
//
//        // Simulate multiple threads trying to register a user with the same email
//        int numThreads = 10;
//        CountDownLatch latch = new CountDownLatch(numThreads);
//        ConcurrentHashMap<String, User> registeredUsers = new ConcurrentHashMap<>();
//
//        for (int i = 0; i < numThreads; i++) {
//            executorService.submit(() -> {
//                User user = new User();
//                user.setUsername("testuser");
//                user.setEmail(email);
//                user.setPassword("password");
//
//                // Mock repository behavior
//                when(userRepository.findByEmail(email)).thenReturn(Optional.empty());
//                when(userRepository.save(user)).thenAnswer(invocation -> {
//                    registeredUsers.put(email, user); // Store registered user in map
//                    return user;
//                });
//
//                // Call the service method
//                User registeredUser = userService.registerUser(user);
//
//                // Verify the result (optional)
//                assertEquals(user.getUsername(), registeredUser.getUsername());
//                assertEquals(user.getEmail(), registeredUser.getEmail());
//
//                // Verify that save method was called once
//                verify(userRepository, times(1)).findByEmail(email);
//                verify(userRepository, times(1)).save(user);
//
//                latch.countDown();
//            });
//        }
//
//        latch.await(); // Wait for all threads to finish
//
//        // Verify thread safety by checking if only one user was registered
//        assertEquals(1, registeredUsers.size());
//        assertEquals(email, registeredUsers.keySet().iterator().next());
//    }
//
//    @BeforeEach
//    public void tearDown() {
//        executorService.shutdown();
//    }
//}
//
