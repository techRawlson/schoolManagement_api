package sampleTest.demo.Models;

import lombok.*;



import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtResponse {
    private String jwtToken;
    private String username;
}
