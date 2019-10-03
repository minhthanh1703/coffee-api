package group3.xavalocoffee.security;

import group3.xavalocoffee.entities.Account;
import group3.xavalocoffee.repository.AccountRepository;
import group3.xavalocoffee.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {

    private final AccountRepository accountRepository;
    private final AuthorityRepository authorityRepository;

    public ApplicationUserDetailsService(AccountRepository accountRepository, AuthorityRepository authorityRepository) {
        this.accountRepository = accountRepository;
        this.authorityRepository = authorityRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);

        if (account == null) {
            throw new UsernameNotFoundException(username);
        }
        if(account.isDisable() == true){
            return null;
        }
        Collection<GrantedAuthority> authorities = Collections.singletonList(
                new SimpleGrantedAuthority(authorityRepository.findById(account.getRole()).getAuthorityName()));

        return new org.springframework.security.core.userdetails.User(account.getUsername(), account.getPassword(),
                authorities);
    }
}
