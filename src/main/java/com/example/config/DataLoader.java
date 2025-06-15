package com.example.config;

import com.example.model.*;
import com.example.repo.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class DataLoader {

  @Bean
  CommandLineRunner init(UserRepository ur, RoleRepository rr, ProductRepository pr) {
    return args -> {
      BCryptPasswordEncoder enc = new BCryptPasswordEncoder();

      Role rRead  = rr.save(new Role("PRODUCT_READ"));
      Role rWrite = rr.save(new Role("PRODUCT_WRITE"));

      AppUser u1 = new AppUser();
      u1.setUsername("reader");
      u1.setPassword(enc.encode("readerpass"));
      u1.getRoles().add(rRead);
      ur.save(u1);

      AppUser u2 = new AppUser();
      u2.setUsername("writer");
      u2.setPassword(enc.encode("writerpass"));
      u2.getRoles().add(rRead);
      u2.getRoles().add(rWrite);
      ur.save(u2);

      pr.save(new Product("Kalem", "Mavi uçlu kurşun kalem"));
      pr.save(new Product("Defter", "Çizgili 100 yaprak defter"));
    };
  }
}
