package servlet;
/**
 * 处理文件上传的servlet
 */
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class FileUpServlet
 */
@WebServlet("/FileUpServlet")
public class FileUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FileUpServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		upload.setHeaderEncoding("UTF-8");
		
		factory.setSizeThreshold(1024 * 500);// 
		File linshi = new File("E:\\linshi");// 
		factory.setRepository(linshi);
		upload.setSizeMax(1024 * 1024 * 5);// 

		try {
			

			FileItem item = upload.parseRequest(request).get(0);
			

			String fileName = item.getName();
			long sizeInBytes = item.getSize();
			System.out.println(fileName);
			System.out.println(sizeInBytes);
			int fileType;

			BufferedReader br = new BufferedReader(new InputStreamReader(item.getInputStream()));

			PrintWriter pw = new PrintWriter(response.getOutputStream());
			String temp;

			while ((temp = br.readLine()) != null) {
				pw.println(temp + "\n");
				System.out.println(temp);
			}
			pw.flush();
			br.close();
			pw.close();

		} catch (FileUploadException e) {
			e.printStackTrace();
		}
	}

}
