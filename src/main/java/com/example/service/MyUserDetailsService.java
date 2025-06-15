package com.example.service;

import com.example.model.AppUser;
import com.example.repo.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class MyUserDetailsService implements UserDetailsService {
  private final UserRepository userRepo;
  public MyUserDetailsService(UserRepository userRepo) {
    this.userRepo = userRepo;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    AppUser user = userRepo.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException(username));
    return new User(
      user.getUsername(),
      user.getPassword(),
      user.getRoles().stream()
          .map(r -> new SimpleGrantedAuthority(r.getName()))
          .collect(Collectors.toList())
    );
  }
}
