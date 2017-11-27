package helloworld.twolayoutlistview;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.CheckBox;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    MyAdapter listAdapter;
    ArrayList arrListString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) this.findViewById(R.id.listview);
        arrListString = new ArrayList();
        for (int i = 0; i < 20; i++) {
            arrListString.add(Integer.toString(i));
        }
        listAdapter = new MyAdapter(this);
        listView.setAdapter(listAdapter);
    }

    class MyAdapter extends BaseAdapter {
        Context mContext;
        LayoutInflater inflater;
        final int TYPE_1 = 0;
        final int TYPE_2 = 1;

        public MyAdapter(Context context) {
            mContext = context;
            inflater = LayoutInflater.from(mContext);
        }

        @Override
        public int getCount() {
            return arrListString.size() + arrListString.size() / 6 + 1;
        }

        //每个convert view都会调用此方法，获得当前所需要的view样式
        @Override
        public int getItemViewType(int position) {
            int p = position % 6;
            if (p == 0)
                return TYPE_1;
            else if (p < 6)
                return TYPE_2;
            else
                return TYPE_1;
        }

        @Override
        public int getViewTypeCount() {
            return 2;
        }

        @Override
        public Object getItem(int arg0) {
            return arrListString.get(arg0);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            viewHolder1 holder1 = null;
            viewHolder2 holder2 = null;
            int type = getItemViewType(position);
            //无convertView，需要new出各个控件
            if (convertView == null) {
                Log.e("convertView = ", " NULL");
                //按当前所需的样式，确定new的布局
                switch (type) {
                    case TYPE_1:
                        convertView = inflater.inflate(R.layout.listitem1, parent, false);
                        holder1 = new viewHolder1();
                        holder1.textView = (TextView) convertView.findViewById(R.id.textview1);
                        holder1.checkBox = (CheckBox) convertView.findViewById(R.id.checkbox);
                        Log.e("convertView = ", "NULL TYPE_1");
                        convertView.setTag(holder1);
                        break;
                    case TYPE_2:
                        convertView = inflater.inflate(R.layout.listitem3, parent, false);
                        holder2 = new viewHolder2();
                        holder2.textView = (TextView) convertView.findViewById(R.id.textview3);
                        holder2.imageView = (ImageView) convertView.findViewById(R.id.imageview);
                        Log.e("convertView = ", "NULL TYPE_2");
                        convertView.setTag(holder2);
                        break;
                }
            } else {
                //有convertView，按样式，取得不同的布局
                switch (type) {
                    case TYPE_1:
                        holder1 = (viewHolder1) convertView.getTag();
                        Log.e("convertView !!!!!!= ", "NULL TYPE_1");
                        break;
                    case TYPE_2:
                        holder2 = (viewHolder2) convertView.getTag();
                        Log.e("convertView !!!!!!= ", "NULL TYPE_2");
                        break;
                }
            }

            //赋值
            switch (type) {
                case TYPE_1:
                    //holder1.textView.setText(Integer.toString(position));
                    holder1.checkBox.setChecked(true);
                    break;
                case TYPE_2:
                    holder2.textView.setText(Integer.toString(position - position / 6 - 1));
                    holder2.imageView.setBackgroundResource(R.mipmap.ic_launcher);
                    break;
            }
            return convertView;
        }
    }

    //各个布局的控件资源
    class viewHolder1 {
        CheckBox checkBox;
        TextView textView;
    }

    class viewHolder2 {
        ImageView imageView;
        TextView textView;
    }
}
