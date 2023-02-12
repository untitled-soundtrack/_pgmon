package at.ac.fhstp.awp_bad.groupxx.pgmon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JwtDto {
    @JsonProperty("auth-token")
    private String authToken;

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
