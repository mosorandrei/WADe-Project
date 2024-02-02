package com.botanical.gardens.serverside.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private String lastName;
    private String email;
}
