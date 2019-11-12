package integrationTest;

import com.epam.config.ApplicationConfig;
import com.epam.config.SwaggerConfig;
import com.epam.config.WebApplicationConfig;
import com.epam.controller.TaskController;
import com.epam.controller.UserController;
import com.epam.model.Role;
import com.epam.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class, SwaggerConfig.class, WebApplicationConfig.class})
@WebAppConfiguration
public class UserControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

   @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void signUpTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        User testUser = User.builder().name("Test").surname("Testovskii").email("Test@mail.ru")
                .password("password").subscription("secret").role(Role.USER).build();
        String json = objectMapper.writeValueAsString(testUser);
        String jsonResponse = mockMvc.perform(post("/signUp")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();
        User responseUser = objectMapper.readValue(jsonResponse, User.class);
        Assert.assertEquals(testUser.getId(), responseUser.getId());
        Assert.assertEquals(testUser.getName(), responseUser.getName());
        Assert.assertEquals(testUser.getSurname(), responseUser.getSurname());
        Assert.assertEquals(testUser.getEmail(), responseUser.getEmail());

    }
}
