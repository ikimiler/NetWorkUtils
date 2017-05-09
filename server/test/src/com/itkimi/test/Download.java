package com.itkimi.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Download extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("Content-Disposition","attachment;filename=atlas-master.zip");
		String path = getServletConfig().getServletContext().getRealPath("/upload/atlas-master.zip");
		File file = new File(path);
		response.setContentLength((int) file.length());
		FileInputStream fileInputStream = new FileInputStream(file);
		byte[] bys = new byte[1024];
		int len = -1;
		while((len = fileInputStream.read(bys)) != -1){
			response.getOutputStream().write(bys,0,len);
			response.getOutputStream().flush();
		}
		fileInputStream.close();
		response.getOutputStream().close();
	}


}
