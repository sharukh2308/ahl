package annahockeyleague.com.ahl;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Picture;
import android.graphics.drawable.Drawable;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by MANUTD on 1/23/2016.
 */
import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class CustomAdapter extends PagerAdapter{

    Context context;
    List<String> imageId;
    public CustomAdapter(Context context){
        this.context = context;

    }

    private List<String> getImage(Context context) throws IOException
    {
        AssetManager assetManager = context.getAssets();
        String[] files = assetManager.list("players");
        List<String> it= Arrays.asList(files);
        return it;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // TODO Auto-generated method stub

        LayoutInflater inflater = ((Activity)context).getLayoutInflater();

        View viewItem = inflater.inflate(R.layout.image_item_home, container, false);
        ImageView imageView = (ImageView) viewItem.findViewById(R.id.imageView);
        Picture picture=new Picture();
        try {
            InputStream ims = context.getAssets().open(getImage(context).get(position));
            Drawable d = Drawable.createFromStream(ims, null);
            imageView.setImageDrawable(d);
            ((ViewPager) container).addView(viewItem);
            return viewItem;
        } catch (IOException e){ return viewItem; }
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        try {
            int size = getImage(context).size();
            return size;
        }
        catch (IOException e){
            return 0;
        }
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        // TODO Auto-generated method stub

        return view == ((View)object);
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // TODO Auto-generated method stub
        ((ViewPager) container).removeView((View) object);
    }

}