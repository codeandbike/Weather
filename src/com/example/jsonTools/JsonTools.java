package com.example.jsonTools;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

/**
 * @date	2013-4-17
 * @time	下午8:22:24
 * @author	 汪家栋
 *
 * 类说明:  用来处理JSON的数据，以字符串的形式显示在UI上
 */
public class JsonTools {
	
	/**
	 * 通过JSONObject对象解析出JSON数据
	 * 
	 * @param jsonStr
	 * @return
	 * @throws Exception
	 */
	public List<String> readJson(String jsonStr) throws Exception {
		JSONObject jsonObject = new JSONObject(jsonStr);
		JSONObject jsonObject2 = jsonObject.getJSONObject("weatherinfo");
		String jsoncity = jsonObject2.getString("city");
		String jsondate_y = jsonObject2.getString("date_y");
		String jsonweek = jsonObject2.getString("week");
		String jsonfchh = jsonObject2.getString("fchh");
		String jsontemp1 = jsonObject2.getString("temp1");
		String jsontemp2 = jsonObject2.getString("temp2");
		String jsontemp3 = jsonObject2.getString("temp3");
		String jsontemp4 = jsonObject2.getString("temp4");
		String jsontemp5 = jsonObject2.getString("temp5");
		String jsontemp6 = jsonObject2.getString("temp6");
		String jsonweather1 = jsonObject2.getString("weather1");
		String jsonweather2 = jsonObject2.getString("weather2");
		String jsonweather3 = jsonObject2.getString("weather3");
		String jsonweather4 = jsonObject2.getString("weather4");
		String jsonweather5 = jsonObject2.getString("weather5");
		String jsonweather6 = jsonObject2.getString("weather6");
		String jsonweawind1 = jsonObject2.getString("wind1");
		String jsonweawind2 = jsonObject2.getString("wind2");
		String jsonweawind3 = jsonObject2.getString("wind3");
		String jsonweawind4 = jsonObject2.getString("wind4");
		String jsonweawind5 = jsonObject2.getString("wind5");
		String jsonweawind6 = jsonObject2.getString("wind6");
		String jsonweaindex = jsonObject2.getString("index");
		String jsonweaindex_uv = jsonObject2.getString("index_uv");
		String jsonindex_xc = jsonObject2.getString("index_xc");
		String jsonindex_tr = jsonObject2.getString("index_tr");
		String jsonindex_co = jsonObject2.getString("index_co");
		String jsonindex_cl = jsonObject2.getString("index_cl");
		String jsonindex_ls = jsonObject2.getString("index_ls");
		String jsonindex_ag = jsonObject2.getString("index_ag");
		
		List<String> list = new ArrayList<String>();
		list.add(jsoncity);
		list.add(jsondate_y);
		list.add(jsonweek);
		list.add(jsonfchh);
		list.add(jsontemp1);
		list.add(jsontemp2);
		list.add(jsontemp3);
		list.add(jsontemp4);
		list.add(jsontemp5);
		list.add(jsontemp6);
		list.add(jsonweather1);
		list.add(jsonweather2);
		list.add(jsonweather3);
		list.add(jsonweather4);
		list.add(jsonweather5);
		list.add(jsonweather6);
		list.add(jsonweawind1);
		list.add(jsonweawind2);
		list.add(jsonweawind3);
		list.add(jsonweawind4);
		list.add(jsonweawind5);
		list.add(jsonweawind6);
		list.add(jsonweaindex);
		list.add(jsonweaindex_uv);
		list.add(jsonindex_xc);
		list.add(jsonindex_tr);
		list.add(jsonindex_co);
		list.add(jsonindex_cl);
		list.add(jsonindex_ls);
		list.add(jsonindex_ag);

		return list;
	}

	/**
	 * 通过URL获取JSON数据，以流的方式获取
	 * 
	 * @param myUrl
	 * @return
	 * @throws Exception
	 */
	public InputStream getInputStream(String myUrl) throws Exception {
		InputStream inputStream = null;
		URL url = new URL(myUrl);
		HttpURLConnection httpURLConnection = (HttpURLConnection) url
				.openConnection();
		httpURLConnection.setReadTimeout(3000);
		httpURLConnection.setDoInput(true);
		httpURLConnection.setDoOutput(true);
		httpURLConnection.setRequestMethod("GET");
		inputStream = httpURLConnection.getInputStream();
		return inputStream;
	}

	/**
	 * 将获取到的流，以字节数组的形式输出
	 * 
	 * @param inputStream
	 * @return
	 * @throws Exception
	 */
	public byte[] readInputStream(InputStream inputStream) throws Exception {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		byte[] data = new byte[1024];
		int len = 0;
		while ((len = inputStream.read(data)) != -1) {
			outputStream.write(data, 0, len);
		}
		outputStream.close();

		return outputStream.toByteArray();
	}

}
 