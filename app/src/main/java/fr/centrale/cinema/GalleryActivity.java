package fr.centrale.cinema;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class GalleryActivity extends AppCompatActivity {

    public static final String IMAGE_POSITION = "IMAGE_POSITION";
    public static final String ITEMS = "ITEMS";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_gallery);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Fragment fragment = new ImagePagerFragment();
        Bundle args = new Bundle();
        args.putString(ImagePagerFragment.ITEMS,getIntent().getExtras().getString(ITEMS));
        args.putInt(ImagePagerFragment.IMAGE_POSITION,getIntent().getExtras().getInt(IMAGE_POSITION));
        fragment.setArguments(args);
        transaction.add(R.id.galleryFragment,fragment,"fragmentTag");
        transaction.commit();
    }

}
