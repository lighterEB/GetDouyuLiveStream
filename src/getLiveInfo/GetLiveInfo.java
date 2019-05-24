package getLiveInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import java.lang.String;


public class GetLiveInfo {
	
	public static String sendPost(String url, String param){
		OutputStreamWriter out = null;
		BufferedReader reader = null;
		String res = "";
		try {
			URL httpUrl = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
			conn.setRequestProperty("Host", "playweb.douyucdn.cn");
			conn.setRequestProperty("Referer", "https://www.douyu.com/directory/myFollow");
			conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.84 Safari/537.36");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setRequestProperty("rid", "这里改成抓包获取的数据和time，auth关联");
			conn.setRequestProperty("time", "同上");
			conn.setRequestProperty("auth", "同上");
			conn.setUseCaches(false);
			conn.setInstanceFollowRedirects(true);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.connect();
			
			out = new OutputStreamWriter(conn.getOutputStream());
			out.flush();
			reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
			String line;
			while ((line = reader.readLine()) != null) {
				res += line;
			}
			reader.close();
			conn.disconnect();
		}catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！" +e);
			e.printStackTrace();
		}
		finally {
			try {
				if(out!=null) {
					out.close();
				}
				
				if(reader!= null) {
					reader.close();
				}
			}
			catch(IOException ex) {
				ex.printStackTrace();
			}
		}
		return res;
	}
	public static String main(String roomid){
		String ret = GetLiveInfo.sendPost("http://playweb.douyucdn.cn/lapi/live/hlsH5Preview/"+roomid+"?rid="+roomid+"&did=", "");
		try {
			String[] strarray = ret.split(":")[8].split("_")[0].split("\"");
			ret = "http://tx2play1.douyucdn.cn/"+strarray[1]+"_4000.xs";
		}catch(ArrayIndexOutOfBoundsException e) {
			String[] strarray = ret.split(":")[2].split("\"");
			ret = strarray[1];
		}
		return ret;
	}
}
