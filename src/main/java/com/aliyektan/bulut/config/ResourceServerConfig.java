package com.aliyektan.bulut.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private ResourceServerTokenServices tokenServices;

    @Value("${spring.security.jwt.resource-ids}")
    private String resourceIds;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(resourceIds).tokenServices(tokenServices);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .requestMatchers()
                .and()
                .authorizeRequests()
                .antMatchers("/account/**").authenticated()
                .antMatchers("/branches/available").hasAuthority("USER")
                .antMatchers("/branches/**").hasAnyAuthority("ADMIN", "SUPERADMIN")
                .antMatchers("/invoices").hasAnyAuthority("ADMIN", "USER", "SUPERADMIN")
                .antMatchers("/invoices/bill").hasAuthority("USER")
                .antMatchers("/invoices/confirm/*").hasAuthority("USER")
                .antMatchers("/parking-events").hasAnyAuthority("USER", "ADMIN", "SUPERADMIN")
                .antMatchers("/parking-events/start").hasAuthority("USER")
                .antMatchers("/pricing").hasAnyAuthority("ADMIN", "SUPERADMIN")
                .antMatchers("/pricing-periods").hasAnyAuthority("ADMIN", "SUPERADMIN")
                .antMatchers("/roles").hasAnyAuthority("ADMIN", "SUPERADMIN")
                .antMatchers("/users").hasAnyAuthority("ADMIN", "SUPERADMIN")
                .antMatchers("/oauth/token").anonymous();

    }

}
