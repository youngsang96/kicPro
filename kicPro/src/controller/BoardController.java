package controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import dao.BoardDao;

import model.Board;

public class BoardController extends Action {
	
	public String list(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		/*
		 * 게시물 목록 보기 
		 * 1. pageNum 파라미터 존재. pageNum 파라미터 없으면 1로 설정. 
		 * 2. 10건의 게시물 출력. => db에서
		 *    해당 페이지에 출력되는 게시물만 조회. 순서 : 최근 게시물 순으로 
		 * 3. 화면에 출력.
		 * 4. boardid 파라메터가 없으면 session을 수정하지 않음 default는 1임
		 */
		HttpSession session = request.getSession();
		// pageNum이 넘어와야 pageNum이 바뀜
		if (request.getParameter("pageNum") != null) {
			session.setAttribute("pageNum", request.getParameter("pageNum"));
		}
		// boardid가 넘어와야 세션이 변경됨 : 현재 게시판분류에 따른 입력,수정을 적용
		if (request.getParameter("boardid") != null) {
			session.setAttribute("boardid", request.getParameter("boardid"));
			session.setAttribute("pageNum", "1");
		}

		
		String pageNum = (String) session.getAttribute("pageNum");
		if (pageNum == null) 	pageNum = "1";
		int pageInt = Integer.parseInt(pageNum);
		
		String boardid = (String) session.getAttribute("boardid");
		if (boardid == null)  	boardid = "1";
		

		
		
		int limit = 10; // 한페이지에 출력할 게시물 건수
		BoardDao dao = new BoardDao();
		int boardcount = dao.boardCount(boardid);// 등록된 전체 게시물의 건수
		/*
		 * pageInt-현재 페이지 넘버, 
		 * limit-한페이지에 출력할 게시물 건수
		 * boardcount--등록된 전체 게시물의 건수
		 * boardid-공지사항(1),자유게시판(2), QnA(3)
		 */
		List<Board> list = dao.list(pageInt, limit, boardcount, boardid); // 화면에 출력된 게시물 데이터
		// 13 ---> boardcount/limit : 4 + 1
		//-----------paging 작업
		int maxpage = (int) (boardcount / limit) + (boardcount % limit == 0 ? 0 : 1);
		int bottomLine = 3;
		// page 1,2,3 : 1, 4,5,6: 2
		int startpage = 1 + (pageInt - 1) / bottomLine * bottomLine;
		int endpage = startpage + bottomLine - 1;
		if (endpage > maxpage) 	endpage = maxpage;
		int boardnum = boardcount - (pageInt - 1) * limit;  //100개 1:100, 2:90, 3:80
		//-----------paging 작업
		
		
		

		
		//print list
		request.setAttribute("boardcount", boardcount);  // 등록된 전체 게시물의 건수
		request.setAttribute("list", list);  //프린트한 게시물
		request.setAttribute("boardnum", boardnum); //게시물 시작번호
		request.setAttribute("pageNum", pageNum);  //현재 페이지 번호
		
		//하단 paging
		request.setAttribute("startpage", startpage); // 하단 시작 페이지
		request.setAttribute("endpage", endpage);  //하단 end 페이지
		request.setAttribute("bottomLine", bottomLine);  //하단 화면당 페이지 보기
		request.setAttribute("maxpage", maxpage);  //총 페이지수
	
		//게시판 제목
		request.setAttribute("boardName", getBoardName(boardid));  //게시판 제목 (공지시항, 자유게시판, QnA)
		
		return "/view/board/list.jsp";
	}
	
	
	public String getBoardName(String boardid) {
		
		String boardName = "";
		switch (boardid) {
		case "1": {   boardName="공지사항"; break;	}
		case "2": {   boardName="자유게시판"; break;	}
		case "3": {   boardName="QnA"; break;	}
		default:
			 boardName="공지사항";
		}
		
		return boardName;
	}
	
	
	
	
	
	

	public String writeForm(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		String boardid = (String) request.getSession().getAttribute("boardid");
		if (boardid == null)  	boardid = "1";
		request.setAttribute("boardName", getBoardName(boardid));
		return "/view/board/writeForm.jsp";
	}

