import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;


public class DBFetcher {

	/**
	 * @param args
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 *
	 */

	Connection conn=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	
/*	String main_url;
	String url_domain_name;
*/	
/*	public DBFetcher(String murl, String dname) throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
	    conn = DriverManager.getConnection("jdbc:sqlite:/home/ashish/Documents/Files/apt_db");

		main_url = murl;
		url_domain_name = dname;
	}
*/
	public DBFetcher() throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
	    conn = DriverManager.getConnection("jdbc:sqlite:/home/ashish/Documents/Files/apt_db");
	}
	
		
//GrabHtml gh = new GrabHtml(main_url,url_domain_name);
	GrabHtml gh = new GrabHtml();	
	public boolean checkWhitelist(String url) throws Exception
	{
		
		System.out.println(" in ccheckWhitelist");
		String temp=null;
		boolean url_present=false;
	    Statement stat = conn.createStatement();
	    ResultSet rs = stat.executeQuery("select * from banks");
	 	while(rs.next())
		{
			temp=rs.getString("url");
			if(temp.equals(url))
			{
				url_present=true;
				break;
			}
				
		}
		return url_present;
	}
	
	public boolean checkSuspiciouslist(String url) throws Exception
	{
		
		String temp=null;
		boolean url_spresent=false;
	    Statement stat = conn.createStatement();
	    ResultSet rs = stat.executeQuery("select * from suspsites");
	 	while(rs.next())
		{
			temp=rs.getString("surl");
			if(temp.equals(url))
			{
				url_spresent=true;
				break;
			}
				
		}
		return url_spresent;
	}
	
	
	public void addWhiteList(String id,String url) throws SQLException
	{
		
		ps = conn.prepareStatement("insert into whitelist values(?,?)");
		ps.setString(1,id);
		ps.setString(2,url);
	    ps.executeUpdate();
	    
	 	
	}
	
	
	public void addSuspList(String id,String url) throws SQLException
	{
		
		ps = conn.prepareStatement("insert into suspsites values(?,?)");
		ps.setString(1,id);
		ps.setString(2,url);
	    ps.executeUpdate();
	    
	}
	
	
	public boolean checkCSSLink(String main_url, String url_domain_name) throws Exception
	{
		String[] css_links = new String[50];
		css_links = gh.getcssName(main_url,url_domain_name);
		String temp=null;
		boolean csslink_present=false;
	    
		for(int css_count=0;css_links[css_count]!=null;css_count++)
		{
		
			System.out.println(css_links[css_count]);
			
		Statement stat = conn.createStatement();
		ResultSet rs = stat.executeQuery("select * from banks");
	 	while(rs.next())
		{
			temp=rs.getString("csslink");
			if(temp.contains(css_links[css_count]))
			{
				System.out.println("The link" + css_links[css_count] + "is present in database" );
				csslink_present=true;
				break;
			}
				
		}
		}
		return csslink_present;
	}
	
	
	public boolean checkCSSContent(String main_url,String dname) throws Exception
	{
		
		String[] css_links = new String[50];
		css_links = gh.getcss(main_url,dname);
		String css = gh.getCSSContent(css_links);
		String temp=null;
		boolean css_present=false;
	    
		//for(int css_count=0;css!=null;css_count++)
		if(css!=null)
		{
		
			System.out.println(css);
			
		Statement stat = conn.createStatement();
		ResultSet rs = stat.executeQuery("select * from banks");
	 	while(rs.next())
		{
			temp=rs.getString("csscontent");
			if(css.contains(temp))
			{
				System.out.println("The css" + css + "is present in database" );
				css_present=true;
				break;
			}
				
		}
		}
		return css_present;
	}
	
	
	public boolean checkCSSdomainName(String main_url, String dname) throws Exception
	{
		String[] css_links = new String[50];
		String css_domain = null;
		css_links = gh.getcss(main_url,dname);
		if(css_links[0].equals("\0"))
		{
			System.out.println("sorry, no css link is present so no need to fetch domain ");
			return false;
		}
		css_domain= gh.getCSSDomain(css_links[0], dname);
		String temp=null;
		boolean css_dnamepresent=false;
		
		System.out.println("css link - " + css_links[0]);
		System.out.println("css domain - " + css_domain);
		
		Statement stat = conn.createStatement();
		ResultSet rs = stat.executeQuery("select * from banks");
	 	while(rs.next())
		{
			temp=rs.getString("cssdomain");
			if(temp.equals(css_domain))
			{
				css_dnamepresent=true;
				break;
			}
		}
		return css_dnamepresent;
	}
	
	
	
	public boolean checkWebPTitle(String murl) throws Exception
	{
		
		String webt = new String();
		webt = gh.getWebpageTitle(murl);
		System.out.println("in dbfetcher"+webt);
		String[] wt_parts = webt.split(" ");
		int i;
		int word_match=0;
		String temp=null;
		boolean wt_present =false;
	    
		Statement stat = conn.createStatement();
		ResultSet rs = stat.executeQuery("select * from banks");
	 	while(rs.next())
		{
			temp=rs.getString("pagetitle");
			for(i=0;i<wt_parts.length;i++)
			{
				if(temp.contains(wt_parts[i]))
				{
					word_match++;
				}
			}
			if(word_match > wt_parts.length*0.5)
			{
				wt_present=true;
				break;
			}
		}
		return wt_present;
	}
	
	
	
	public boolean checkUrlShortening(String url) throws Exception
	{
		String temp=null;
		boolean urlshort_present=false;
	    Statement stat = conn.createStatement();
	    ResultSet rs = stat.executeQuery("select * from urlshort");
	 	while(rs.next())
		{
			temp=rs.getString("usurl");
			System.out.println("in urlshort"+temp+"*"+url+"*");
			if(temp.equals(url))
			{
				urlshort_present=true;
				break;
			}
				
		}
		return urlshort_present;
	
	}
	
	public boolean checkFreeWebHosting(String url) throws Exception
	{
		String temp=null;
		boolean urlshort_present=false;
	    Statement stat = conn.createStatement();
	    ResultSet rs = stat.executeQuery("select * from freehost");
	 	while(rs.next())
		{
			temp=rs.getString("fname");
			System.out.println("in fname"+temp+"*"+url+"*");
			if(temp.equals(url))
			{
				urlshort_present=true;
				break;
			}
				
		}
		return urlshort_present;
	
	}
	
	
	public boolean check_Toptarget(String url) throws Exception
	{
		
		String temp=null;
		boolean tt_present=false;
	    Statement stat = conn.createStatement();
	    ResultSet rs = stat.executeQuery("select * from banks");
	 	while(rs.next())
		{
	 		temp=rs.getString("name");
	 		tt_present=url.equalsIgnoreCase(temp);
	 		if(tt_present)
	 		{
	 			tt_present=true;
	 			break;
	 		}
	 			
	 		else
	 			tt_present=false;
	 		
		}	
	 	return tt_present;
	}
	
	
	
	public boolean check_shortforms(String url) throws Exception
	{
		String temp=null;
		boolean sf_present=false;
	    Statement stat = conn.createStatement();
	    ResultSet rs = stat.executeQuery("select * from banks");
	 	while(rs.next())
		{
	 		temp=rs.getString("short");
	 		sf_present=url.contains(temp);
	 		if(sf_present)
	 			break;
	 		else
	 			sf_present=false;
	 		
		}	
	 	return sf_present;
	}
	
	
	public boolean checkWebageKeywds(String url) throws Exception
	{
		int key_count=0;
		boolean key_present=false;
		String temp=null;
		BufferedReader bf = new BufferedReader(new FileReader("/home/ashish/test.txt"));
		//gh.connect(url);
		String inputLine;
		StringBuilder a = new StringBuilder();
 		Statement stat = conn.createStatement();
	    ResultSet rs = stat.executeQuery("select * from keywords");
	    while((inputLine = bf.readLine()) != null)
		{
			while (rs.next())
			{
				a.append(inputLine);
				temp=rs.getString("keyword");
				System.out.print("\nkeyword from db -> " + temp);
				if(inputLine.contains(temp))
				{
					key_count++;
					key_present=true;
					System.out.print("\nkeyword -> " + temp + " is present  ");
					System.out.println(key_count);
				}
				else
				{
					System.out.print("\nkeyword -> " + temp + " is not present  ");
				}
			}
			
		}
	    bf.close();
		return key_present;
	}
	
	
	
	public boolean checkurlKeywds(String url) throws Exception
	{
		boolean ukey_present=false;
		String temp=null;
		Statement stat = conn.createStatement();
	    ResultSet rs = stat.executeQuery("select * from keywords");
		while (rs.next())
		{
				temp=rs.getString("keyword");
				if(url.contains(temp))
				{
					ukey_present=true;
					break;
				}
				System.out.print("\nkeyword from db -> " + temp);
		
		}
		return ukey_present;
	}


	
	
}








