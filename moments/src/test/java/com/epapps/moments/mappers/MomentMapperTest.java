package com.epapps.moments.mappers;

import com.epapps.moments.dtos.MomentRequestDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MomentMapperTest {

    @Test
    void mapToMoment() {
        var momentRequest = MomentRequestDto.builder().imgUrl("asdas").title("eric").description("hi").ubication("tona").build();

        var sut = new MomentMapper().mapToMoment(momentRequest);

        assertEquals(sut.getTitle(), momentRequest.getTitle());

    }
}