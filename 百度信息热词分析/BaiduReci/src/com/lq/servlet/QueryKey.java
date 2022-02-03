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


@WebServlet("/QueryKey")
public class QueryKey extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public QueryKey() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		QueryFromMySql queryFromMySql=new QueryFromMySql();
		request.setCharacterEncoding("utf-8");
		String keyword=request.getParameter("keyword");
		List<CiTiao> ciTiaos=queryFromMySql.SearchByName(keyword);
		System.out.println(ciTiaos.get(0).toString());
		int count=queryFromMySql.query();
		System.out.println(count);
		request.setAttribute("citiaos", ciTiaos);
		request.getRequestDispatcher("/list_key.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}

}
