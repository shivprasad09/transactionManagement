package com.example.onlinebanking.security;

import com.example.onlinebanking.model.User;
import com.example.onlinebanking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * CustomUserDetailsService is a custom implementation of the {@link CustomUserDetailsService} interface used for Spring Security authentication.
 * It retrieves user details from the database and constructs a {@link CustomUserDetailsService} object for authentication purposes.
 *
 * <p>This class is annotated with {@link java.security.Provider.Service} to indicate that it is a Spring-managed service component.
 * It interacts with the {@link com.example.onlinebanking.repository.UserRepository} to fetch user details based on the provided username.</p>
 *
 * <p>Key functionalities include:
 * <ul>
 *     <li>Loading user details by username for authentication.</li>
 *     <li>Throwing a {@link UsernameNotFoundException} if the user is not found in the database.</li>
 *     <li>Wrapping the retrieved {@link User} entity in a {@link CustomUserDetails} object.</li>
 * </ul>
 * </p>
 *
 * <p>This service is used by Spring Security to authenticate users during the login process.</p>
 *
 * @see CustomUserDetailsService
 * @see com.example.onlinebanking.repository.UserRepository
 * @see CustomUserDetails
 * @see UsernameNotFoundException
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Loads user details by username for authentication.
     * <p>
     * This method retrieves a {@link User} entity from the database using the provided username.
     * If the user is not found, it throws a {@link UsernameNotFoundException}.
     * The retrieved user is then wrapped in a {@link CustomUserDetails} object and returned.
     * </p>
     *
     * @param username the username of the user to be loaded.
     * @return a {@link CustomUserDetails} object containing the user's details.
     * @throws UsernameNotFoundException if the user with the specified username is not found.
     */
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new CustomUserDetails(user);
    }
}