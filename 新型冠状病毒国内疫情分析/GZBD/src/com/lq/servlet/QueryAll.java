package com.lq.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import com.lq.bean.BinLin;
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
		ArrayList<BinLin> binLins=queryFromMySql.SearchList();
		response.setContentType("text/html; charset=utf-8");
		JSONArray jsonArray=JSONArray.fromObject(binLins);   //将数据转为json类型
		System.out.println(jsonArray.toString());
		PrintWriter writer = response.getWriter();
		writer.println(jsonArray);
		writer.flush();
		writer.close();
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}

}
