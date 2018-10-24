package es.iessaladillo.pedrojoya.pr04.ui.main;

import android.app.SearchManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import es.iessaladillo.pedrojoya.pr04.R;
import es.iessaladillo.pedrojoya.pr04.data.local.Database;
import es.iessaladillo.pedrojoya.pr04.data.local.model.Avatar;
import es.iessaladillo.pedrojoya.pr04.ui.avatar.AvatarActivity;
import es.iessaladillo.pedrojoya.pr04.utils.ValidationUtils;

import static com.google.android.material.snackbar.Snackbar.LENGTH_SHORT;

@SuppressWarnings("WeakerAccess")
public class MainActivity extends AppCompatActivity {
    private static final int RC_OTRA = 100;
    private Avatar avatar;
    private EditText txtWeb;
    private EditText txtName;
    private EditText txtAddress;
    private EditText txtPhonenumber;
    private EditText txtEmail;
    private TextView lblName;
    private TextView lblEmail;
    private TextView lblAddress;
    private TextView lblAvatar;
    private TextView lblPhoneNumber;
    private ImageView imgAddress;
    private ImageView imgWeb;
    private ImageView imgEmail;
    private ImageView imgAvatar;
    private ImageView imgPhoneNumber;
    private TextView lblWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        avatar = Database.getInstance().getDefaultAvatar();
        txtWeb = ActivityCompat.requireViewById(this, R.id.txtWeb);
        txtName = ActivityCompat.requireViewById(this, R.id.txtName);
        txtAddress = ActivityCompat.requireViewById(this, R.id.txtAddress);
        txtPhonenumber = ActivityCompat.requireViewById(this, R.id.txtPhonenumber);
        txtEmail = ActivityCompat.requireViewById(this, R.id.txtEmail);
        lblName = ActivityCompat.requireViewById(this, R.id.lblName);
        lblEmail = ActivityCompat.requireViewById(this, R.id.lblEmail);
        lblAddress = ActivityCompat.requireViewById(this, R.id.lblAddress);
        lblAvatar = ActivityCompat.requireViewById(this, R.id.lblAvatar);
        lblPhoneNumber = ActivityCompat.requireViewById(this, R.id.lblPhonenumber);
        lblWeb = ActivityCompat.requireViewById(this, R.id.lblWeb);
        imgAddress = ActivityCompat.requireViewById(this, R.id.imgAddress);
        imgWeb = ActivityCompat.requireViewById(this, R.id.imgWeb);
        imgEmail = ActivityCompat.requireViewById(this, R.id.imgEmail);
        imgAvatar = ActivityCompat.requireViewById(this, R.id.imgAvatar);
        imgPhoneNumber = ActivityCompat.requireViewById(this, R.id.imgPhonenumber);
        changeAvatar(avatar);

