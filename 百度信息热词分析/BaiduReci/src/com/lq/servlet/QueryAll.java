package com.lq.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lq.bean.CiTiao;
import com.lq.dao.QueryFromMySql;

@WebServlet("/QueryAll")
public class QueryAll extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public QueryAll() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		QueryFromMySql queryFromMySql=new QueryFromMySql();
		request.setCharacterEncoding("utf-8");
		List<CiTiao> ciTiaos=queryFromMySql.SearchList();
		//System.out.println(xinJians.get(1).toString());
		int count=queryFromMySql.query();
		System.out.println(count);
		request.setAttribute("citiaos", ciTiaos);
		request.getRequestDispatcher("/list_all.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		doGet(request, response);
	}

}
