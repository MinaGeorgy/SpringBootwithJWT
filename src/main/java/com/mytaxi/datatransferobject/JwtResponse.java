package com.mytaxi.datatransferobject;

import java.io.Serializable;

/**
 * Created by mina.georgy
 */
public class JwtResponse implements Serializable {

    private static final long serialVersionUID = 1250166508152483573L;

    private final String token;

    public JwtResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }
}
