package com.rest;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.rest.services.MvcConfiguration;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes=MvcConfiguration.class)
@WebAppConfiguration
public abstract class SpringBaseTest {
    
    @Autowired
    private WebApplicationContext wac;
    
    protected MockMvc mockMvc;
    
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
                                                  
    }
    
    protected MockMvc getMockMvc() {
        return mockMvc;
    }
    
    protected void initializeMockMvc() {
    
        // Process mock annotations
        MockitoAnnotations.initMocks(this);
        
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }
    
    protected WebApplicationContext getWebApplicationContext() {
    
        if (wac == null) {
            throw new RuntimeException("WebApplicationContext is null");
        }
        return wac;
    }
}
