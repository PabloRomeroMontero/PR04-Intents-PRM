package es.iessaladillo.pedrojoya.pr04.ui.avatar;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import es.iessaladillo.pedrojoya.pr04.R;
import es.iessaladillo.pedrojoya.pr04.utils.ResourcesUtils;

public class AvatarActivity extends AppCompatActivity {

    private ImageView imgAvatar1;
    private ImageView imgAvatar2;
    private ImageView imgAvatar3;
    private ImageView imgAvatar4;
    private ImageView imgAvatar5;
    private ImageView imgAvatar6;
    private TextView lblAvatar1;
    private TextView lblAvatar2;
    private TextView lblAvatar3;
    private TextView lblAvatar4;
    private TextView lblAvatar5;
    private TextView lblAvatar6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avatar);
        initView();
    }

    private void initView() {
        imgAvatar1 = ActivityCompat.requireViewById(this,R.id.imgAvatar1);
        imgAvatar2 = ActivityCompat.requireViewById(this,R.id.imgAvatar2);
        imgAvatar3 = ActivityCompat.requireViewById(this,R.id.imgAvatar3);
        imgAvatar4 = ActivityCompat.requireViewById(this,R.id.imgAvatar4);
        imgAvatar5 = ActivityCompat.requireViewById(this,R.id.imgAvatar5);
        imgAvatar6 = ActivityCompat.requireViewById(this,R.id.imgAvatar6);

        lblAvatar1 = ActivityCompat.requireViewById(this,R.id.lblAvatar1);
        lblAvatar2 = ActivityCompat.requireViewById(this,R.id.lblAvatar2);
        lblAvatar3 = ActivityCompat.requireViewById(this,R.id.lblAvatar3);
        lblAvatar4 = ActivityCompat.requireViewById(this,R.id.lblAvatar4);
        lblAvatar5 = ActivityCompat.requireViewById(this,R.id.lblAvatar5);
        lblAvatar6 = ActivityCompat.requireViewById(this,R.id.lblAvatar6);

    }

    // DO NO TOUCH
    private void selectImageView(ImageView imageView) {
        imageView.setAlpha(ResourcesUtils.getFloat(this, R.dimen.selected_image_alpha));
    }

    // DO NOT TOUCH
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
