package Peder.MySearch.master.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;

import Peder.MySearch.service.MasterService;

/**
 * Servlet implementation class Query
 */
@WebServlet("/Query")
public class Query extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(Query.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Query() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");

		Long startTime = System.currentTimeMillis();

		String query = new String(request.getParameter("query").getBytes(
				"iso8859-1"), "utf-8");
		System.out.println(query);
		MasterService ms = new MasterService();
		List<String> result=ms.getQueryIds(query);
		
		JSONArray json=JSONArray.fromObject(result);
		PrintWriter out = response.getWriter();
		out.print(json.toString());
		Long endTime = System.currentTimeMillis();
		logger.info("所有所花费的时间为：" + (endTime - startTime) + "ms");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
