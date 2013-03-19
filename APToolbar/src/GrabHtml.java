
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
//import java.io.IOException;
//import java.io.FileReader;
//import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStreamReader;
//import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
//import java.util.Scanner;
import java.util.StringTokenizer;

//import com.bea.common.security.xacml.IOException;



 
public class GrabHtml
{
	
 URLProcess uob = new URLProcess();
/* String main_url; //= "";
 String url_domain_name; //="";
*/ 
 /*public GrabHtml(String m_url,String dname)
 {
	 main_url = m_url;
	 url_domain_name=dname;
 }*/
 
 public GrabHtml()
 {
	 
 }


public void processSourceCode(String murl) throws Exception
{
	String[] css_links = new String[50];
	String[] css_content = new String[50];
	String css_domain = null;
		System.out.println("program started " + murl);
	connect(murl);
	//css_links = getcss();
	for(int css_count=0;css_links[css_count]!=null;css_count++)
	{
		System.out.println(css_links[css_count]);
	}
	
	//css_content = getCSSContent(css_links);
	//css_domain = getCSSDomain(css_links[0]);
	System.out.println("css domain" + css_domain);
	
		for(int css_count=0;css_content[css_count]!=null;css_count++)
	{
		System.out.println(css_content[css_count]);
	}
	
}

public String getCSSContent(String[] css_links) throws Exception
{
	int link_count = 0;
	int content_count = 0;
	String[] css = new String[50];
	try
	 {
		
		
	//while(css_links[link_count]!="\0")
		while(link_count == 0)
	{
			URL css_link = new URL(css_links[link_count]);
			URLConnection yc = css_link.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					yc.getInputStream(), "UTF-8"));
			String inputLine;
			StringBuilder a = new StringBuilder();
     
     
     
			while ((inputLine = in.readLine()) != null)
			{
				a.append(inputLine);
				css[content_count] = "";
				css[content_count] += inputLine;

			}
			in.close();
			content_count++;
			link_count++;
	}
	css[content_count]="\0";
	
   
	  }catch(Exception e)
	  {
	  }
	return css[0];
}

 public void connect(String murl) throws Exception
 {
 	 try
	 {
	  URL url = new URL(murl);
      URLConnection yc = url.openConnection();
      BufferedReader in = new BufferedReader(new InputStreamReader(
              yc.getInputStream(), "UTF-8"));
      String inputLine;
      StringBuilder a = new StringBuilder();
      File file = new File("/home/ashish/test.txt");
      
      if (file.createNewFile()){
        System.out.println("File is created!");
      }else
      {
        System.out.println("File already exists.");
      }
      OutputStream f1 = new FileOutputStream("/home/ashish/test.txt");
      byte buff[];
      
      
      while ((inputLine = in.readLine()) != null)
      {
    	  a.append(inputLine);
    	  buff = inputLine.getBytes();
    	  f1.write(buff);
      }
      in.close();
      f1.close();
      System.out.println("End of page.");
    
	  }catch(Exception e)
	  {
	  }
 
 }
 	 
 	public String[] getcss(String main_url, String url_domain_name) throws Exception
 	{
 	System.out.println("in getcss");
 	BufferedReader bf = new BufferedReader(new FileReader("/home/ashish/test.txt"));
 	StringTokenizer st = new StringTokenizer(bf.readLine(),"<>");
 	String[] css_links = new String[50];
 	int css_count = 0;
 	  
 	while (st.hasMoreElements()) 
 	{
 		//System.out.println("in while css check");
        String token = (String) st.nextElement();
    	if(token.contains(".css") && token.contains("link"))
        {
    		System.out.println("in first if css check");	
        	StringTokenizer st1 = new StringTokenizer(token,"\"\"\'\'&");
        	while (st1.hasMoreElements()) 
         	{
                String token1 = (String) st1.nextElement();
                if(token1.contains(".css"))
                {
                	System.out.println("in second if css check");
                	System.out.print(token1);
                 	//css_links[css_count] = token1;
                 	String[] css_parts = token1.split("/");
                 	System.out.print(css_parts.length);
                 	css_links[css_count] = css_parts[css_parts.length-1];
                 	
                 	
                 	String[] link_parts = token1.split(".");
                 	if(link_parts.length<=2)
                 	{
                 	
                 		//String temp = null;
                 		System.out.println("in third if css check");
                 	//	StringTokenizer st2 = new StringTokenizer(main_url,url_domain_name,true);
                 	//	System.out.println("token count = " + st2.countTokens());
                 		System.out.println("url = " + main_url + " dmoani name " + url_domain_name);
                 		String[] url_parts = main_url.split(url_domain_name);
                 		System.out.println("url parts = 1. " + url_parts[0] + "2. " + url_parts[1]);
                 		
                 		
                 		token1 = url_parts[0] + url_domain_name + "/" + token1;
                 	}
                 	else
                 	{
                 		css_links[css_count] = token1;
                 	}
                 	
                 	
                 	System.out.println("in getcss while" + css_links[css_count]);
                 	css_count++;
          
                }
         	}
        	
        }
      
 	}

 	  css_links[css_count]="\0";
	  // Close the file after done searching
	  for(css_count=0;css_links[css_count]!="\0";css_count++)
	  {
		  System.out.println(" link - "+css_links[css_count]);
	  }
	  bf.close();
	  return css_links;
 }
 	
 	public String[] getcssName(String main_url, String url_domain_name) throws Exception
 	{
 	System.out.println("in getcss");
 	BufferedReader bf = new BufferedReader(new FileReader("/home/ashish/test.txt"));
 	StringTokenizer st = new StringTokenizer(bf.readLine(),"<>");
 	String[] css_links = new String[50];
 	int css_count = 0;
 	  
 	while (st.hasMoreElements()) 
 	{
 	//	System.out.println("in while css check");
        String token = (String) st.nextElement();
    	if(token.contains(".css") && token.contains("link"))
        {
    		System.out.println("in first if css check");	
        	StringTokenizer st1 = new StringTokenizer(token,"\"\"\'\'&");
        	while (st1.hasMoreElements()) 
         	{
                String token1 = (String) st1.nextElement();
                if(token1.contains(".css"))
                {
                	System.out.println("in second if css check");
                	System.out.print(token1);
                 	//css_links[css_count] = token1;
                 	String[] css_parts = token1.split("/");
                 	System.out.print(css_parts.length);
                 	css_links[css_count] = css_parts[css_parts.length-1];
                                  	System.out.println("in getcss while" + css_links[css_count]);
                 	css_count++;
          
                }
         	}
        	
        }
      
 	}

 	  css_links[css_count]="\0";
	  // Close the file after done searching
	  for(css_count=0;css_links[css_count]!="\0";css_count++)
	  {
		  System.out.println(" link - "+css_links[css_count]);
	  }
	  bf.close();
	  return css_links;
 }
 	

 	

 	public boolean check_passwordfield(String murl) throws Exception
 	{
 	System.out.print(murl);
 	connect(murl);
    BufferedReader bf = new BufferedReader(new FileReader("/home/ashish/test.txt"));
 	StringTokenizer st = new StringTokenizer(bf.readLine(),"<>");
 	boolean pwd_present = false;
 	while (st.hasMoreElements()) 
 	{
        String token = (String) st.nextElement();
        if(token.contains("type=\'password\'") || token.contains("type=\"password\""))
    	{
        	pwd_present=true;
    		System.out.println("Password field present");
    	}
 	}
 	bf.close();
 	return pwd_present;
 	}
 	
 	
 	
 	public String getWebpageTitle(String murl) throws Exception
 	{
 	//connect(murl);
 	BufferedReader bf = new BufferedReader(new FileReader("/home/ashish/test.txt"));
 	StringTokenizer st = new StringTokenizer(bf.readLine(),"<>");
 	String wtitle = "";
 	System.out.println("in getwebpagetitle");
 	int flag=0;
 	while (st.hasMoreElements()&&flag==0) 
 	{
 		String token = (String) st.nextElement();
 		System.out.println("-" +token);
 		if(token.equals("title"))
 		{
 			//String token1 = (String) st.nextElement();
 			wtitle=(String)st.nextElement();
 			System.out.println(" link - "+wtitle);
 			bf.close();
 			return wtitle;
 			//flag=1;
 			//break;
 		}
 		
      	
     }
     
 	  //System.out.println(" link - "+wtitle);
	wtitle="blank";
	bf.close();
	return wtitle;
 }

 	//@SuppressWarnings("null")
	public String getCSSDomain(String css_link,String dname)
 	{
 		System.out.println(" in css domain name  ");
 		String css_domain = null;
 		URLProcess uobj = null;
 		uobj = new URLProcess();
		
 		System.out.println("css link " + css_link);
 		if(css_link!=null)
 		{
 		if(css_link.charAt(0) == '/' || css_link.charAt(0) == '.')
 		{
 			css_domain = dname;
 		}
 		else
 		{
 			css_domain = uobj.decodeURL(css_link);
 		}
		System.out.println("css domain name - "+ css_domain);
 		}
		return css_domain;
 		 		
 	}
 	
 	
}

	


 /* public static void main(String[] args){
 
  try{
   //Calling the Connect method
   Connect("http://google.com");
  }catch(Exception e){
 
  }
*/ 



