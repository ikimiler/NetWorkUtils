package com.itkimi.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

public class Test extends HttpServlet {
	

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		String value1 = request.getParameter("key1");
		String value2 = request.getParameter("key2");
		
		Bean bean = new Bean();
		bean.value1 = value1;
		bean.value2 = value2;
		String json = JSON.toJSONString(bean);
		response.getWriter().write(json);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		if(request.getContentType().equals("application/json;charset=utf-8")){
			ServletInputStream inputStream = request.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
			StringBuffer sb = new StringBuffer();
			String line = null;
			while((line = br.readLine()) != null){
				sb.append(line);
			}
			inputStream.close();
			response.getWriter().write(sb.toString() );
		}else{
			this.doGet(request, response);
		}
	}
}
