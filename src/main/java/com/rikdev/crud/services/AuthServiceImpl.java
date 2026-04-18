package com.rikdev.crud.services;

import com.rikdev.crud.dto.JwtResponse;
import com.rikdev.crud.dto.LoginRequest;
import com.rikdev.crud.dto.RegisterRequest;
import com.rikdev.crud.entities.Person;
import com.rikdev.crud.entities.Role;
import com.rikdev.crud.entities.User;
import com.rikdev.crud.entities.UserRole;
import com.rikdev.crud.entities.PasswordResetToken;
import com.rikdev.crud.repositories.PasswordResetTokenRepository;
import com.rikdev.crud.repositories.PersonRepository;
import com.rikdev.crud.repositories.RoleRepository;
import com.rikdev.crud.repositories.UserRepository;
import com.rikdev.crud.repositories.UserRoleRepository;
import com.rikdev.crud.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PersonRepository personRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordResetTokenRepository tokenRepository;
    private final EmailService emailService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserRepository userRepository,
            PersonRepository personRepository,
            RoleRepository roleRepository,
            UserRoleRepository userRoleRepository,
            PasswordResetTokenRepository tokenRepository,
            EmailService emailService,
            JwtUtil jwtUtil,
            PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.personRepository = personRepository;
        this.roleRepository = roleRepository;
        this.userRoleRepository = userRoleRepository;
        this.tokenRepository = tokenRepository;
        this.emailService = emailService;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    // 🔐 LOGIN
    @Override
    public JwtResponse login(LoginRequest request) {

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no existe"));

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword_hash())) {
            throw new RuntimeException("Credenciales incorrectas");
        }

        if (!Boolean.TRUE.equals(user.getIs_active())) {
            throw new RuntimeException("Usuario inactivo");
        }

        String token = jwtUtil.generateToken(user);
        return new JwtResponse(token);
    }

    // 📝 REGISTER
    @Override
    @Transactional
    public void register(RegisterRequest request) {

        String finalUsername = (request.getUsername() != null) ? request.getUsername() : request.getEmail();

        if (userRepository.existsByUsername(finalUsername)) {
            throw new RuntimeException("El usuario ya existe");
        }

        // 1️⃣ Crear y Guardar la Persona primero
        Person person = new Person();
        person.setFirst_name(request.getFirstName());
        person.setLast_name(request.getLastName());
        person.setIdentification(request.getIdentification());
        person.setEmail(request.getEmail());
        person.setF_creation(LocalDateTime.now().toString());
        person.setF_update(LocalDateTime.now().toString());
        // Puedes agregar más campos si los incluyes en el JSON

        person = personRepository.save(person);

        // 2️⃣ Crear usuario vinculado a la persona
        User user = new User();
        user.setUsername(finalUsername);
        user.setEmail_login(request.getEmail());
        user.setPassword_hash(
                passwordEncoder.encode(request.getPassword()));
        user.setIs_active(true);
        user.setFcreation(LocalDateTime.now());
        user.setId_person(person.getId_person()); // Usamos el ID generado de la persona

        user = userRepository.save(user);

        // 3️⃣ Obtener rol USER
        Role roleUser = roleRepository.findByName("COMMON")
                .orElseThrow(() -> new RuntimeException("Rol COMMON no existe"));

        // 4️⃣ Crear relación user_role
        UserRole userRole = new UserRole(user, roleUser);
        userRoleRepository.save(userRole);
    }

    // 📧 FORGOT PASSWORD
    @Override
    public void forgotPassword(String email) {
        User user = userRepository.findByEmailLogin(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ese email"));

        // Generar token único (UUID)
        String token = java.util.UUID.randomUUID().toString();

        // Eliminar token anterior si existe
        tokenRepository.findByUser(user).ifPresent(tokenRepository::delete);

        // Guardar nuevo token
        PasswordResetToken resetToken = new PasswordResetToken(token, user);
        tokenRepository.save(resetToken);

        // Enviar email
        emailService.sendResetPasswordEmail(user.getEmail_login(), token);
    }

    // 🔄 RESET PASSWORD
    @Override
    @Transactional
    public void resetPassword(String token, String newPassword) {
        PasswordResetToken resetToken = tokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Token de recuperación inválido"));

        if (resetToken.isExpired()) {
            tokenRepository.delete(resetToken);
            throw new RuntimeException("El token ha expirado");
        }

        User user = resetToken.getUser();
        user.setPassword_hash(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        // Eliminar token usado
        tokenRepository.delete(resetToken);
    }
}
