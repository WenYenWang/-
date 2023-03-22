package main.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.security.web.csrf.CsrfFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
	@Bean
	public AccessDeniedHandler accessDeniedHandler() {
		return new EshopAccessDeniedHandler();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth

//		.inMemoryAuthentication()
//		.withUser("John").password(passwordEncoder().encode("admin")).roles("ADMIN", "EMPLOYEE")
//		.and()
//		.withUser("Eric").password(passwordEncoder().encode("employee")).roles("EMPLOYEE")
//		.and()
//		.withUser("Michael").password(passwordEncoder().encode("client")).roles("CLIENT");

			.jdbcAuthentication().dataSource(dataSource)
			.usersByUsernameQuery("select login, password, enabled from user where login=?")
			.authoritiesByUsernameQuery("select login, role from role where login=?");
		}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//Unicode UTF-8
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        http.addFilterBefore(filter,CsrfFilter.class);
        
		http.authorizeRequests()
			.antMatchers("/**", "/login", "/process-order-form")
				.permitAll()
			.antMatchers("/addtour","/add-customer","/safe-products")
				.hasAnyRole("ADMIN", "EMPLOYEE")			
			.antMatchers("/show-products","/show-cartdetail/{cartId}","/show-customer")
				.hasAnyRole("CLIENT")
			.and()
				.formLogin()
				.loginPage("/login")
				.loginProcessingUrl("/checkUserAccount")
//				.usernameParameter("username")
//				.passwordParameter("password")
				.defaultSuccessUrl("/")
				.permitAll()
			.and()
				.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/")
				.invalidateHttpSession(true)
				.permitAll()
			.and()
				//.exceptionHandling().accessDeniedPage("/forbidden");
				.exceptionHandling().accessDeniedHandler(accessDeniedHandler());
	}
}
