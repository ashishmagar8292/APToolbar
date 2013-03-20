

//import java.io.FileOutputStream;
//import java.io.BufferedReader;
import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.OutputStream;
import java.io.PrintWriter;
//import java.net.URL;
import java.sql.SQLException;
//import java.sql.SQLException;
//import java.io.PrintWriter;

//import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.json.JSONObject;





/**
 * Servlet implementation class try4
 */
@WebServlet("/try4")
public class Main_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	public String ash = "ashish";
	public String output = null;
    public Main_servlet() {
        super();
        
        
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 
		

			
						    
		
		
			}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	 @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		 
		 
		 
		 
		 
		System.out.println(output);
		
		/*		request.getParameter("toServer");
		
		 
		InputStream is = request.getInputStream();
		byte[] charr = new byte[is.available()];
		is.read(charr);
		String asht = new String(charr,"UTF-8");
		System.out.println("the request parameter is" + asht );
*/		
	
		
		 //request.setAttribute("Results", "ashish is great");
	     //RequestDispatcher dispatcher = request.getRequestDispatcher("/redirect.jsp");
	     //dispatcher.forward(request,response);
	     //String bits="bittuz";

		/*response.setHeader("name", "value");
		response.setContentType("text/javascript");
  		response.setStatus(HttpServletResponse.SC_OK);
*/
		
  		PrintWriter out = response.getWriter();

  		
  		output = request.getParameter("stringParameter");
		System.out.println("output is " + output);
		
		//String m_url=request.getParameter("main_url");
		String m_url = output;
		
		
		//String ash = request.getHeader();
		
		int th_val = 0;
		boolean var_wlist = false;
		boolean var_slist=false;
		//boolean var_susp=false;
		boolean var_tt = false;
		boolean var_urlshort=false;
		boolean var_fwhost=false;
		boolean var_ip=false;
		boolean var_sf=false;
		boolean var_key=false;
		boolean var_cssLink = false;
		boolean var_csscont = false;
		boolean var_cssdname = false;
		boolean var_pwd = false;
		boolean var_webtitle = false;
		boolean var_urlkey=false;
		boolean var_port = false;
		String result = null;
		String dname = new String();
		
		int susp_present=0,wl_present=0,pwd_present=1;
		
		DBFetcher obj = null;
		URLProcess uobj=null;
		GrabHtml gobj = null;
		
		uobj=new URLProcess();
		
		
				 
		
		
		
		dname=uobj.decodeURL(m_url);
		 
