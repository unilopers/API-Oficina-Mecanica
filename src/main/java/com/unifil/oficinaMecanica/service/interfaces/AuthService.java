package com.unifil.oficinaMecanica.service.interfaces;

import com.unifil.oficinaMecanica.dto.request.LoginRequestDTO;
import com.unifil.oficinaMecanica.dto.request.RegisterRequestDTO;
import com.unifil.oficinaMecanica.dto.response.LoginResponseDTO;
import com.unifil.oficinaMecanica.dto.response.UserProfileResponseDTO;

public interface AuthService {

    void registrarUsuario(RegisterRequestDTO dto) throws Exception;

    LoginResponseDTO fazerLogin(LoginRequestDTO dto) throws Exception;

    UserProfileResponseDTO obterPerfil(String username) throws Exception;
}

