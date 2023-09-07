package och01;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BaseOfService;

/**
 * Servlet implementation class Conteroller
 */
//@WebServlet("/Conteroller")
public class Conteroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       Map<String, Object> ListSet = new HashMap<>();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Conteroller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		String cmdsProp = config.getInitParameter("Config");
		System.out.println("init cmdsProp->"+cmdsProp);
		Properties converP = new Properties();
		FileInputStream makeFile = null;
		
		try {
			String contextPath= config.getServletContext().getRealPath(cmdsProp);
			makeFile = new FileInputStream(contextPath);
			
			converP.load(makeFile);
			
		} catch (Exception e) {
			e.printStackTrace();			
			System.out.println("init e.getMessage()->"+e.getMessage());
		} finally {
			if(makeFile != null)
				try {
					makeFile.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		Iterator choose = converP.keySet().iterator();
		
		if(choose.hasNext()) {
			do {					// hasNext = 다음 걸 갖고 있는가? next= 포인터 이동한 대상
				// properties에 key값 가져오기
				String key = (String) choose.next();
				// 가져온 Key값으로 해당 프로퍼티의 내용인 service의 위치를 가져온다. String
				String serviAdd = converP.getProperty(key);
				
				try {
					Class<?> makeClass = Class.forName(serviAdd);
					BaseOfService makeIns = (BaseOfService) makeClass.getDeclaredConstructor().newInstance();
					ListSet.put(key, makeIns);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}catch (Exception e) {
					
				}
			}while(choose.hasNext());
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	protected void startServi(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("startServi Start>");

		String view= null;
		
		BaseOfService bos = null;
		String cutServiName= request.getRequestURI();
		cutServiName = cutServiName.substring(request.getContextPath().length());
		
		try {
			bos = (BaseOfService) ListSet.get(cutServiName);
			view = bos.requestPro(request, response);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("startServi e.getMessage()->"+e.getMessage());
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}

}
