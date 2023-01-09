package controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import DAO.WeddingDAO;
import DTO.WeddingList;
import DTO.WeddingUser;

@WebServlet("/")
public class WeddingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private WeddingDAO dao;
	private ServletContext ctx;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		// init은 서블릿 객체 생성시 딱 한번만 실행하므로 객체를 한번만 생성해 공유할 수 있다.
		dao = new WeddingDAO();
		// ServletContext : 웹어플리케이션의 자원관리
		ctx = getServletContext();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // request 객체에 저장된 한글 깨짐 방지
		doPro(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // request 객체에 저장된 한글 깨짐 방지
		doPro(request, response);
	}

	protected void doPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String context = request.getContextPath(); // 프로젝트 이름(메인)
		String command = request.getServletPath(); // 프로젝트 안에 jsp 경로 //?는 해당값 찾기
		String site = null;
		
		/*
		 * if(command == null) { //request를 또 한다 > service를 진행한다
		 * getServletContext().getRequestDispatcher("/list") .forward(request,
		 * response); }
		 */
		
		switch (command) {
		case "/write": // 글쓰기 화면
			site = "write.jsp";
			break;
		case "/logindex": // 로그인 화면
			site = "login.jsp";
			break;
		case "/list":  // 방명록 리스트
			site = getList(request);
			break;
		case "/view":  // 방명록 내용
			site = getView(request);
			break;
		case "/insert": // 방명록 insert 기능
			site = insertWedding(request);
			break;
		case "/login": // 로그인 insert 기능
			site = loginsert(request);
			break;
		case "/delete": // delete 기능
			site = deleteWedding(request);
			break;
		case "/update": // update 기능
			site = updateWedding(request);
			break;
		case "/edit": // 수정화면
			site = getViewForEdit(request);
			break;
			
		}
		
		if (site.startsWith("redirect:/")) { // 경로가 redirect로 시작하는가
			String rview = site.substring("redirect:/".length());
			// 문자열을 "redirect:/" 부분을 잘라서 뒷 부분만 나옴

			response.sendRedirect(rview); //

		} else { // forword
			ctx.getRequestDispatcher("/" + site).forward(request, response);
		}
	}


		public String getList(HttpServletRequest request) {
			List<WeddingList> list;

			try {
				list = dao.getList();
				request.setAttribute("weddingList", list);

			} catch (Exception e) {
				e.printStackTrace();
				ctx.log("게시판 목록 생성 과정에서 문제 발생");
				// 나중에 사용자한테 에러메세지를 보여주기 위해 저장
				request.setAttribute("error", "게시판 목록이 정상적으로 처리되지 않았습니다!");
			}

			return "list.jsp";
	}
		
		public String getView(HttpServletRequest request) {
			int list_no = Integer.parseInt(request.getParameter("list_no"));

			try {
				WeddingList w = dao.getView(list_no);
				request.setAttribute("weddingList", w);
				request.setAttribute("list_no", list_no);
				
			} catch (Exception e) {
				e.printStackTrace();
				ctx.log("게시물을 가져오는 과정에서 문제 발생");
				request.setAttribute("error", "게시물을 정상적으로 가져오지 못 했습니다!");
			}

			return "view.jsp";
		}
		
		public String getViewForEdit(HttpServletRequest request) {
			int list_no = Integer.parseInt(request.getParameter("list_no"));

			try {
				WeddingList w = dao.getView(list_no);
				request.setAttribute("weddingList", w);
				request.setAttribute("list_no", list_no);
			} catch (Exception e) {
				e.printStackTrace();
				ctx.log("게시글을 가져오는 과정에서 문제 발생!!");
				request.setAttribute("error", "게시글을 정상적으로 가져오지 못했습니다!!");
			}

			return "edit.jsp";
		}
		

		public String insertWedding(HttpServletRequest request) {
			// 객체 생성
			WeddingList l = new WeddingList();

			try {
				BeanUtils.populate(l, request.getParameterMap());
				dao.insertWedding(l);

			} catch (Exception e) {
				e.printStackTrace();
				ctx.log("추가 과정에서 문제 발생");
				try {
					// get방식으로 넘겨줄때 한글 깨짐 방지
					String encodeName = URLEncoder.encode("방명록 작성 실패!", "UTF-8");
					return "redirect:/list?error=" + encodeName;
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
			}

			return "redirect:/list";

		}
		
		public String loginsert(HttpServletRequest request) {
			// 객체 생성
			WeddingUser u = new WeddingUser();	

			try {
				BeanUtils.populate(u, request.getParameterMap());
				dao.loginsert(u);
				request.setAttribute("user", u);
				

			} catch (Exception e) {
				e.printStackTrace();
				ctx.log("추가 과정에서 문제 발생");
				try {
					// get방식으로 넘겨줄때 한글 깨짐 방지
					String encodeName = URLEncoder.encode("등록 실패!", "UTF-8");
					return "redirect:/write?error=" + encodeName;
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
			}

			return "write.jsp";

		}
		
		public String deleteWedding(HttpServletRequest request) {
	    	int list_no = Integer.parseInt(request.getParameter("list_no"));

	    	List<WeddingList> list;

			try {
				list = dao.getList();
				request.setAttribute("weddingList", list);
				dao.deleteWedding(list_no);
				
			} catch (Exception e) {
				e.printStackTrace();
				ctx.log("삭제 과정에서 문제 발생!!");
				request.setAttribute("error", "정상적으로 삭제되지 않았습니다!!");
				return getList(request);
			}
			return "redirect:/list";
		}
		
		
		public String updateWedding(HttpServletRequest request) {
			WeddingList l = new WeddingList();
			try {
				BeanUtils.populate(l, request.getParameterMap());
				dao.updateWedding(l);
			} catch (Exception e) {
				e.printStackTrace();
				ctx.log("수정 과정에서 문제 발생!!");
				request.setAttribute("error", "게시물이 정상적으로 수정되지 않았습니다!!");
				return getList(request);
			}
			
			return "redirect:/view?list_no=" + l.getList_no();
		}
}
