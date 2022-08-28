package com.humanresource.security;

import com.humanresource.auth.UserService;
import com.humanresource.enums.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author umeshkhatiwada13@infodev
 * @project demo1
 * @created 07/07/2022 - 9:20 AM
 */
@Configuration
@EnableWebSecurity
// For Annotation based Authorization
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordConfig passwordConfig;
    private final UserService userService;

    @Autowired
    public ApplicationSecurityConfig(PasswordConfig passwordConfig, UserService userService) {
        this.passwordConfig = passwordConfig;
        this.userService = userService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //Any request must be authenticated (ie client must provide username and password)
        http.csrf().disable()
                .authorizeRequests()
                //Permit all request for request to below given urls
                .antMatchers("/", "index", "/css/*", "/js/*").permitAll()
                // Allow all apis starting with /api/ to be accessed by users with role student only
                .antMatchers("/api/**").hasAnyAuthority(UserRole.ADMIN.getRole())
                /* // Allow delete, post and put request to user with authority COURSE_WRITE in management/api/** apis
                 .antMatchers(HttpMethod.DELETE, "/management/api/**").hasAuthority(ApplicationUserPermission.COURSE_WRITE.getPermission())
                 .antMatchers(HttpMethod.POST, "/management/api/**").hasAuthority(ApplicationUserPermission.COURSE_WRITE.getPermission())
                 .antMatchers(HttpMethod.PUT, "/management/api/**").hasAuthority(ApplicationUserPermission.COURSE_WRITE.getPermission())
                 // Allow users with role to access management/api/** apis get methods with roles Admin and Admin_Trainee
                 .antMatchers(HttpMethod.GET, "/management/api/**")
                 .hasAnyRole(ApplicationUserRole.ADMIN.name(), ApplicationUserRole.ADMIN_TRAINEE.name())*/
                .anyRequest()
                .authenticated()
                //Authenticity of client is done using form based authentication
                .and().formLogin()
                .loginPage("/login").permitAll()
                .defaultSuccessUrl("/home", true)
                .and().logout().logoutUrl("/logout")
                .clearAuthentication(true)
                // In case Remember-me is implemented
//                .deleteCookies("JSESSIONID", "remember-me")
                .logoutSuccessUrl("/login");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordConfig.passwordEncoder());
    }
}
