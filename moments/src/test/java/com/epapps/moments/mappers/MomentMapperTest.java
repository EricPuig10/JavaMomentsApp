package com.epapps.moments.mappers;

import com.epapps.moments.dtos.moment.MomentRequestDto;
import com.epapps.moments.models.Fav;
import com.epapps.moments.models.Moment;
import com.epapps.moments.models2.User;
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