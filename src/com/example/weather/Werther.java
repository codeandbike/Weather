package com.example.weather;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.cityID_Data.cityID_Data;
import com.example.jsonTools.JsonTools;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Werther extends Activity {

	private ViewPager viewPager;
	private List<View> views;// �����л�������View
	private String phah = "";// ��ȡ���ݵ�URL
	private List<String> myData;
	private Button cx_but;
	private EditText editCity;
	private TextView weather;
	private TextView wind;
	private TextView dateTime;
	private TextView temp;
	private TextView city;
	private TextView city1;
	private TextView wind1;
	private TextView index;
	private TextView weather1;
	private TextView index_co;
	private TextView index_ls;
	private TextView index_cl;
	private TextView index_ag;
	private TextView index_uv;
	private TextView index_xc;
	private TextView index_tr;
	private AlertDialog dialog;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.werther);
		viewPager = (ViewPager) this.findViewById(R.id.viewpager);
		editCity = new EditText(this);
		// ��̬���������໬�Ĳ���
		View view1 = LayoutInflater.from(Werther.this).inflate(
				R.layout.base_msg, null);
		View view2 = LayoutInflater.from(Werther.this).inflate(
				R.layout.life_msg, null);
		// ���ز����е�UI
		weather = (TextView) view1.findViewById(R.id.weather);
		wind = (TextView) view1.findViewById(R.id.wind);
		dateTime = (TextView) view1.findViewById(R.id.date);
		temp = (TextView) view1.findViewById(R.id.temp);
		city = (TextView) view1.findViewById(R.id.city);
		city1 = (TextView) view2.findViewById(R.id.city1);
		wind1 = (TextView) view2.findViewById(R.id.wind1);
		weather1 = (TextView) view2.findViewById(R.id.weather1);
		index = (TextView) view2.findViewById(R.id.index);
		index_co = (TextView) view2.findViewById(R.id.index_co);
		index_ls = (TextView) view2.findViewById(R.id.index_ls);
		index_cl = (TextView) view2.findViewById(R.id.index_cl);
		index_ag = (TextView) view2.findViewById(R.id.index_ag);
		index_uv = (TextView) view2.findViewById(R.id.index_uv);
		index_xc = (TextView) view2.findViewById(R.id.index_xc);
		index_tr = (TextView) view2.findViewById(R.id.index_tr);

		// �������л����������ּ��ش�views��������
		views = new ArrayList<View>();
		views.add(view1);
		views.add(view2);
		// ����������ʵ�ֲ໬Ч��
		viewPager.setAdapter(new MyAdapter());
		// �����Ի���Dialog
		dialog();

		cx_but = (Button) view1.findViewById(R.id.cx_but);
		cx_but.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// ��ʾ�Ի���
				dialog.show();

			}
		});

	}

	/**
	 * �����Ի���Dialog�ķ���
	 */
	private void dialog() {
		AlertDialog.Builder dBuilder = new AlertDialog.Builder(Werther.this);
		dBuilder.setTitle("���������").setIcon(android.R.drawable.ic_dialog_info)
				.setView(editCity)
				.setPositiveButton("ȷ��", new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						String cityName = "";
						String str1 = "";
						cityName = editCity.getText().toString();
						Map<String, String> map_City = new HashMap<String, String>();
						map_City = cityID_Data.getMap();
						str1 = map_City.get(cityName);
						phah = "http://m.weather.com.cn/data/" + str1 + ".html";
						String jsonString = "";
						// ��ʼ��������
						JsonTools jsonTools = new JsonTools();
						try {
							// ��ȡJSON����
							jsonString = new String(jsonTools
									.readInputStream(jsonTools
											.getInputStream(phah)), "utf-8");
							myData = new ArrayList<String>();
							// ����JSON���ݱ�����list������
							myData = jsonTools.readJson(jsonString);
							weather.setText(myData.get(10));
							wind.setText(myData.get(16));
							dateTime.setText(myData.get(1) + " / "
									+ myData.get(2));
							temp.setText(myData.get(3) + "��");
							city.setText(myData.get(0));
							city1.setText(myData.get(0));
							weather1.setText(myData.get(10));
							wind1.setText(myData.get(16));
							index.setText("����ָ����" + myData.get(22));
							index_co.setText("���ʶȣ�" + myData.get(26));
							index_ls.setText("��ɹָ����" + myData.get(28));
							index_cl.setText("������" + myData.get(27));
							index_ag.setText("������" + myData.get(29));
							index_uv.setText("�����ߣ�" + myData.get(23));
							index_xc.setText("ϴ����" + myData.get(24));
							index_tr.setText("���Σ�" + myData.get(25));
						} catch (UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				}).setNegativeButton("ȡ��", null);
		dialog = dBuilder.create();

	}

	/**
	 * 
	 * @date 2013-4-17
	 * @time ����9:22:34
	 * @author ���Ҷ�
	 * 
	 *         ��˵����ʵ�ֲ໬����������
	 */
	class MyAdapter extends PagerAdapter {

		// �л���View�ĸ���
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return views.size();
		}

		// ���ٻ�����view
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub

			((ViewPager) container).removeView(views.get(position));
		}

		// ��ȡ���������
		@Override
		public CharSequence getPageTitle(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		// ��ʼ������
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			// TODO Auto-generated method stub
			((ViewPager) container).addView(views.get(position));
			return views.get(position);
		}

		// �ж�view��object�Ƿ��й���
		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0 == arg1;
		}

	}

}
