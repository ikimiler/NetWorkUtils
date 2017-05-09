package com.itkimi.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Upload extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String path = getServletConfig().getServletContext().getRealPath("/upload");
		FileOutputStream fileOutputStream = new FileOutputStream(new File(path,"atlas-master.zip"));
		
		ServletInputStream inputStream = request.getInputStream();
		byte[] bys = new byte[1024];
		int len = 0;
		while( (len = inputStream.read(bys)) != -1){
			fileOutputStream.write(bys,0,len);
			fileOutputStream.flush();
		}
		
		fileOutputStream.close();
		inputStream.close();
		
		response.getWriter().write("上传成功");
	}





}
