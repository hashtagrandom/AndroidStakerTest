package hashtagrandom.games.seppe.stakescape.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.test.suitebuilder.TestMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.InterfaceAddress;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import hashtagrandom.games.seppe.stakescape.R;
import hashtagrandom.games.seppe.stakescape.items.Item;
import hashtagrandom.games.seppe.stakescape.pojo.User;

/**
 * Created by Thomas Machiels on 21-11-2016.
 */

public class ImageAdapter extends BaseAdapter {
    private Context c;
    private User user;

    public ImageAdapter(Context c, User user){
        this.c = c;
        this.user = user;

        System.out.println("inventory" + user.getInventory().size());
    }

    @Override
    public int getCount() {
        if(user != null){
            return user.getInventory().size();
        }
        else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        List<Item> inventory = user.getInventory();
        List<Integer> images = new ArrayList<>();
        List<Integer> count = new ArrayList<>();
        for(Item i:inventory){
            images.add(i.getImageResource());
            count.add(i.getAmount());
        }
        View grid;
        LayoutInflater inflater = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(convertView == null){
            grid = new View(c);
            grid = inflater.inflate(R.layout.single_grid_item,null);
            TextView textView = (TextView)grid.findViewById(R.id.grid_text);
            ImageView imageView= (ImageView)grid.findViewById(R.id.grid_image);
            textView.setText(count.get(position)+"");
            imageView.setImageResource(images.get(position));
        }else{
            grid = (View)convertView;
        }

        return grid;
    }

}
