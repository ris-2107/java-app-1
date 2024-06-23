//package com.deliverreez.javaservice.userservice.integrationTests;
//
//import com.deliverreez.javaservice.services.userservice.src.api.restrictedapi.RestrictedApiController;
//import com.deliverreez.javaservice.services.userservice.src.model.User;
//import com.deliverreez.javaservice.services.userservice.src.service.UserService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import static org.mockito.Mockito.*;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//public class UserControllerIntegrationTests {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Mock
//    private UserService userService;
//
//    @InjectMocks
//    private RestrictedApiController userController;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void testRegisterUser() throws Exception {
//        // Mock user data
//        User user = new User();
//        user.setUsername("testuser");
//        user.setEmail("testuser@example.com");
//        user.setPassword("password");
//
//        // Mock service method
//        when(userService.registerUser(any(User.class))).thenReturn(user);
//
//        // Perform HTTP POST request
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/users")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{\"username\":\"testuser\",\"email\":\"testuser@example.com\",\"password\":\"password\"}")
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isCreated())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("testuser"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("testuser@example.com"));
//
//        // Verify that registerUser method was called once
//        verify(userService, times(1)).registerUser(any(User.class));
//    }
//}
