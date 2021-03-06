package com.example.iningke.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.iningke.myapplication.adapter.HxyActivityAdapter;
import com.example.iningke.myapplication.bean.HxyActivityBean;
import com.example.iningke.myapplication.contact.ContactsFromPhoneActivity;
import com.example.iningke.myapplication.contact.GoToSystemContactActivity;
import com.example.iningke.myapplication.exlistview.MyExpandableListViewActivity;
import com.example.iningke.myapplication.gif.GifActivity;
import com.example.iningke.myapplication.iflytek.IFlyTekSpeechActivity;
import com.example.iningke.myapplication.mvp.activity.LoginActivity;
import com.example.iningke.myapplication.permission.TextPermission2Activity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HxyActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @Bind(R.id.hxy_listView)
    ListView hxyListView;
    private HxyActivityAdapter adapter;
    private List<HxyActivityBean> dataSource = new ArrayList<>();
    private ListView listView_footView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hxy);
        ButterKnife.bind(this);
        initView();
        setData();
//        setPullLvHeight(listView_footView);
    }

    private void setData() {

        dataSource.add(new HxyActivityBean("ScrollForeverTextView", "自定义无限循环的跑马灯"));
        dataSource.add(new HxyActivityBean("ContactsFromPhoneActivity", "自定义联系人列表界面"));
        dataSource.add(new HxyActivityBean("GoToSystemContactActivity", "直接跳系统的联系人界面"));
        dataSource.add(new HxyActivityBean("MyExpandableListViewActivity", "带分组的ListView"));
        dataSource.add(new HxyActivityBean("GifActivity", "用Glide加载GIF动态图，并且可缩放"));
        dataSource.add(new HxyActivityBean("IFlyTekSpeechActivity", "科大讯飞的语音朗读功能"));
        dataSource.add(new HxyActivityBean("TextToSpeechActivity", "android自带的语音朗读功能，仅支持英文,德语,意大利语,法语,西班牙语"));
        dataSource.add(new HxyActivityBean("LoginActivity", "MVP模式的一个简答的例子"));
        dataSource.add(new HxyActivityBean("TextPermission2Activity", "android6.0动态权限"));

        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                startActivity(new Intent(this, MarqueeActivity.class));
                break;
            case 1:
                startActivity(new Intent(this, ContactsFromPhoneActivity.class));
                break;
            case 2:
                startActivity(new Intent(this, GoToSystemContactActivity.class));
                break;
            case 3:
                startActivity(new Intent(this, MyExpandableListViewActivity.class));
                break;
            case 4:
                startActivity(new Intent(this, GifActivity.class));
                break;
            case 5:
                startActivity(new Intent(this, IFlyTekSpeechActivity.class));
                break;
            case 6:
                startActivity(new Intent(this, TextToSpeechActivity.class));
                break;
            case 7:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case 8:
                startActivity(new Intent(this, TextPermission2Activity.class));
                break;
        }
    }

    private void initView() {
        adapter = new HxyActivityAdapter(dataSource);
//        View headView = LayoutInflater.from(this).inflate(R.layout.activity_mpandroid_chart, null);
//        hxyListView.addHeaderView(headView);
//        View footView = LayoutInflater.from(this).inflate(R.layout.activity_hxy, null);
//        listView_footView = (ListView) footView.findViewById(R.id.hxy_listView);
//        listView_footView.setBackgroundColor(Color.RED);
//        listView_footView.setAdapter(adapter);
//        hxyListView.addFooterView(footView);
        hxyListView.setAdapter(adapter);
        hxyListView.setOnItemClickListener(this);

    }

    private void setPullLvHeight(ListView pull) {
        int totalHeight = 0;
        ListAdapter adapter = pull.getAdapter();
        for (int i = 0, len = adapter.getCount(); i < len; i++) { //listAdapter.getCount()返回数据项的数目
            View listItem = adapter.getView(i, null, pull);
            listItem.measure(0, 0); //计算子项View 的宽高
            totalHeight += listItem.getMeasuredHeight(); //统计所有子项的总高度
        }

        ViewGroup.LayoutParams params = pull.getLayoutParams();
        params.height = totalHeight + (pull.getDividerHeight() * (pull.getCount() - 1));
        pull.setLayoutParams(params);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }


}
