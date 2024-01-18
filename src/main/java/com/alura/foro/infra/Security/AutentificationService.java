package com.alura.foro.infra.Security;

import com.alura.foro.repository.UserRepository;
import jdk.swing.interop.SwingInterOpUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutentificationService implements UserDetailsService {

    public final UserRepository userRepository;

    public AutentificationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String nombre) throws UsernameNotFoundException {
        System.out.println( userRepository.findByNombre(nombre));
        return userRepository.findByNombre(nombre);
    }

}