        imgWeb.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_WEB_SEARCH)
                    .putExtra(SearchManager.QUERY, txtWeb.getText().toString());


            if (validateWhitIcon(txtWeb, imgWeb, lblWeb, ValidationUtils.isValidUrl(txtWeb.getText().toString()))
                    && isAvailable(this, intent))
                startActivity(intent);


        });
        imgAddress.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + txtAddress.getText().toString()));

            if (validateWhitIcon(txtAddress, imgAddress, lblAddress, !TextUtils.isEmpty(txtAddress.getText()))
                    && isAvailable(this, intent))
                startActivity(intent);

        });
        imgPhoneNumber.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + txtPhonenumber.getText().toString()));

            if (validateWhitIcon(txtPhonenumber, imgPhoneNumber, lblPhoneNumber,
                    ValidationUtils.isValidPhone(txtPhonenumber.getText().toString())) &&
                    isAvailable(this, intent))
                startActivity(intent);

        });
        imgEmail.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_SENDTO)
                    .setData(Uri.parse("mailto:" + txtEmail.getText().toString()));

            if (validateWhitIcon(txtEmail, imgEmail, lblEmail, ValidationUtils.isValidEmail(txtEmail.getText().toString()))
                    && isAvailable(this, intent))
                startActivity(intent);

        });

        imgAvatar.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AvatarActivity.class);
            intent.putExtra(AvatarActivity.AVATAR, (Parcelable) avatar);
            startActivityForResult(intent, RC_OTRA);
        });


        txtAddress.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus)
                lblAddress.setTypeface(Typeface.DEFAULT_BOLD);
            else
                lblAddress.setTypeface(Typeface.DEFAULT);
        });
        txtName.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus)
                lblName.setTypeface(Typeface.DEFAULT_BOLD);
            else
                lblName.setTypeface(Typeface.DEFAULT);
        });
        txtPhonenumber.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus)
                lblPhoneNumber.setTypeface(Typeface.DEFAULT_BOLD);
            else
                lblPhoneNumber.setTypeface(Typeface.DEFAULT);
        });
        txtWeb.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus)
                lblWeb.setTypeface(Typeface.DEFAULT_BOLD);
            else
                lblWeb.setTypeface(Typeface.DEFAULT);
        });
        txtEmail.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus)
                lblEmail.setTypeface(Typeface.DEFAULT_BOLD);
            else
                lblEmail.setTypeface(Typeface.DEFAULT);
        });


        txtWeb.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validateWhitIcon(txtWeb, imgWeb, lblWeb, ValidationUtils.isValidUrl(txtWeb.getText().toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        txtName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validateWhitoutIcon(txtName, lblName, !TextUtils.isEmpty(txtName.getText()));
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        txtAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validateWhitIcon(txtAddress, imgAddress, lblAddress, !TextUtils.isEmpty(txtAddress.getText()));
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        txtPhonenumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validateWhitIcon(txtPhonenumber, imgPhoneNumber, lblPhoneNumber, ValidationUtils.isValidPhone(txtPhonenumber.getText().toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        txtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validateWhitIcon(txtEmail, imgEmail, lblEmail, ValidationUtils.isValidEmail(txtEmail.getText().toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        txtWeb.setOnEditorActionListener((v, actionId, event) -> {
            save();
            return false;
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == RC_OTRA) {
            if (data != null && data.hasExtra(AvatarActivity.AVATAR)) {
                avatar = data.getParcelableExtra(AvatarActivity.AVATAR);
                changeAvatar(avatar);
            }
        }
    }

    private static boolean isAvailable(Context ctx, Intent intent) {
        final PackageManager packageManager = ctx.getPackageManager();
        List<ResolveInfo> appList =
                packageManager.queryIntentActivities(intent,
                        PackageManager.MATCH_DEFAULT_ONLY);
        return appList.size() > 0;
    }

    private boolean validateWhitoutIcon(EditText editText, TextView textView, boolean validate) {

        if (!validate) {
            textView.setEnabled(false);
            editText.setError(getString(R.string.main_invalid_data));
        } else {
            textView.setEnabled(true);
            editText.setError(null);
        }
        return validate;
    }


    private boolean validateWhitIcon(EditText editText, ImageView imgView, TextView textView,
                                     boolean validate) {
        if (!validate) {
            imgView.setEnabled(false);
            textView.setEnabled(false);
            editText.setError(getString(R.string.main_invalid_data));
        } else {
            imgView.setEnabled(true);
            textView.setEnabled(true);
            editText.setError(null);
        }
        return validate;
    }


    private boolean validateAll() {
        boolean result = false;
        boolean address = validateWhitIcon(txtAddress, imgAddress, lblAddress, !TextUtils.isEmpty(txtAddress.getText()));
        boolean email = validateWhitIcon(txtEmail, imgEmail, lblEmail, ValidationUtils.isValidEmail(txtEmail.getText().toString()));
        boolean name = validateWhitoutIcon(txtName, lblName, !TextUtils.isEmpty(txtName.getText()));
        boolean phoneNumber = validateWhitIcon(txtPhonenumber, imgPhoneNumber, lblPhoneNumber, ValidationUtils.isValidPhone(txtPhonenumber.getText().toString()));
        boolean web = validateWhitIcon(txtWeb, imgWeb, lblWeb, ValidationUtils.isValidUrl(txtWeb.getText().toString()));

        if (address && name && email && phoneNumber && web) {
            result = true;
        }
        return result;
    }


    public void changeAvatar(Avatar avatar) {
        imgAvatar.setImageResource(avatar.getImageResId());
        lblAvatar.setText(avatar.getName());
        imgAvatar.setTag(avatar.getImageResId());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.mnuSave) {
            save();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void save() {
        String message = validateAll() ? getString(R.string.main_saved_succesfully) : getString(R.string.main_error_saving);
        Snackbar.make(txtWeb, message, LENGTH_SHORT).show();
    }

}
