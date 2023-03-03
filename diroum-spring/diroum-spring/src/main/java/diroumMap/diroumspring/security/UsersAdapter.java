package diroumMap.diroumspring.security;

import diroumMap.diroumspring.web.domain.users.UserRole;
import diroumMap.diroumspring.web.domain.users.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

public class UsersAdapter extends User {

    private Users users;

    public UsersAdapter(Users users) {
        super(users.getLoginId(), users.getPassword(), authorities(Collections.singleton(users.getUserRole())));
        this.users = users;
    }

    public Users getUsers(){
        return users;
    }

    private static Collection<? extends GrantedAuthority> authorities(Set<UserRole> roles) {
        return roles.stream()
                .map(r -> new SimpleGrantedAuthority(r.name()))
                .collect(Collectors.toSet());
    }

}
