package org.techforumist.addressbook.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configurable
@EnableWebSecurity
// Modificando ou substituindo a segurança padrão da inicialização do SpringBoot.
public class WebConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	AppUserDetailsService appUserDetailsService;

	// Este método é para substituir o AuthenticationManagerBuilder padrão.
	// Podemos especificar como os detalhes do usuário são mantidos no aplicativo.
	// Pode estar em um banco de dados, LDAP ou na memória.
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(appUserDetailsService);
	}

	// Este método é para substituir algumas configurações do WebSecurity
	// Se você deseja ignorar algumas solicitações ou padrões de solicitações.
	@Override
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);
	}

	// Este método é usado para substituir HttpSecurity do aplicativo da web.
	// Podemos especificar nossos critérios de autorização dentro deste método.
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				// starts authorizing configurations
				.authorizeRequests()
				// ignoring the "/", "/index.html", "/app/**", "/register",
				// "/favicon.ico"
				.antMatchers("/", "/index.html", "/app/**", "/register", "/favicon.ico").permitAll()
				// authenticate all remaining URLS
				.anyRequest().fullyAuthenticated().and()
				// enabling the basic authentication
				.httpBasic().and()
				// configuring the session as state less. Which means there is
				// no session in the server
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				// disabling the CSRF - Cross Site Request Forgery
				.csrf().disable();
	}
}