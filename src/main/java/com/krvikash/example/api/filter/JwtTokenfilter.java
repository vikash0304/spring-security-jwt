package com.krvikash.example.api.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.krvikash.example.api.service.UserInfoService;
import com.krvikash.example.api.util.JwtUtility;

@Component
public class JwtTokenfilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtility utility;

	@Autowired
	private UserInfoService userInfoService;

	@SuppressWarnings("deprecation")
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authHeader = request.getHeader("Authorization");
		String token = null;
		String userName = null;

		if (!StringUtils.isEmpty(authHeader) && authHeader.startsWith("Bearer")) {
			token = authHeader.substring(7);
			userName = utility.extractUsername(token);
		}

		if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = userInfoService.loadUserByUsername(userName);
			if (utility.validateToken(token, userDetails)) {

				UsernamePasswordAuthenticationToken userPasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				userPasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(userPasswordAuthenticationToken);
			}
		}
		filterChain.doFilter(request, response);
	}

}
