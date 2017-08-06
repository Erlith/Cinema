package fr.centrale.cinema;

import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by pierre on 24/07/2017.
 */

public class ImageCinema {
    private final ImageView imageView;
    private DisplayImageOptions options;
    public ImageCinema(ImageView imageView) {
        this.imageView=imageView;
        options  = new DisplayImageOptions.Builder()
                .showImageOnFail(R.drawable.ic_download)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
    }

    public void loadFromUrl(String url)
    {
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage(url, imageView, options);
    }
}
