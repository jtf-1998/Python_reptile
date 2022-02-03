package com.lq.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import com.lq.bean.LeiXinTj;
import com.lq.dao.QueryFromMySql;

/**
 * Servlet implementation class QueryBySend_time
 */
@WebServlet("/QueryBySend_time")
public class QueryBySend_time extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryBySend_time() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		QueryFromMySql queryFromMySql=new QueryFromMySql();
		ArrayList<LeiXinTj> leiXinTjs=queryFromMySql.QueryBySend_time();
		response.setContentType("text/html; charset=utf-8");
		System.out.println("**************************************");
		JSONArray jsonArray=JSONArray.fromObject(leiXinTjs);
		System.out.println(jsonArray.toString());
		PrintWriter writer = response.getWriter();
		writer.println(jsonArray);
		writer.flush();
		writer.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
