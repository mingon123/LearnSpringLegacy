package kr.spring.interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.spring.member.vo.MemberVO;

public class AdminCheckInterceptor extends HandlerInterceptorAdapter{
	private static final Logger log = LoggerFactory.getLogger(AdminCheckInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception{
		log.debug("<<AdminCheckInterceptor 진입>>");
		
		HttpSession session = request.getSession();
		MemberVO user = (MemberVO)session.getAttribute("user");
		if(user==null) {
			// 로그인이 되지 않은 상태
			response.sendRedirect(request.getContextPath()+"/member/login.do");
			return false;
		}
		
		if(user.getAuth()!=9) {
			// 관리자 권한이 아닐 때
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/common/accessDenied.jsp");
			dispatcher.forward(request, response);
			return false;
		}
		
		return true;
	}
	
}
