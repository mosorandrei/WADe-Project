package com.botanical.gardens.serverside.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewDTO {
    private int score;
    private String tourName;
    private String firstName;
    private String lastName;
}
