package ng.com.codetrik.cipmas;
/*
 @Author Hamzat Habibllahi
 */
import ng.com.codetrik.cipmas.user.accessibility.CipmasUserDetailsService;
import ng.com.codetrik.cipmas.user.accessibility.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.SecureRandom;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,"/api/v1/user").permitAll()
                .antMatchers(HttpMethod.PUT,"/api/v1/user").hasRole(Role.ADMIN.name())

                .antMatchers(HttpMethod.POST,"/api/v1/customers").hasAnyRole(Role.ADMIN.name(),Role.MANAGER.name(),Role.DEVELOPER.name())
                .antMatchers(HttpMethod.PUT,"/api/v1/customers").hasAnyRole(Role.ADMIN.name(),Role.MANAGER.name(),Role.DEVELOPER.name())
                .antMatchers(HttpMethod.DELETE,"/api/v1/customers/{id}").hasAnyRole(Role.ADMIN.name(),Role.MANAGER.name())
                .antMatchers(HttpMethod.GET,"/api/v1/customers").hasAnyRole(Role.ADMIN.name(),Role.MANAGER.name(),Role.DEVELOPER.name())
                .antMatchers(HttpMethod.GET,"/api/v1/customers/{id}").hasAnyRole(Role.ADMIN.name(),Role.MANAGER.name(),Role.DEVELOPER.name())

                .antMatchers(HttpMethod.POST,"/api/v1/suppliers").hasAnyRole(Role.ADMIN.name(),Role.MANAGER.name(),Role.DEVELOPER.name())
                .antMatchers(HttpMethod.PUT,"/api/v1/suppliers").hasAnyRole(Role.ADMIN.name(),Role.MANAGER.name(),Role.DEVELOPER.name())
                .antMatchers(HttpMethod.DELETE,"/api/v1/suppliers/{id}").hasAnyRole(Role.ADMIN.name(),Role.MANAGER.name())
                .antMatchers(HttpMethod.GET,"/api/v1/suppliers").hasAnyRole(Role.ADMIN.name(),Role.MANAGER.name(),Role.DEVELOPER.name())
                .antMatchers(HttpMethod.GET,"/api/v1/suppliers/{id}").hasAnyRole(Role.ADMIN.name(),Role.MANAGER.name(),Role.DEVELOPER.name())

                .antMatchers(HttpMethod.POST,"/api/v1/quotes").hasAnyRole(Role.ADMIN.name(),Role.MANAGER.name(),Role.DEVELOPER.name())
                .antMatchers(HttpMethod.PUT,"/api/v1/quotes").hasAnyRole(Role.ADMIN.name(),Role.MANAGER.name(),Role.DEVELOPER.name())
                .antMatchers(HttpMethod.DELETE,"/api/v1/quotes/{id}").hasAnyRole(Role.ADMIN.name(),Role.MANAGER.name())
                .antMatchers(HttpMethod.GET,"/api/v1/quotes").hasAnyRole(Role.ADMIN.name(),Role.MANAGER.name(),Role.DEVELOPER.name())
                .antMatchers(HttpMethod.GET,"/api/v1/quotes/{id}").hasAnyRole(Role.ADMIN.name(),Role.MANAGER.name(),Role.DEVELOPER.name())

                .antMatchers(HttpMethod.POST,"/api/v1/quotations").hasAnyRole(Role.ADMIN.name(),Role.MANAGER.name(),Role.DEVELOPER.name())
                .antMatchers(HttpMethod.PUT,"/api/v1/quotations").hasAnyRole(Role.ADMIN.name(),Role.MANAGER.name(),Role.DEVELOPER.name())
                .antMatchers(HttpMethod.DELETE,"/api/v1/quotations/{id}").hasAnyRole(Role.ADMIN.name(),Role.MANAGER.name())
                .antMatchers(HttpMethod.GET,"/api/v1/quotations").hasAnyRole(Role.ADMIN.name(),Role.MANAGER.name(),Role.DEVELOPER.name())
                .antMatchers(HttpMethod.GET,"/api/v1/quotations/{id}").hasAnyRole(Role.ADMIN.name(),Role.MANAGER.name(),Role.DEVELOPER.name())

                .antMatchers(HttpMethod.POST,"/api/v1/materials").hasAnyRole(Role.ADMIN.name(),Role.MANAGER.name(),Role.DEVELOPER.name())
                .antMatchers(HttpMethod.PUT,"/api/v1/materials").hasAnyRole(Role.ADMIN.name(),Role.MANAGER.name(),Role.DEVELOPER.name())
                .antMatchers(HttpMethod.DELETE,"/api/v1/materials/{id}").hasAnyRole(Role.ADMIN.name(),Role.MANAGER.name())
                .antMatchers(HttpMethod.GET,"/api/v1/materials").hasAnyRole(Role.ADMIN.name(),Role.MANAGER.name(),Role.DEVELOPER.name())
                .antMatchers(HttpMethod.GET,"/api/v1/materials/{id}").hasAnyRole(Role.ADMIN.name(),Role.MANAGER.name(),Role.DEVELOPER.name())

                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(getAuthProvider());
    }

    @Bean
    public UserDetailsService getUserDetailService(){
        return new CipmasUserDetailsService();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder(10,new SecureRandom("cyberlord_abiz1992".getBytes()));
    }

    @Bean
    public DaoAuthenticationProvider getAuthProvider(){
        DaoAuthenticationProvider daoAuthProvider = new DaoAuthenticationProvider();
        daoAuthProvider.setPasswordEncoder(getPasswordEncoder());
        daoAuthProvider.setUserDetailsService(getUserDetailService());
        return daoAuthProvider;
    }
}
