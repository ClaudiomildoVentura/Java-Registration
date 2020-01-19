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
// Modificando ou substituindo a segurança padrão da inicialização do
// SpringBoot.
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
				// começa a autorizar configurações
				.authorizeRequests()
				// ignorando o "/", "/index.html", "/app/**", "/register", "/favicon.ico"
				.antMatchers("/", "/index.html", "/app/**", "/register", "/favicon.ico").permitAll()
				// autenticação de todos os URLS restantes
				.anyRequest().fullyAuthenticated().and()
				// habilitando a autenticação básica
				.httpBasic().and()
				// configurando a sessão como menos estado. O que significa que existe
				// nenhuma sessão no servidor
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				// desativando o CSRF - falsificação de solicitação entre sites
				.csrf().disable();
	}
}