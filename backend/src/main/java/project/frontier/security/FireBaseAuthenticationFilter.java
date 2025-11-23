package project.frontier.security;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import project.frontier.entity.User;
import project.frontier.entity.enums.UserRole;
import project.frontier.repository.UserRepository;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

public class FireBaseAuthenticationFilter extends OncePerRequestFilter {

    private final UserRepository userRepository;
    
    public FireBaseAuthenticationFilter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        if(header==null || !header.startsWith("Bearer")){
            filterChain.doFilter(request, response);
            return;
        }

        String token = header.substring(7);

        try{
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
            String firebaseUid = decodedToken.getUid();
            String email = decodedToken.getEmail();

            User user = userRepository.findByFirebaseUid(firebaseUid)
                    .orElse(null);

            if(user==null){
                String name = decodedToken.getName();
                if(name == null || name.trim().isEmpty()) {
                    name = email.split("@")[0]; 
                }
                
                User newUser = User.builder()
                        .firebaseUid(firebaseUid)
                        .email(email)
                        .name(name)
                        .role(UserRole.COLONIST)
                        .build();
                user = userRepository.save(newUser);
            }

            UsernamePasswordAuthenticationToken authentication = 
                    new UsernamePasswordAuthenticationToken(user, null, 
                            Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole().name())));


            SecurityContextHolder.getContext().setAuthentication(authentication);
        }catch (FirebaseAuthException e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Firebase token");
            return;
        }

        filterChain.doFilter(request, response);


        
    }
}
