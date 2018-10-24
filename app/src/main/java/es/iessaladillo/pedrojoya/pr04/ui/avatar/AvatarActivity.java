package es.iessaladillo.pedrojoya.pr04.ui.avatar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import es.iessaladillo.pedrojoya.pr04.R;
import es.iessaladillo.pedrojoya.pr04.data.local.Database;
import es.iessaladillo.pedrojoya.pr04.data.local.model.Avatar;
import es.iessaladillo.pedrojoya.pr04.utils.ResourcesUtils;

import static es.iessaladillo.pedrojoya.pr04.data.local.Database.getInstance;

public class AvatarActivity extends AppCompatActivity {
    public static final String AVATAR = "AVATAR";
    private List<Avatar> listCat;
    private Avatar avatarIntent;
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
        getIntentData();
        initView();
    }

    private void getIntentData() {
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra(AVATAR)) {
                avatarIntent = intent.getParcelableExtra(AVATAR);
            }
        }
    }

    private void initView() {

        listCat = Database.getInstance().queryAvatars();

        imgAvatar1 = ActivityCompat.requireViewById(this, R.id.imgAvatar1);
        imgAvatar2 = ActivityCompat.requireViewById(this, R.id.imgAvatar2);
        imgAvatar3 = ActivityCompat.requireViewById(this, R.id.imgAvatar3);
        imgAvatar4 = ActivityCompat.requireViewById(this, R.id.imgAvatar4);
        imgAvatar5 = ActivityCompat.requireViewById(this, R.id.imgAvatar5);
        imgAvatar6 = ActivityCompat.requireViewById(this, R.id.imgAvatar6);

        lblAvatar1 = ActivityCompat.requireViewById(this, R.id.lblAvatar1);
        lblAvatar2 = ActivityCompat.requireViewById(this, R.id.lblAvatar2);
        lblAvatar3 = ActivityCompat.requireViewById(this, R.id.lblAvatar3);
        lblAvatar4 = ActivityCompat.requireViewById(this, R.id.lblAvatar4);
        lblAvatar5 = ActivityCompat.requireViewById(this, R.id.lblAvatar5);
        lblAvatar6 = ActivityCompat.requireViewById(this, R.id.lblAvatar6);

        setAvatarCats();
        checkImgView(avatarIntent);

        imgAvatar1.setOnClickListener(v -> imgSend(0));
        imgAvatar2.setOnClickListener(v -> imgSend(1));
        imgAvatar3.setOnClickListener(v -> imgSend(2));
        imgAvatar4.setOnClickListener(v -> imgSend(3));
        imgAvatar5.setOnClickListener(v -> imgSend(4));
        imgAvatar6.setOnClickListener(v -> imgSend(5));
    }


    private void imgSend(int position) {
        Intent resultado = new Intent();
        resultado.putExtra(AVATAR, listCat.get(position));
        setResult(RESULT_OK, resultado);
        finish();
    }

    private void selectImageView(ImageView imageView) {
        imageView.setAlpha(ResourcesUtils.getFloat(this, R.dimen.selected_image_alpha));
    }

    private void setAvatarCats() {
        ;
        imgAvatar1.setImageResource(listCat.get(0).getImageResId());
        imgAvatar2.setImageResource(listCat.get(1).getImageResId());
        imgAvatar3.setImageResource(listCat.get(2).getImageResId());
        imgAvatar4.setImageResource(listCat.get(3).getImageResId());
        imgAvatar5.setImageResource(listCat.get(4).getImageResId());
        imgAvatar6.setImageResource(listCat.get(5).getImageResId());

        lblAvatar1.setText(listCat.get(0).getName());
        lblAvatar2.setText(listCat.get(1).getName());
        lblAvatar3.setText(listCat.get(2).getName());
        lblAvatar4.setText(listCat.get(3).getName());
        lblAvatar5.setText(listCat.get(4).getName());
        lblAvatar6.setText(listCat.get(5).getName());
    }

    private void checkImgView(Avatar avatar) {
        if (avatar.getName().equals(lblAvatar1.getText())) {
            selectImageView(imgAvatar1);
        } else if (avatar.getName().equals(lblAvatar2.getText())) {
            selectImageView(imgAvatar2);
        } else if (avatar.getName().equals(lblAvatar3.getText())) {
            selectImageView(imgAvatar3);
        } else if (avatar.getName().equals(lblAvatar4.getText())) {
            selectImageView(imgAvatar4);
        } else if (avatar.getName().equals(lblAvatar5.getText())) {
            selectImageView(imgAvatar5);
        } else {
            selectImageView(imgAvatar6);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
