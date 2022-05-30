
/*
package com.example.wineshop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private final PasswordEncoder pwEncoder =
			PasswordEncoderFactories.createDelegatingPasswordEncoder();
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				//.antMatchers("/swagger-ui/index.html#").permitAll()
				.antMatchers(HttpMethod.GET, "/wine").permitAll()
				.antMatchers("/type").hasRole("ADMIN")
				.antMatchers(HttpMethod.DELETE, "/wine/{id}").hasRole("ADMIN")
				//.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
				.and()
			.logout()
				.permitAll();
	}

	@Bean
	@Override
	public UserDetailsService userDetailsService() {
		UserDetails user = User.builder()
				.username("user")
				.password(pwEncoder.encode("password"))
				.roles("USER")
				.build();
		UserDetails admin = User.builder()
				.username("admin")
				.password(pwEncoder.encode("jpassword"))
				.roles("USER", "ADMIN")
				.build();

		return new InMemoryUserDetailsManager(user, admin);
	}
}


*/