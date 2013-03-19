import java.util.StringTokenizer;

//import java.util.StringTokenizer;

public class URLProcess 
{
	//String main_url = "";
	
			
	public String decodeURL(String main_url)
	{
		int i=0;
		//String arr = main_url;
		int token_count = 0;
		//String parts[] = arr.split("\\.");
		String domain_name = null;
		int st_count=0;
		String[] slash_tokens = new String[100];
		
		System.out.print("in decode url");
		
		String http= "http://";
		if(main_url.charAt(0) != 'h')
		{
			//System.out.print(main_url);
			System.out.print("in do if dukkkar shra");
			//main_url = "http:" + main_url ;
			//http.concat(main_url);
			//System.out.print(http+main_url);
			main_url = http + main_url;
			
			System.out.print(main_url);
		}
		StringTokenizer st = new StringTokenizer(main_url,"/");
		
		i=0;
		String dom_url = null;
		while(st.hasMoreElements())
		{
				
				
				slash_tokens[i++] =  (String) st.nextElement();
				if(i==1)
				{
					dom_url = (String) st.nextElement();
					break;
				}
		}
		
		//st_count = st.countTokens();
		System.out.println("slashtokencount  = "  + st_count + "dom_url = " + dom_url);
		
		
		
		
		
		
		
		
		
		
		StringTokenizer st1 = new StringTokenizer(dom_url,".");
		token_count = st1.countTokens();
		String[] tokens = new String[100];
		i=0;
		while(st1.hasMoreElements())
		{
			tokens[i] = (String) st1.nextToken();	
			System.out.println("token  = " + tokens[i]);
			i++;
			
		}
		System.out.println("tokencount  = "  + token_count);
		System.out.println("last token is  = "  + tokens[token_count-1]);
		if(true)
		//if(tokens[token_count-1].equals("it")) // || tokens[token_count-1] == "in")
		{
				/*if(token_count>2)
		
				{
					System.out.println("in if");
					domain_name = tokens[token_count-3] + "." + tokens[token_count-2] + "." + tokens[token_count-1];
				}
				else*/
				{
					System.out.println("in else");
					domain_name = tokens[token_count-2] + "." + tokens[token_count-1];
				}
				
		}
		System.out.println("domain name is " + domain_name);
		
		
		
		return domain_name;
		
	}
	
	public boolean check_IP(String url)
	{
		boolean flag=false;
		for(int i=0;i<url.length();i++)
		{
			if((((int)url.charAt(i)>=48) && ((int)url.charAt(i)<=57)))
			{
				System.out.println(url.charAt(i)+" : " + (int)url.charAt(i));
				flag=true;
			}
			else
			{
				System.out.println(url.charAt(i)+" : " + (int)url.charAt(i));
				flag=false;
				break;
			}
		}
			
		return flag;
		
	}

	
	public boolean check_Portno(String url)
	{
		boolean flag=false;
		for(int i=0;i<url.length();i++)
		{
			if((((int)url.charAt(i)>=48) && ((int)url.charAt(i)<=57)))
			{
				System.out.println(url.charAt(i)+" : " + (int)url.charAt(i));
				flag=true;
			}
			else
			{
				System.out.println(url.charAt(i)+" : " + (int)url.charAt(i));
				flag=false;
				break;
			}
		}
			
		return flag;
		
	}

	public int check_suspChar(String url)
	{
		int s_value=0,dot_count=0;
		//boolean flag=false;
		for(int i=0;i<url.length();i++)
		{
			if(url.charAt(i)=='@')
			{
				System.out.println(url.charAt(i));
				s_value++;
				//flag=true;
			}
			
			if(url.charAt(i)=='-')
			{
				System.out.println(url.charAt(i));
				s_value++;
			
			}
			if(url.charAt(i)== '.')
			{
				dot_count++;
			}
			
			if (url.charAt(i)=='%')
			{
				System.out.println(url.charAt(i));
				if(url.charAt(i+1) == '4' && url.charAt(i+2)=='0')
				{
					System.out.println(url.charAt(i+1)+url.charAt(i+2));
					s_value++;
				//	flag=true;
				}								
			}
				
		}
		StringTokenizer st= new StringTokenizer(url,"//",false);
		int token_count = st.countTokens();
		if(token_count>2)
		{
			s_value++;
			//flag=true;
		}
		if(dot_count>4)
		{
			s_value++;
		}
		return s_value;
		
	}
	
	/*public int calcThreshold(boolean var_truthvalue,int t)
	{
		if(var_truthvalue==true)
		{
			++t;
			System.out.println("t = " + t + " truth value = " + var_truthvalue );
			return t;
		}
		else
		{
			System.out.println("t = " + t + " truth value = " + var_truthvalue );
			return t;
		}
	}
	*/
}