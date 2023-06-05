package com.PASTRACK.PASTRACK.Service.OrangTua;

import java.util.List;

import com.PASTRACK.PASTRACK.OrangTuaRequest.OrangTuaRequest;

public interface OrangTuaService {
    List<OrangTuaRequest> getUsernameSiswa(String usernameOrtu);
}
