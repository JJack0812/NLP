package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet implementation class hello
 * 自己最开始写的一个和项目无关的servlet
 */
@WebServlet("/hello")
public class hello extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public hello() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("处理get");
		response.setContentType("text/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		int age=Integer.parseInt(request.getParameter("age"));
		String name=String.valueOf(request.getParameter("name"));
		JSONObject j=new JSONObject();
		try {
			j.put("age", age);
			j.put("name", name);
			
		} catch (JSONException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		out.println(j);
		out.println("123");
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		System.out.println("处理POST");
		response.setContentType("text/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		int age=Integer.parseInt(request.getParameter("age"));
		String name=String.valueOf(request.getParameter("name"));
		JSONObject j=new JSONObject();
		try {
			j.put("age", age);
			j.put("name", name);
			
		} catch (JSONException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		out.println(j);
	}
	

}
