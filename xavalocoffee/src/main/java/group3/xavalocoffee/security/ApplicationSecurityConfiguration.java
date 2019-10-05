package group3.xavalocoffee.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final ApplicationUserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public ApplicationSecurityConfiguration(ApplicationUserDetailsService userDetailsService,
                                            BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests()
                //.antMatchers(HttpMethod.POST, "**").permitAll()
                .antMatchers("**/swagger-resources/**",
                        "/swagger-ui.html",
                        "/v2/api-docs",
                        "/webjars/**",
                        "/api/account/login",
                        "/api/account/sign-in").permitAll()
                .antMatchers(HttpMethod.GET, "/api/**").hasAnyAuthority("Admin", "Staff")
                .antMatchers(HttpMethod.PUT, "/api/**").hasAnyAuthority("Admin", "Staff")
                .antMatchers(HttpMethod.DELETE, "/api/**").hasAnyAuthority("Admin", "Staff")
                .antMatchers(HttpMethod.POST, "/api/**").hasAnyAuthority("Admin", "Staff")
                .anyRequest().authenticated()
                .and()
                .addFilter(new JWTGenerator(authenticationManager()))
                .addFilter(new JWTVerifier(authenticationManager()))
                // disable session creation on Spring Security
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        http.formLogin().init(http);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/", "/csrf", "/v2/api-docs", "/swagger-resources/configuration/ui", "/configuration/ui", "/swagger-resources", "/swagger-resources/configuration/security", "/configuration/security", "/swagger-ui.html", "/webjars/**");
    }

}
