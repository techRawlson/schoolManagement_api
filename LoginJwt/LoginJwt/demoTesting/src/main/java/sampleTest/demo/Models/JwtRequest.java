package sampleTest.demo.Models;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder


public class JwtRequest {
    private String email;
    private String password;
}
