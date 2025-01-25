package scm.SmartContactManager.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import scm.SmartContactManager.Repository.user_repository;

@Service
public class SecurityCustomServiceDetail implements UserDetailsService {
@Autowired
private user_repository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       return  userRepository.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("user not found"));
    }
}
