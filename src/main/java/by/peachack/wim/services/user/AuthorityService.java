package by.peachack.wim.services.user;

import by.peachack.wim.model.user.Authority;
import by.peachack.wim.repositories.AuthorityRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorityService {
    private final AuthorityRepository authorityRepository;

    private final String DEFAULT_AUTHORITY;

    public AuthorityService(AuthorityRepository authorityRepository,
                            @Value("${app.default-user-role}") String defaultAuthority) {
        this.authorityRepository = authorityRepository;
        DEFAULT_AUTHORITY = defaultAuthority;
    }

    public Authority getDefaultAuthority() {
        Optional<Authority> authority = authorityRepository
                .findByAuthority(DEFAULT_AUTHORITY);
        return authority.orElseGet(() -> authorityRepository.save(new Authority(null, DEFAULT_AUTHORITY)));
    }
}
