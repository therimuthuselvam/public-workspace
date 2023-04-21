package com.in28minutes.springboot.firstrestapi.user;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsCommandLineRunner implements CommandLineRunner {

  private Logger logger = LoggerFactory.getLogger(getClass());

  private UserDetailsRepository repository;

  public UserDetailsCommandLineRunner(UserDetailsRepository repository) {
    this.repository = repository;
  }

  @Override
  public void run(String... args) throws Exception {
    logger.info(Arrays.toString(args));
    repository.save(new UserDetails("Theri", "Admin"));
    repository.save(new UserDetails("Ranga", "Admin"));
    repository.save(new UserDetails("Ravi", "User"));

    List<UserDetails> userDetails = repository.findAll();
    userDetails.forEach(user -> logger.info(user.toString()));

    List<UserDetails> usersWithAdminRoles = repository.findByRole("Admin");
    usersWithAdminRoles.forEach(user -> logger.info(user.toString()));
  }

}
