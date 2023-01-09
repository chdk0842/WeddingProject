package DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DTO.WeddingList;
import DTO.WeddingUser;

public class WeddingDAO {

	final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:xe";

	// 데이터 베이스와의 연결수행 메소드
	public Connection open() {
		Connection conn = null;
		try {
			Class.forName(JDBC_DRIVER); // 비번 확인
			conn = DriverManager.getConnection(JDBC_URL, "test", "test1234");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn; // 데이터 베이스의 연결 객체를 리턴
	}
	
	// 게시판 리스트 가져오기
	public ArrayList<WeddingList> getList() throws Exception {
		Connection conn = open();
		ArrayList<WeddingList> weddingList = new ArrayList<>(); // board 객체를 저장할 arraylist

		String sql = "SELECT L.LIST_NO, L.TITLE, U.USER_NAME "
				+ "FROM WEDDING_LIST L "
				+ "JOIN WEDDING_USER U "
				+ "ON (L.USER_ID = U.USER_ID)";
		
		PreparedStatement pstmt = conn.prepareStatement(sql); // 쿼리문 등록 -> 컴파일
		ResultSet rs = pstmt.executeQuery(); // 쿼리문 실행 -> 데이터 베이스 결과 저장


		try (conn; pstmt; rs) {
			while (rs.next()) {
				WeddingList w = new WeddingList();
				w.setList_no(rs.getInt(1));
				w.setTitle(rs.getString(2));
				w.setUser_id(rs.getString(3));

				weddingList.add(w);
			}

			return weddingList;
		}

	}
	
	   // 뷰
		public WeddingList getView(int list_no) throws Exception {
			Connection conn = open();
			WeddingList w = new WeddingList();

			String sql = "SELECT L.TITLE, U.USER_NAME, L.CONTENT, U.USER_PW FROM WEDDING_LIST L JOIN WEDDING_USER U ON (L.USER_ID = U.USER_ID) WHERE LIST_NO=?";
		
			PreparedStatement pstmt = conn.prepareStatement(sql); // 쿼리문 등록 -> 컴파일
			pstmt.setInt(1, list_no); // 첫번째 ?에 이것을 넣겠다.
			ResultSet rs = pstmt.executeQuery(); // 쿼리문 실행 -> 데이터 베이스 결과 저장

			try (conn; pstmt; rs) {
				while (rs.next()) {
					w.setTitle(rs.getString(1));
					w.setUser_name(rs.getString(2));
					w.setContent(rs.getString(3));
					w.setUser_pw(rs.getString(4));
					
				}

				return w;
			}

		}
		
		//방명록 등록(리스트)
		public void insertWedding(WeddingList l) throws Exception {
			Connection conn = open();

			String sql = "INSERT INTO WEDDING_LIST VALUES(WEDDING_SEQ.nextval, ?, ?, ?)";			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			try (conn; pstmt) {
				pstmt.setString(1, l.getUser_id());
				pstmt.setString(2, l.getTitle());
				pstmt.setString(3, l.getContent());
				pstmt.executeUpdate();
			}
		}
		
		//로그인 등록 (유저)
		public void loginsert(WeddingUser u) throws Exception {
			Connection conn = open();

			String sql = "INSERT INTO WEDDING_USER VALUES(?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			try (conn; pstmt) {
				pstmt.setString(1, u.getUser_name());
				pstmt.setString(2, u.getUser_id());
				pstmt.setString(3, u.getUser_pw());
				pstmt.executeUpdate();
			}
		}
		
		
		// 글 삭제
		public void deleteWedding(int list_no) throws Exception {
			Connection conn = open();

			String sql = "delete from wedding_list where list_no=?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);

			try (conn; pstmt) {
				pstmt.setInt(1, list_no);
				// 삭제된 글이 없을 경우
				if (pstmt.executeUpdate() != 1) {
					throw new Exception("DB에러");
				}
			}
		}
		
		// 방명록 수정 글 보기
		public WeddingList getViewForEdit(int list_no) throws Exception {
			Connection conn = open();

			WeddingList l = new WeddingList();
			String sql = "SELECT L.TITLE, U.USER_NAME, L.CONTENT FROM WEDDING_LIST L JOIN WEDDING_USER U ON (L.USER_ID = U.USER_ID) where list_no=" + list_no;

			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

	        

			try (conn; pstmt; rs) {   
				
				if(rs.next()) {
					
				l.setTitle(rs.getString(1));
				l.setUser_name(rs.getString(2));
				l.setContent(rs.getString(3));
				
				}
				
				return l;
				
			}
			
			
		}
		
		// 게시판 글 수정
		public void updateWedding(WeddingList l) throws Exception {
			Connection conn = open();

			String sql = "update wedding_list set title = ?, content = ? where list_no = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);

			try (conn; pstmt) {
				pstmt.setString(1, l.getTitle());
				pstmt.setString(2, l.getContent());
				pstmt.setInt(3, l.getList_no());

				// 수정된 글이 없을 경우
				if (pstmt.executeUpdate() != 1) {
					throw new Exception("DB에러");
				}
			}
		}


}
