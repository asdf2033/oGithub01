package service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Board;
import dao.BoardDao;

public class ListUp implements BaseOfService {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BoardDao bd = BoardDao.getInstance();
		int currentPage = Integer.parseInt(request.getParameter("pageNum"));
		if(request.getParameter("pageNum")== null || request.getParameter("pageNum") == "") currentPage =1;
		
		
		
		try {
			int totCnt = bd.getTotCnt();
			int pageSize = 10, blockSize = 10;
//			int startPage = totCnt-
//			List<Board> boardList =  bd.boardList();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
		return "listUp.jsp";
	}

}
