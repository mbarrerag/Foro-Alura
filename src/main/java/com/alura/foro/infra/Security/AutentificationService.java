package com.alura.foro.infra.Security;

import com.alura.foro.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * The AutentificationService class implements the UserDetailsService interface
 * to provide user details for authentication purposes.
 *
 * @author [Your Name]
 * @version 1.0
 * @since 2024-01-01
 */
@Service
public class AutentificationService implements UserDetailsService {

    /**
     * The UserRepository instance for retrieving user details.
     */
    public final UserRepository userRepository;

    /**
     * Constructs an AutentificationService with the provided UserRepository.
     *
     * @param userRepository The UserRepository instance.
     */
    public AutentificationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Retrieves user details by username.
     *
     * @param username The username for which user details are to be retrieved.
     * @return The UserDetails for the specified username.
     * @throws UsernameNotFoundException If the username is not found.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByNombre(username);
    }

}
