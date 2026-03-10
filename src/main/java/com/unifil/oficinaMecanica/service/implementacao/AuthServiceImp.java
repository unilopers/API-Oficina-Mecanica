package com.unifil.oficinaMecanica.service.implementacao;

import com.unifil.oficinaMecanica.config.JwtUtil;
import com.unifil.oficinaMecanica.dto.request.LoginRequestDTO;
import com.unifil.oficinaMecanica.dto.request.RegisterRequestDTO;
import com.unifil.oficinaMecanica.dto.response.LoginResponseDTO;
import com.unifil.oficinaMecanica.dto.response.UserProfileResponseDTO;
import com.unifil.oficinaMecanica.entity.UserEntity;
import com.unifil.oficinaMecanica.repository.UserRepository;
import com.unifil.oficinaMecanica.service.interfaces.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImp implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Value("${jwt.expiration}")
    private Long expiration;

    @Override
    public void registrarUsuario(RegisterRequestDTO dto) throws Exception {
        Optional<UserEntity> usuarioExistente = userRepository.findByEmail(dto.getEmail());
        if (usuarioExistente.isPresent()) {
            throw new Exception("Usuário com este email já existe");
        }

        UserEntity novoUsuario = new UserEntity();
        novoUsuario.setNome(dto.getNome());
        novoUsuario.setEmail(dto.getEmail());
        novoUsuario.setSenha(passwordEncoder.encode(dto.getSenha()));

        userRepository.save(novoUsuario);
    }

    @Override
    public LoginResponseDTO fazerLogin(LoginRequestDTO dto) throws Exception {
        Optional<UserEntity> usuario = userRepository.findByEmail(dto.getEmail());

        if (usuario.isEmpty()) {
            throw new Exception("Email ou senha inválidos");
        }

        UserEntity user = usuario.get();

        if (!passwordEncoder.matches(dto.getSenha(), user.getSenha())) {
            throw new Exception("Email ou senha inválidos");
        }
        String token = jwtUtil.generateToken(user.getNome(), user.getEmail());
        return new LoginResponseDTO(token, user.getNome(), user.getEmail(), expiration);
    }

    @Override
    public UserProfileResponseDTO obterPerfil(String username) throws Exception {

        Optional<UserEntity> usuario = userRepository.findByEmail(username);

        if (usuario.isEmpty()) {

            throw new Exception("Usuário não encontrado");
        }

        UserEntity user = usuario.get();
        return new UserProfileResponseDTO(user.getId(), user.getNome(), user.getEmail());
    }
}

