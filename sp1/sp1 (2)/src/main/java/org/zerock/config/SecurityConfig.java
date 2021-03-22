package org.zerock.config;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.zerock.security.CustomLoginSuccessHandler;
import org.zerock.security.CustomNoOpPasswordEncoder;
import org.zerock.security.CustomUserDetailsService;

import javax.sql.DataSource;

@Configuration
@Log4j
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Setter(onMethod_ = {@Autowired})
    private DataSource dataSource;

    @Bean
    public UserDetailsService customUserService() {
        return new CustomUserDetailsService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserService()).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests().antMatchers("/user/all").permitAll()
                .antMatchers("/user/admin").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/user/member").access("hasRole('ROLE_MEMBER')");

        //로그인
        http.formLogin().loginPage("/customLogin").loginProcessingUrl("/login").successHandler(loginSuccessHandler());

        //로그아웃
        http.logout().logoutUrl("/customLogout").invalidateHttpSession(true).deleteCookies("remember-me", "JSESSION_ID");

        //자동로그인
        http.rememberMe().key("zerock").tokenRepository(persistentTokenRepository()).tokenValiditySeconds(604800);
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
////        auth.inMemoryAuthentication().withUser("admin").password("{noop}admin").roles("ADMIN");
////        auth.inMemoryAuthentication().withUser("member").password("{noop}member").roles("MEMBER");
//        log.info("configure JDBC ................");
//        String queryUser = "select userid, userpw, enabled from tbl_member where userid = ? ";
//        String queryDetails = "select userid, auth from tbl_member_auth where userid = ? ";
//
//        auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(passwordEncoder()).usersByUsernameQuery(queryUser)
//                .authoritiesByUsernameQuery(queryDetails);
//    }

    @Bean
    public AuthenticationSuccessHandler loginSuccessHandler() {
        return new CustomLoginSuccessHandler();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new CustomNoOpPasswordEncoder();
    }



    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
        repo.setDataSource(dataSource);
        return repo;
    }
}