System.out.println("dname = " + dname);
		try {
			obj = new DBFetcher();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("second con");
		//gobj = new GrabHtml(m_url,dname);
		gobj = new GrabHtml();
		System.out.println("first constru");
		
		//PrintWriter out = response.getWriter();
		System.out.println("i m here");
        response.setContentType("text/html");
        try 
        {

        	var_slist=obj.checkSuspiciouslist(m_url);
         	if(var_slist==false)
     		{
     			out.println("url " + m_url + " is not Present in susplist<br>");
     			
     	   	}
     	   
     	   	else
     	   	{
     	   		out.println("url " + m_url + " is <b> present </b> in susplist<br>");
     	   		susp_present = 1;
 
         	   	
     	   	}
         	
         	
        	
        	var_wlist=obj.checkWhitelist(m_url);
        	if(var_wlist==false)
        	{
        		out.println("url " + m_url + " is not Present in whitelist<br>");
        	}		
        	else
        	{
        		out.println("url " + m_url + " is <b> present </b> in whitelist<br>");
        		wl_present = 1;
 
            }
        	
        	//String id = request.getParameter("T2");
        	//String url = request.getParameter("T1");
        	//obj.addWhiteList(id, url);
        	//out.print("Inserted")	;
        	
        	
        	
        	var_pwd = gobj.check_passwordfield(m_url);
        	if(var_pwd==false)
        	{
        		out.println("Password field not present<br>");
        		pwd_present=0;
        		
        	}		
        	else
        	{
        		out.println("Password field <b> present </b><br>");
        		th_val+=2;
            }
        	out.print("threshold value is"+th_val);
        	
        	
        	// URL Check starts
        	
        	
        	var_urlkey=obj.checkurlKeywds(m_url);
        	if(var_urlkey==false)
    		{
        		out.println("No keywords present in url<br>");
        		System.out.println("No keywords <b> present in url </b>");
    			
    	   	}
    	   	
    	   	else
    	   	{
    	   		out.println("keywords <b> present in url</b><br>");
    	   		System.out.println("keywords <b> present in url</b><br>");
    	   		th_val+=3;
        	   	
    	   	}
        	out.print("threshold value is"+th_val);
        	
        	String dname_parts[] = dname.split("\\.");
        	var_ip = uobj.check_IP(dname_parts[1]);
        	if(var_ip==false)
    		{
        		out.println("No IP address present<br>");
        		System.out.println("No IP address <b> present </b>");
    			
    	   	}
    	   	
    	   	else
    	   	{
    	   		out.println("IP address <b> present </b><br>");
    	   		System.out.println("IP address <b> present </b><br>");
    	   		th_val++;
        	   	
    	   	}
        	//th_val = uobj.calcThreshold(var_ip, th_val);
        	if(dname.contains(":"))
        	{
        		String parts[] = dname.split(":");
        		var_port = uobj.check_Portno(parts[1]);
        	}
        	if(var_port==false)
    		{
        		out.println("No Port no present<br>");
        		System.out.println("No Port no <b> present </b>");
    			
    	   	}
    	   	
    	   	else
    	   	{
    	   		out.println("Port no  <b> present </b><br>");
    	   		System.out.println("Port no <b> present </b><br>");
    	   		th_val++;
        	   	
    	   	}
        	
        	
        	//var_susp = uobj.check_suspChar(m_url);
        	int v_susp = uobj.check_suspChar(m_url);
        	if(v_susp==0)
    		{
    			out.println("No susp char present<br>");
    			
    	   	}
    	   	
    	   	else
    	   	{
    	   		out.println("susp char <b> present </b><br>" + v_susp);
    	   		th_val = th_val + v_susp;
        	   	
    	   	}
        	//th_val = uobj.calcThreshold(var_susp, th_val);
        	
        	
        	var_urlshort=obj.checkUrlShortening(dname);
            if(var_urlshort==false)
    		{
    			out.println("domain " + dname + " is not Present in urlshortening<br>");
    			
    			
    	   	}
    	   	
    	   	else
    	   	{
    	   		out.println("domain " + dname + " is <b> present </b> in urlshortening<br>");
    	   		th_val++;
    	   	}
        	//th_val = uobj.calcThreshold(var_urlshort, th_val);
       
        	
        	var_fwhost=obj.checkFreeWebHosting(m_url);
        	if(var_fwhost==false)
    		{
    			out.println("domain " + dname + " is not Present in freewebhost<br>");
    			
    	   	}
    	   	
    	   	else
    	   	{
    	   		out.println("domain " + dname + " is <b> present </b> in freewebhost<br>");
    	   		th_val++;
    	   	}
        	//th_val = uobj.calcThreshold(var_fwhost, th_val);
        	
        	
        	var_tt = obj.check_Toptarget(m_url);
        	if(var_tt==false)
    		{
    			out.println("No Top target name present");
    			
    	   	}
    	   	
    	   	else
    	   	{
    	   		out.println("Top target name <b> present </b>");
    	   		th_val+=2;
    	   	}
        	//th_val = uobj.calcThreshold(var_tt, th_val);
        	
        	var_sf=obj.check_shortforms(m_url);
        	if(var_sf==false)
    		{
    			out.println("url " + m_url + " does not have a shortform Present for the given url<br>");
    			
    	   	}
    	   	
    	   	else
    	   	{
    	   		out.println("url " + m_url + " has a shortform <b> present </b> for the given url<br>");
    	   		th_val+=2;
    	   	}
        	//th_val = uobj.calcThreshold(var_sf, th_val);
        	
        	
        	
        	//Webpage content checking 
        	
        	var_key=obj.checkWebageKeywds(m_url);
        	if(var_key==false)
    		{
    			out.println("keywords are not present in the given webpage<br>");
    			
    	   	}
    	   	
    	   	else
    	   	{
    	   		out.println("keywords are <b> present </b> in the given webpage<br>");
    	   		th_val++;
    	   	}
        	//th_val = uobj.calcThreshold(var_key, th_val);

            
        	var_webtitle=obj.checkWebPTitle(m_url);
        	if(var_webtitle==false)
    		{
    			out.println("webpage title is not present in the given webpage<br>");
    			
    	   	}
    	   	
    	   	else
    	   	{
    	   		out.println("webpage title is <b> present </b> in the given webpage<br>");
    	   		th_val+=3;
    	   	}
        	
        	//th_val = uobj.calcThreshold(var_webtitle, th_val);

        	
        	
        	// CSS checking
        	
        	var_cssLink = obj.checkCSSLink(m_url,dname);
        	if(var_cssLink==false)
    		{
    			out.println("css links are not present<br>");
    	   	}
    	   	
    	   	else
    	   	{
    	   		out.println("css links are <b> present </b><br>");
    	   		th_val+=3;
    	   	}
        	//th_val = uobj.calcThreshold(var_cssLink, th_val);
        	
        	
        	// 	CSS checking
        	var_cssLink = obj.checkCSSdomainName(m_url, dname);
        	if(var_cssLink==false)
    		{
    			out.println("css domain does not match<br>");
    	   	}
    	   	
    	   	else
    	   	{
    	   		out.println("css domain is <b> matched </b><br>");
    	   		th_val+=3;
    	   	}
        	//th_val = uobj.calcThreshold(var_cssLink, th_val);
        	
        	
        	//var_cssdname = obj.checkCSSdomainName();
        	if(var_cssdname==false)
    		{
    			out.println("css dname is not present <br>");
    	   	}
    	   	
    	   	else
    	   	{
    	   		out.println("css dname is <b> present </b><br>");
    	   		th_val+=3;
    	   	}
        	//th_val = uobj.calcThreshold(var_cssdname, th_val);
        	
        	
        	
        	var_csscont = obj.checkCSSContent(m_url,dname);
        	if(var_csscont==false)
    		{
    			out.println("css cont is not present <br>");
    	   	}
    	   	
    	   	else
    	   	{
    	   		out.println("css cont is <b> present </b><br>");
    	   		th_val+=3;
    	   	}
        	//th_val = uobj.calcThreshold(var_csscont, th_val);
        	
        	
        	
        	if(wl_present == 1)
        	{
        		out.print("<br>The threshold value is:"+th_val);
        		out.print("<br><b>Site in whitelist,  The site is safe</b> ");
        		result="not phish";
        	}
        	else if(susp_present == 1)
        	{
        		out.print("<br>The threshold value is:"+th_val);
        		out.print("<br><b> Site in blacklst, The site is phish </b>");
        		result="phish";
        	}
        	else if(pwd_present == 0)
        	{
        		out.print("<br>The threshold value is:"+th_val);
        		out.print("<br><b> Password field not present, no risk of phishing </b> ");
        		result="not phish";
        	}

        	else if(th_val >= 7)
        	{
        		out.print("<br>The threshold value is:"+th_val);
        		out.print("<br><b> Threshold exceeded , The site is phish </b>");
        		result="phish";
        	}
        	else
        	{
        		out.print("<br>The threshold value is:"+th_val);
        		out.print("<br><b> The site is safe </b>");
        		result="not phish";
        		
        	}
        	
        	
    		
        	
    	
        	
        	} //end of try
        	catch(Exception e)
        	{
        		System.out.println(e);
            }

        	
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        pw.write(result);
        
         //System.out.println(result);
	     //response.sendRedirect("http://localhost:7001/APToolbar/redirect.jsp?result="+result);
	     //response.setHeader("result",result);
       

     }
     


	 	
	


}