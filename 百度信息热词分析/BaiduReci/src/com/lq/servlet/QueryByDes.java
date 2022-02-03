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

@WebServlet("/QueryByDes")
public class QueryByDes extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public QueryByDes() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		QueryFromMySql queryFromMySql=new QueryFromMySql();
		request.setCharacterEncoding("utf-8");
		String des=request.getParameter("des");
		List<CiTiao> ciTiaos=queryFromMySql.SearchByDes(des);
		System.out.println(ciTiaos.get(0).toString());
		int count=queryFromMySql.query();
		System.out.println(count);
		request.setAttribute("citiaos", ciTiaos);
		request.getRequestDispatcher("/list_sort.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}

}
