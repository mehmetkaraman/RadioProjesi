package deneme.com.radioprojesi.radio;


import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import deneme.com.radioprojesi.R;

public class ListViewCustomAdapter extends BaseAdapter
{
    public String isimListesi[];
    public String urlListesi[];
    public int images[];
 
    public Activity context;
    public LayoutInflater inflater;
 
    public ListViewCustomAdapter(Activity context,ArrayList<String> isimListesi, ArrayList<String> urlListesi, int[] images) {
        super();
 
        this.context = context;
        this.isimListesi = isimListesi.toArray(new String[0]);
        this.urlListesi = urlListesi.toArray(new String[0]);
        this.images = images;
 
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
 
    public int getCount() {
        // TODO Auto-generated method stub
        return isimListesi.length;
    }
 
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }
 
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }
 
    public static class ViewHolder
    {
        ImageView imgViewLogo;
        TextView txtViewisimListesi;
        TextView txtViewurlListesi;
    }
 
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
 
        ViewHolder holder;
        if(convertView==null)
        {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.list_item, null);
 
            holder.imgViewLogo = (ImageView) convertView.findViewById(R.id.imageView);
            holder.txtViewisimListesi = (TextView) convertView.findViewById(R.id.name);
            holder.txtViewurlListesi = (TextView) convertView.findViewById(R.id.url);
 
            convertView.setTag(holder);
        }
        else
            holder=(ViewHolder)convertView.getTag();
 
        holder.imgViewLogo.setImageResource(images[position]);
        holder.txtViewisimListesi.setText(isimListesi[position]);
        holder.txtViewurlListesi.setText(urlListesi[position]);
 
        return convertView;
    }
 
}