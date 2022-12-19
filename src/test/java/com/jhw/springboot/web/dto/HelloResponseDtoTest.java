package com.jhw.springboot.web.dto;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {

    @Test
    public void lombok_function_test() {
        // given
        String name = "lombok";
        int amount = 1219;
        // when
        HelloResponseDto helloResponseDto = new HelloResponseDto(name, amount);
        // then
        assertThat(helloResponseDto.getName()).isEqualTo(name); // 동등 비교
        assertThat(helloResponseDto.getAmount()).isEqualTo(amount);
    }
}