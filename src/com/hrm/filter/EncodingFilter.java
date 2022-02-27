package com.hrm.filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EncodingFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String url = request.getServletPath();
        // endsWith()：判断字符串是否以指定字符结尾
        if (!(url.endsWith(".js") || url.endsWith(".css") || url.endsWith(".json"))) {
            // 设置post方式提交的数据的编码
            request.setCharacterEncoding("UTF-8");
            // 设置响应内容的编码
            response.setCharacterEncoding("UTF-8");
            // 告诉浏览器使用指定的编码解析当前响应的内容
            response.setContentType("text/html;charset=UTF-8");
        }
        // 放行
        chain.doFilter(request,response);
    }
}