	public String writePro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		/* 준비사항 :  WebContent/upfile folder작성하세요
		 * 1. 파라미터 값을 model.Board 객체 저장. 
		 * 2. sequence.nextval 처리 */
		String uploadpath = request.getServletContext().getRealPath("/") + "upfile";
		int size = 10 * 1024 * 1024;
		MultipartRequest multi;
		//boardid session 저장 내용 : default  1
		String boardid = (String) request.getSession().getAttribute("boardid");
		if (boardid == null) 	boardid = "1";  //1 공지사항
		try {	multi = new MultipartRequest(request, uploadpath, size, "utf-8");
			Board board = new Board();
			board.setName(multi.getParameter("name"));	board.setPass(multi.getParameter("pass"));
			board.setSubject(multi.getParameter("subject"));
			board.setContent(multi.getParameter("content"));
			board.setFile1(multi.getFilesystemName("file1"));		board.setBoardid(boardid);
			if (board.getFile1() == null) 	board.setFile1("");
			// 2. sequence nextval 입력
			BoardDao dao = new BoardDao();
			// 3. board 객체의 내용을 db에 insert 하기
			String msg = "게시물 등록 실패";		String url = "board/writeForm";
			if (dao.insert(board)) {		msg = "게시물 등록 성공";		url = "board/list";	}
			request.setAttribute("msg", msg);
			request.setAttribute("url", url);
		} catch (IOException e) {
			e.printStackTrace();		}
		return "/view/alert.jsp";
	}	

	public String info(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		/*
		 * : 게시물 상세 보기 :board/info?num=41 
		 * 
		 * 1. num 파라미터를 이용하여 db에 해당 게시물 조회 
		 *      Board board = new BoardDao().selectOne(num); 
		 * 2. 조회수 증가시키기. 
		 * readcnt+1 new BoardDao().readcntadd(num); 
		 * 
		 * 3. 1번에서 조회한 게시물데이터를 화면에 출력하기
		 */

		int num = Integer.parseInt(request.getParameter("num"));
		// 파라미터값읽기
		BoardDao dao = new BoardDao();
		Board board = dao.selectOne(num); // 게시물 조회
		dao.readcntadd(num); // 조회건수증가
		request.setAttribute("board", board);
		String boardid = (String) request.getSession().getAttribute("boardid");
		if (boardid == null)  	boardid = "1";
		request.setAttribute("boardName", getBoardName(boardid));
		
		return "/view/board/info.jsp";
	}

	
	public String updateForm(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		/*
		 * 1. num 값의 게시물을 조회화여 화면 출력하기
		 */
		int num = Integer.parseInt(request.getParameter("num"));
		Board board = new BoardDao().selectOne(num);
		request.setAttribute("board", board);
		return "/view/board/updateForm.jsp";
	}

	public String update(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		/*
		 * 1. 파라미터정보들을 Board 객체 저장. 
		 * 2. 비밀번호 검증 비밀번호
		 * 일치 : 수정으로. 
		 * 비밀번호 불일치 : 비밀번호 오류 메세지 출력하고, updateForm.jsp로 페이지 이동 3. 수정성공 : 수정성공
		 * 메시지 출력 후 list.jsp 페이지 이동 수정실패 : 수정실패 메시지 출력 후 updateForm.jsp 페이지 이동
		 */

		// 파라미터 정보 Board 객체에 저장
		Board board = new Board();
		String uploadpath = request.getServletContext().getRealPath("/") + "upfile/";
		MultipartRequest multi;
		try {
			multi = new MultipartRequest(request, uploadpath, 10 * 1024 * 1024, "euc-kr");

			board.setNum(Integer.parseInt(multi.getParameter("num")));
			board.setName(multi.getParameter("name"));
			board.setPass(multi.getParameter("pass"));
			board.setSubject(multi.getParameter("subject"));
			board.setContent(multi.getParameter("content"));
			board.setFile1(multi.getFilesystemName("file1"));
			// 수정시 첨부파일의 수정이 발생하지 않은 경우
			if (board.getFile1() == null || board.getFile1().equals("")) {
				board.setFile1(multi.getParameter("file2"));
			}
			// 비밀번호 검증
			BoardDao dao = new BoardDao();
			Board dbBoard = dao.selectOne(board.getNum());
			String msg = "비밀번호가 틀렸습니다.";
			String url = "board/updateForm?num=" + board.getNum();

			if (board.getPass().equals(dbBoard.getPass())) {
				// 수정하기
				if (dao.update(board)) {
					msg = "게시물 수정 완료";
					url = "board/list";
				} else {
					msg = "게시물 수정 실패";
				}
			}
			request.setAttribute("url", url);
			request.setAttribute("msg", msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "/view/alert.jsp";
	}

	public String deleteForm(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		int num = Integer.parseInt(request.getParameter("num"));
		request.setAttribute("num", num);
		return "/view/board/deleteForm.jsp";
	}

	public String delete(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		/*
		 * /WebContent/model1/board/delete.jsp 1. num,pass 파라미터를 변수에 저장. 2. 입력된 비밀번호와 db
		 * 비밀번호 검증 틀린경우 : 비밀번호 오류 메시지 출력, deleteForm.jsp 페이지 이동 3. 게시물 삭제. 삭제 성공 : 삭제 성공
		 * 메시지 출력, list.jsp 페이지 이동 삭제 실패 : 삭제 실패 메시지 출력, info.jsp 페이지 이동
		 */

		int num = Integer.parseInt(request.getParameter("num"));
		String pass = request.getParameter("pass"); // 입력된 비밀번호
		System.out.println(pass);
		String msg = "비밀번호가 틀렸습니다!";
		String url = "board/deleteForm?num=" + num;
		BoardDao dao = new BoardDao();
		Board board = dao.selectOne(num);
		// board.getPass() : db에 저장된 비밀번호
		if (pass.equals(board.getPass())) {
			if (dao.delete(num)) {
				msg = "게시글을 성공적으로 삭제하였습니다.";
				url = "board/list";
			} else {
				msg = "게시글을 삭제하는데 실패하였습니다!";
				url = "board/info?num=" + num;
			}
		}

		request.setAttribute("url", url);
		request.setAttribute("msg", msg);

		return "/view/alert.jsp";
	}

	public String replyForm(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		/*
		 * 답변글 쓰기 화면 
		 * 1. 원글의 num을 파라미터로 받는다. 
		 * 2. 원글의 num,ref,reflevel,refstep 정보를 저장 
		 * 3. 입력 화면 표시
		 */

		int num = Integer.parseInt(request.getParameter("num"));// 파라미터값읽기
		BoardDao dao = new BoardDao();
		Board board = dao.selectOne(num); // 게시물 조회

		request.setAttribute("board", board);
		String boardid = (String) request.getSession().getAttribute("boardid");
		if (boardid == null)  	boardid = "1";
		request.setAttribute("boardName", getBoardName(boardid));
		return "/view/board/replyForm.jsp";
	}

	
	
	
	
	
	public String replyPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		String boardid = (String) request.getSession().getAttribute("boardid");
		if (boardid == null) 	boardid = "1";
		Board board = new Board();
		board.setNum(Integer.parseInt(request.getParameter("num")));
		board.setRef(Integer.parseInt(request.getParameter("ref")));
		board.setReflevel(Integer.parseInt(request.getParameter("reflevel")));
		board.setRefstep(Integer.parseInt(request.getParameter("refstep")));
		board.setName(request.getParameter("name"));
		board.setPass(request.getParameter("pass"));
		board.setSubject(request.getParameter("subject"));
		board.setContent(request.getParameter("content"));
		board.setFile1("");
		board.setBoardid(boardid);
		BoardDao dao = new BoardDao();
		dao.refstepadd(board.getRef(), board.getRefstep());
		// 3. Board 객체를 db에 insert 하기.
		String msg = "답변등록시 오류발생";
		String url = "board/replyForm?num=" + board.getNum();
		if (dao.insert(board)) {
			msg = "답변등록 완료";	url = "board/list";
		}
		request.setAttribute("url", url);
		request.setAttribute("msg", msg);
		return "/view/alert.jsp";
	}
	
}
