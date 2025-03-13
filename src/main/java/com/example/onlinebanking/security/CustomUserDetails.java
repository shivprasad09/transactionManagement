package com.example.onlinebanking.security;

import com.example.onlinebanking.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * CustomUserDetails is a custom implementation of the {@link UserDetails} interface used for Spring Security authentication.
 * It wraps a {@link User} entity and provides the necessary details required by Spring Security for authentication and authorization.
 *
 * <p>This class is used to integrate the application's {@link User} entity with Spring Security's authentication framework.
 * It implements methods to retrieve user details such as username, password, and account status.</p>
 *
 * <p>Key functionalities include:
 * <ul>
 *     <li>Providing the user's password for authentication.</li>
 *     <li>Providing the user's username for identification.</li>
 *     <li>Implementing methods to check account status (e.g., non-expired, non-locked, enabled).</li>
 * </ul>
 * </p>
 *
 * <p>Note: The {@link #getAuthorities()} method currently returns an empty collection. If role-based or authority-based access control
 * is required, this method should be implemented to return the user's roles or authorities.</p>
 *
 * @see UserDetails
 * @see User
 */
public class CustomUserDetails implements UserDetails {
    private final User user;

    /**
     * Constructs a new {@link CustomUserDetails} instance with the specified {@link User} entity.
     *
     * @param user the {@link User} entity to wrap.
     */
    public CustomUserDetails(User user) {
        this.user = user;
    }

    /**
     * Returns the authorities granted to the user. Currently, this method returns an empty collection.
     * <p>
     * If role-based or authority-based access control is required, this method should be implemented
     * to return the user's roles or authorities.
     * </p>
     *
     * @return a collection of {@link GrantedAuthority} objects.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Return an empty collection by default. Add roles/authorities if needed.
        return Collections.emptyList();
    }

    /**
     * Returns the password used to authenticate the user.
     *
     * @return the user's password.
     */
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    /**
     * Returns the username used to authenticate the user.
     *
     * @return the user's username.
     */
    @Override
    public String getUsername() {
        return user.getUsername();
    }

    /**
     * Indicates whether the user's account has expired.
     *
     * @return {@code true} if the user's account is non-expired, {@code false} otherwise.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true; // Add logic for account expiration if needed
    }

    /**
     * Indicates whether the user's account is locked.
     *
     * @return {@code true} if the user's account is non-locked, {@code false} otherwise.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true; // Add logic for account locking if needed
    }

    /**
     * Indicates whether the user's credentials (password) have expired.
     *
     * @return {@code true} if the user's credentials are non-expired, {@code false} otherwise.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Add logic for credential expiration if needed
    }

    /**
     * Indicates whether the user is enabled.
     *
     * @return {@code true} if the user is enabled, {@code false} otherwise.
     */
    @Override
    public boolean isEnabled() {
        return true; // Add logic for enabling/disabling users if needed
    }
}