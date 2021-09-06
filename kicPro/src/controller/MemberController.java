package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import dao.MemberDao;
import model.Member;

public class MemberController extends Action {
	// handler의 service method 임
	// url:/kicPro/member/hello
	public String hello(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("============hello");
		request.setAttribute("hello", "hello 테스트 입니다");
		return "/view/hello.jsp";
	}

	// url:/kicPro/member/list
	public String list(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		request.setAttribute("list", "list 테스트 입니다");
		return "/view/list.jsp";
	}

	// url:/kicPro/member/memberInput
	public String memberInput(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		request.setAttribute("input", "member input 테스트 입니다");
		return "/view/member/memberInput.jsp";
	}

	public String main(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		return "/view/main.jsp";
	}

	public String memberInputPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		MemberDao dao = new MemberDao();
		Member m = new Member();
		request.setCharacterEncoding("UTF-8");
		m.setId(request.getParameter("id"));
		m.setPass(request.getParameter("pass"));
		m.setName(request.getParameter("name"));
		m.setGender(Integer.parseInt(request.getParameter("gender")));
		m.setTel(request.getParameter("tel"));
		m.setEmail(request.getParameter("email"));
		m.setPicture(request.getParameter("picture"));
		int num = dao.memberInsert(m);
		System.out.println(num + "개 저장됨");
		request.setAttribute("member", m);
		if (num != 1) {
			request.setAttribute("url", "/member/memberInput");
			request.setAttribute("msg", "회원가입이 않됬습니다");

		} else {

			request.setAttribute("url", "/member/loginForm");
			request.setAttribute("msg", "가입하여 주셔서 감사합니다");

		}
		return "/view/alert.jsp";

	}

	public String loginForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

		return "/view/member/loginForm.jsp";
	}

	public String loginPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		MemberDao dao = new MemberDao();
		Member member = dao.selectOne(id);
		String msg = "아이디를 확인 하세요";
		String url = "/member/loginForm";
		if (member != null) {
			if (pass.equals(member.getPass())) {
				request.getSession().setAttribute("login", id);
				msg = member.getName() + "님이 로그인 하셨습니다";
				url = "/member/main";

			} else {
				msg = "비밀번호를 확인 하세요";
			}

		}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return "/view/alert.jsp";

	}

	public String logOut(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		request.getSession().invalidate();
		return "/view/main.jsp";
	}

	// 1) browser : http://localhost:9080/kicPro/member/memberList
	public String memberList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 2)
		MemberDao dao = new MemberDao();
		String id = (String) request.getSession().getAttribute("login");
		String msg = "회원정보를 확인 할 수 없습니다";
		String url = "member/loginForm";
		if (id != null && id.equals("admin")) {
			List<Member> mlist = dao.memberList();
			// 4) jsp 보여주는 자료를 보내는것
			request.setAttribute("mlist", mlist);
			// 5) view (jsp) : el , jstl
			return "/view/member/memberList.jsp";
		}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return "/view/alert.jsp";

	}

	// 1) browser : http://localhost:9080/kicPro/member/memberInfo
	public String memberInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		// 2)
		String userid = request.getParameter("userid");
		String id = (String) request.getSession().getAttribute("login");
		MemberDao dao = new MemberDao();
		String msg = "회원정보를 확인 할 수 없습니다";
		String url = "member/loginForm";
		
		if (id != null) {
			if (id.equals("admin")) {
				Member member = dao.selectOne(userid);
				request.setAttribute("member", member);
				return "/view/member/memberInfo.jsp";
			} else {
				Member member = dao.selectOne(id);
				request.setAttribute("member", member);
				return "/view/member/memberInfo.jsp";
			}
		}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return "/view/alert.jsp";
	}

	// http://localhost:9080/kicPro/member/memberUpdate
	public String memberUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		// 2)
		String userid = request.getParameter("userid");
		String id = (String) request.getSession().getAttribute("login");
		MemberDao dao = new MemberDao();
		String msg = "회원정보를 확인 할 수 없습니다";
		String url = "member/loginForm";
		if (id != null) { // login 되어있음
			if (id.equals("admin")) { // login id가 admin
				Member member = dao.selectOne(userid);
				request.setAttribute("member", member);
				return "/view/member/memberUpdate.jsp";
			} else { // admin 이 아닌 유저
				Member member = dao.selectOne(id);
				request.setAttribute("member", member);
				return "/view/member/memberUpdate.jsp";
			}
		}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return "/view/alert.jsp";
	}

	// http://localhost:9080/kicPro/member/memberUpdatePro

	public String memberUpdatePro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		String userid = request.getParameter("userid");
		String id = (String) request.getSession().getAttribute("login");
		Member mem = new Member();
		MemberDao dao = new MemberDao();
		mem.setName(request.getParameter("name"));
		mem.setPass(request.getParameter("pass"));
		mem.setGender(Integer.parseInt(request.getParameter("gender")));
		mem.setTel(request.getParameter("tel"));
		mem.setEmail(request.getParameter("email"));
		mem.setPicture(request.getParameter("picture"));
		String msg = "수정 할 수 없습니다";
		String url = "member/main";
		int num = 0;
		if (id != null) {
			if (id.equals("admin")) {
				mem.setId(userid);
				url = "member/memberList";
			} else {
				mem.setId(id);
				url = "member/memberInfo";
			}
			num = dao.memberUpdate(mem);
		}
		
		System.out.println(num);
		if (num==1) {
			msg="수정되었습니다 ";
			
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return "/view/alert.jsp";
	}
	
	public String memberDelete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		// 2)
		String userid = request.getParameter("userid");
		String id = (String) request.getSession().getAttribute("login");
		MemberDao dao = new MemberDao();
		String msg = "회원정보를 확인 할 수 없습니다";
		String url = "member/loginForm";
		if (id != null) { // login 되어있음
			if (id.equals("admin")) { // login id가 admin
				Member member = dao.selectOne(userid);
				request.setAttribute("member", member);
				return "/view/member/deleteForm.jsp";
			} else { // admin 이 아닌 유저
				Member member = dao.selectOne(id);
				request.setAttribute("member", member);
				return "/view/member/deleteForm.jsp";
			}
		}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return "/view/alert.jsp";
	}
	
	
	
	
	public String memberDeletePro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		String userid = request.getParameter("userid");
		String pass = request.getParameter("pass");
		String id = (String) request.getSession().getAttribute("login");
	
		MemberDao dao = new MemberDao();
		
		String msg = "탈퇴 할 수 없습니다";
		String url = "member/main";
		int num = 0;
		if (id != null) {
			if (id.equals("admin")) {
				num = dao.memberDelete(userid, pass);
				url = "member/memberList";
			} else {
				num = dao.memberDelete(id, pass);
				request.getSession().invalidate();
				url = "member/main";			}	}
		
		System.out.println(num);
		if (num==1) {			msg="탈퇴 하였습니다";			
			
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return "/view/alert.jsp";
	}
	
	
	public String photoForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		return "/singleview/photoForm.jsp";
	}
	
	
	public String photoPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		   //picture : <input type="file" name="picture">
		   String path = request.getServletContext().getRealPath("/") + "/photo";
		   System.out.println(path);
		   String filename = null;
		   try {
			   MultipartRequest multi = new MultipartRequest
					   (request,path,10*1024*1024,"euc-kr");
			   filename = multi.getFilesystemName("picture");
		   } catch(IOException e) {
			   e.printStackTrace();
		   }
		   
		   request.setAttribute("filename", filename);   //photoPro
		//workspace..\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\kicPro\/photo   
		return "/singleview/photoPro.jsp";
	}
	
	
	
	
	
	
} // end class
