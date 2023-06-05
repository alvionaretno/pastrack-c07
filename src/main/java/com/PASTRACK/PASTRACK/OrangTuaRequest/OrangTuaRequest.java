package com.PASTRACK.PASTRACK.OrangTuaRequest;

import java.io.Serializable;

import lombok.Data;

@Data
public class OrangTuaRequest implements Serializable {
    String usernameAnak;

    public String getUsernameAnak() {
        return usernameAnak;
    }

    public void setUSernameAnak(String usernameAnak) {
        this.usernameAnak = usernameAnak;
    }
}
