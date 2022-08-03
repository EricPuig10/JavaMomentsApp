package com.epapps.moments.models;

import com.epapps.moments.models2.User;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class MomentTest {

    @Test
    void commentCount() {
        Moment moment = new Moment();
        Comment comment = new Comment();

        moment.addComment((comment));

        int sut = moment.commentsCount();

        assertThat(sut, equalTo(1));
    }

    @Test
    void shouldHaveLikesCounter() {
        var moment = new Moment();
        var user = new User();
        moment.setId(1L);
        user.setId(1L);
        var like = new Fav(user, moment);

        moment.toggleFav(like);

        int sut = moment.favsCount();

        assertThat(sut, equalTo(1));
    }

    @Test
    void momentShouldntLetAddLikeIfMomentDoesNotMatch() {
        var moment = new Moment();
        var moment2 = new Moment();
        var user = new User();
        moment.setId(1L);
        moment2.setId(2L);
        user.setId(1L);
        var like = new Fav(user, moment);
        moment2.toggleFav(like);
        var sut = moment2.favsCount();

        assertThat(sut, equalTo(0));
    }

    @Test
    void momentShouldKnowIfIsLikedByAUser() {
        var moment = new Moment();
        var latinlover = new User();
        var like = new Fav(latinlover, moment);
        moment.toggleFav(like);
        var sut = moment.isFaved(latinlover);

        assertThat(sut, equalTo(true));
    }

    @Test
    void loverShouldBeContainedInFavList() {
        var moment = new Moment();
        var lover = new User();
        var nolover = new User();
        var fav = new Fav(lover, moment);
        moment.toggleFav(fav);
        var sut = moment.isFaved(nolover);

        assertThat(sut, equalTo(false));
    }
}