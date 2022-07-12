package com.epapps.moments.models;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

class MomentTest {

    @Test
    void commentCount(){
        Moment moment = new Moment();
        Comment comment = new Comment();

        moment.addComment((comment));

        int sut = moment.commentsCount();

        assertThat(sut, equalTo(1));
    }
}