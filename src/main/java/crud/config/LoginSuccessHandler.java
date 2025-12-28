package crud.config;

import crud.model.User;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        User user = (User) authentication.getPrincipal();
        int userId = user.getId();
        boolean hasUserRole = authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER"));
        boolean hasAdminRole = authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
        if (hasAdminRole) {
            response.sendRedirect("/admin/users");
        } else if (hasUserRole) {
            response.sendRedirect("/user/" + userId);
        } else {
            response.sendRedirect("/login");
        }

    }
}
