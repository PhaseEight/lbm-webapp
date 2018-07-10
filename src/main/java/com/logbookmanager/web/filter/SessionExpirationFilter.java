package com.logbookmanager.web.filter;

import com.logbookmanager.web.util.PrimeFacesUtil;
import org.springframework.stereotype.Service;

import javax.faces.bean.ApplicationScoped;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This filter handles session expiration during ajax request.
 * <p>
 * IMPORTANT: The spring security filter MUST be placed after this one.
 * <p>
 * Note: if you do not use Spring Security filter then you do not need this
 * filter since you can handle ViewExpiredException as any other exception (see
 * {@link ConversationAwareExceptionHandler}).
 */
@Service
@ApplicationScoped
public class SessionExpirationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (PrimeFacesUtil.isAjax(request) && !request.isRequestedSessionIdValid()) {
            response.getWriter().print(xmlPartialRedirectToPage(request, "/welcome?session_expired=1"));
            response.flushBuffer();
            return;
        }

        filterChain.doFilter(request, response);
    }

    private String xmlPartialRedirectToPage(HttpServletRequest request, String page) {
        return "<?xml version='1.0' encoding='UTF-8'?>" //
                + "<partial-response>" //
                + "<redirect url=\"" + request.getContextPath() + page + "\"/>" //
                + "</partial-response>";
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}