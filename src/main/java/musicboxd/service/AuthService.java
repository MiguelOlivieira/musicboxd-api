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
@RequiredArgsConstructor // Necessário para a injeção de dependência do repository funcionar
public class AuthService {

    // Injetando o repositório
    private final UserRepository userRepository;

    @Transactional
    public void register(UserRegisterRequestDTO request) {
        // Verifica se o e-mail já existe (opcional, mas recomendado)
        // if(userRepository.findByEmail(request.email()).isPresent()) {
        //     throw new RuntimeException("E-mail já cadastrado");
        // }

        // Cria uma nova instância da entidade User
        User user = new User();

        // Mapeia os dados do DTO para a entidade.
        // Nota: Se o seu DTO for um Record, use request.nome(). Se for Classe, use request.getNome().
        user.setNome(request.nome()); // Ajuste o nome do método de acordo com o seu DTO
        user.setEmail(request.email());
        user.setSenha(request.senha()); // Futuramente, adicione um PasswordEncoder (ex: BCrypt) aqui

        // Persiste o usuário no banco de dados
        userRepository.save(user);
    }

    public AuthResponseDTO login(LoginRequestDTO request) {
        return new AuthResponseDTO("token-de-exemplo");
    }

    public void sendRecoveryEmail(String email) {

    }

    public void validateEmail(String email) {

    }
}