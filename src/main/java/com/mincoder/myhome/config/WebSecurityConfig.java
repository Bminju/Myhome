package com.mincoder.myhome.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;


@Configuration
    @EnableWebSecurity
    public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

        @Autowired  //springboot가 알아서 인스턴스 주입
        private DataSource dataSource;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests()
                        .antMatchers("/", "/css/**").permitAll()
                        .anyRequest().authenticated()
                        .and()
                    .formLogin()
                        .loginPage("/account/login")
                        .permitAll()
                        .and()
                    .logout()
                        .permitAll();
        }

        @Autowired   //jdbc를 이용해서 사용자쿼리와 권한쿼리를 가져옴
        public void configureGlobal(AuthenticationManagerBuilder auth)
                throws Exception {
            auth.jdbcAuthentication()
                    .dataSource(dataSource)  //properties에 설정되어 있는 dataSource를 사용할 수 있음
                    .passwordEncoder(passwordEncoder())  //passwordEncoder이용해서 비번 암호화 알아서 처리함
                    .usersByUsernameQuery("select username,password,enabled "  //인증처리 - table 컬럼 순서대로 select 해야함
                            + "from user "
                            + "where username = ?")
                    .authoritiesByUsernameQuery("select username,authority "  //권한처리
                            + "from user_role ur inner join user u on ur.user_id = u.id "
                            + "inner join role r on ur.role_id = r.id "
                            + "where username = ?");
        }

    @Bean  //위에 @Configuration 를 했기때문에 @Bean설정 가능함.
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  //비번 암호화를 스프링부트가 제공함
    }

    //참고*  Authentication 로그인 , Authroization 권한

}

