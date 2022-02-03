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
import com.lq.bean.BinLin;
import com.lq.dao.QueryFromMySql;


@WebServlet("/QueryByShen")
public class QueryByShen extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public QueryByShen() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		QueryFromMySql queryFromMySql=new QueryFromMySql();
		request.setCharacterEncoding("utf-8");
		String shen=request.getParameter("shen");
		ArrayList<BinLin> binLins=queryFromMySql.SearchByShen(shen);
		System.out.println(binLins.get(0).toString());
		response.setContentType("text/html; charset=utf-8");
		JSONArray jsonArray=JSONArray.fromObject(binLins);   //将数据转为json类型
		System.out.println(jsonArray.toString());
//		PrintWriter writer = response.getWriter();
//		writer.println(jsonArray);
//		writer.flush();
//		writer.close();
		request.setAttribute("binlin", binLins);
		request.getRequestDispatcher("/charts/charts-area.jsp?shen="+shen).forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}

}
