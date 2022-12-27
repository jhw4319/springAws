package com.jhw.springboot.web;

import com.jhw.springboot.config.auth.SecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class) // 스프링 부트 테스트와 JUnit 사이에 연결자 역활
@WebMvcTest(controllers = HelloController.class, excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)}) // Web에 집중할 수 있는 어노테이션
public class HelloControllerTest {
    @Autowired
    private MockMvc mvc; // 웹 API를 테스트.

    @Test
    @WithMockUser(roles = "USER")
    public void hello_return() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello")) // HTTP GET 요청
                .andExpect(status().isOk()) // Status 검증
                .andExpect(content().string(hello)); // 응답 본문의 내용을 검증
    }

    @Test
    @WithMockUser(roles = "USER")
    public void helloDto_return() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(get("/hello/dto").param("name", name).param("amount", String.valueOf(amount))) // 요청 파라미터 설정. String만 허용.
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name))) // $를 기준으로 필드명을 명시.
                .andExpect(jsonPath("$.amount", is(amount)));

    }

}