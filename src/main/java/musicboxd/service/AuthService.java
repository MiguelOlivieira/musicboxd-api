
package musicboxd.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import musicboxd.dto.request.LoginRequestDTO;
import musicboxd.dto.request.UserRegisterRequestDTO;
import musicboxd.dto.response.AuthResponseDTO;
import musicboxd.model.User;
import musicboxd.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    @Transactional
    public void register(UserRegisterRequestDTO request) {
        User user = User.builder()
                .nome(request.nome())
                .biografia(request.biografia())
                .email(request.email())
                .senha(request.senha())
                .build();

        userRepository.save(user);
    }

    public AuthResponseDTO login(LoginRequestDTO request) {
        return new AuthResponseDTO("token-de-exemplo");
    }

    public void sendRecoveryEmail(String email) {}

    public void validateEmail(String email) {}
}