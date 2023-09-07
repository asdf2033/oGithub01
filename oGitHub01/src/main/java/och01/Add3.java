package och01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Add3
 */
@WebServlet("/Add3") //address를 나타냄 annotation
public class Add3 extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Add3() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("doGet 여기 왔네 ");
		//parameter 받기
		int num = Integer.parseInt(request.getParameter("num"));  //클라이언트에서 text형태로 넘어왔기 때문에 int형으로 변경해야한다.
		String loc = request.getParameter("loc");
		System.out.println("num ->" + num);
		System.out.println("loc ->" + loc);
		//목표 : 1부터 누적값 전달 
		int sum = 0;
		for(int i = 1; i<=num; i++) {
			sum+=i;
		}
		System.out.println("sum -> " + sum); //개발자 방식 
		
		//Encoding 적용 - > browser로 보내기 전에 해야함
//		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8"); // utf-8로 설정, 들고다니기 
		
		
		PrintWriter out = response.getWriter();// 공식 ----> 사용자 Browser에 보여주는 객체
		out.println("<html><body>");
		out.printf("<h1>1부터 %d까지의 합계</h1>",num);
		out.printf("<h4>LOC --> %s</h4>",loc);
		out.println(sum);
		out.println("</body></html>"); // 단순 텍스트가 아닌 HTML문을 이용한 내용을 사용해야한다.
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("doPost 여기 왔네 ");
		System.out.println("doPost 여기 왔네 ");
		System.out.println("doPost 여기 왔네 ");
		System.out.println("doPost 여기 왔네 ");
		//Encoding 적용 - > browser로 보내기 전에 해야함
		request.setCharacterEncoding("utf-8");// request에 실려있는 한글 데이터를 인코딩해서 정상적으로 받을 수 있또록 해줌, Parameter를 받기 전에 해야한다.
		int num = Integer.parseInt(request.getParameter("num"));  //클라이언트에서 text형태로 넘어왔기 때문에 int형으로 변경해야한다.
		String loc = request.getParameter("loc");
		System.out.println("num ->" + num);
		System.out.println("loc ->" + loc);
		//목표 : 1부터 누적값 전달 
		int sum = 0;
		for(int i = 1; i<=num; i++) {
			sum+=i;
		}
		System.out.println("sum -> " + sum);
		
		//Encoding 적용 - > browser로 보내기 전에 해야함
		
		response.setContentType("text/html;charset=utf-8"); // utf-8로 설정, 들고다니기 
		/* request.setCharacterEncoding("utf-8"); */ // 받은 후에 넣어주면 깨친채로 받게 된다. 
		
		PrintWriter out = response.getWriter();// 공식 ----> 사용자 Browser에 보여주는 객체
		out.println("<html><body>");
		out.printf("<h1>1부터 %d까지의 합계(Post)</h1>",num);
		out.printf("<h4>LOC --> %s</h4>",loc);
		out.println(sum);
		out.println("</body></html>");
		out.close();
	}

}
