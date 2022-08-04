package com.epapps.moments.dtos.moment;


import com.epapps.moments.models2.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class MomentJsonRequest {
    public String title;
    public String imgUrl;
    public String description;
    public String ubication;
    public String username;

    public MomentJsonRequest() {

    }

}
