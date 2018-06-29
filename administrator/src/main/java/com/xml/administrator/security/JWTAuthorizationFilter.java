package com.xml.administrator.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.xml.administrator.model.Admin;
import com.xml.administrator.services.AdminService;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	private JWTTokenUtil jwtTokenUtil;
	
	private AdminService adminSer;
	
    public JWTAuthorizationFilter(AuthenticationManager authManager, JWTTokenUtil jwtTokenUtil, AdminService adminSer) {
        super(authManager);
        this.jwtTokenUtil = jwtTokenUtil;
        this.adminSer = adminSer;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader(SecurityConstants.HEADER_STRING);
        if (header == null || !header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            chain.doFilter(req, res);
            return;
        }
        
        String token = header.substring((SecurityConstants.TOKEN_PREFIX).length());
        String email = jwtTokenUtil.getEmailFromToken(token);
        Admin temp = adminSer.findUserByEmail(email);
        if (temp != null && jwtTokenUtil.validateToken(token, temp)) {
	        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
	        System.out.println("User: "+authentication.getName()+" Permissions: "+authentication.getAuthorities());
	        SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(req, res);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(SecurityConstants.HEADER_STRING).substring((SecurityConstants.TOKEN_PREFIX).length());
        if (token != null) {
        	
            String user = jwtTokenUtil.getEmailFromToken(token);
            if (user != null) {
            	
                return new UsernamePasswordAuthenticationToken(user, null, jwtTokenUtil.getAuthorities(token));
            }
            return null;
        }
        return null;
    }
}