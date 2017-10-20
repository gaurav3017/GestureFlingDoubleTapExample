package in.gaurav3017.flingdoubletapexample;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.StorageReference;
//import com.google.firebase.storage.UploadTask;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;


public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    private Toolbar mToolbar;

    private ProgressDialog mProgressDialog;
    private GestureDetectorCompat gestureDetector;
    //private StorageReference mImageStorage;
    private Uri resultUri = null;
    //private FirebaseUser mCurrentUser;
    private ImageView mImageView;
    private EditText mPostTitle;
    private EditText mPostDesc;


    String hex_color = "#2196f3"; //this will store the hex color of the color background of the layput which will be sent to firebase(by default the color is primarycolor
    int count = 1;//this is the counter which is used to change the color and keep track

    @Override
    protected void onCreate(Bundle savedInstanceState) {




        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_feed);

        this.gestureDetector = new GestureDetectorCompat(this,this);

        //default color of the changing color layout
        //rel_Layout.setBackgroundColor(Color.parseColor("#000000"));

        //The transparent toolbar.
        mToolbar = (Toolbar) findViewById(R.id.create_activity_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //mImageStorage = FirebaseStorage.getInstance().getReference();
        //mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();

        mPostTitle = (EditText) findViewById(R.id.create_post_heading);
        mPostDesc = (EditText) findViewById(R.id.create_post_desc);

        mImageView = (ImageView) findViewById(R.id.changing_image_button);
       // mRelativeLayout = (RelativeLayout) findViewById(R.id.changing_color_layout);



    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

        RelativeLayout rel_Layout = (RelativeLayout) findViewById(R.id.changing_color_layout);

        while(true){

            if(count==1) {
                rel_Layout.setBackgroundColor(Color.parseColor("#4A148C"));
                hex_color = "#4A148C";
                Toast.makeText(this, "Violet", Toast.LENGTH_SHORT).show();
                //mImageView.setBackgroundColor(R.drawable.transparent_background);
                count++;
                break;
            }
            if(count==2) {
                rel_Layout.setBackgroundColor(Color.parseColor("#B71C1C"));
                hex_color = "#B71C1C";
                Toast.makeText(this, "Red", Toast.LENGTH_SHORT).show();
                count++;
                break;
            }
            if(count==3) {
                rel_Layout.setBackgroundColor(Color.parseColor("#0D47A1"));
                hex_color = "#0D47A1";
                Toast.makeText(this, "Blue", Toast.LENGTH_SHORT).show();
                count++;
                break;
            }
            if(count==4) {
                rel_Layout.setBackgroundColor(Color.parseColor("#004D40"));
                hex_color = "#004D40";
                Toast.makeText(this, "Green", Toast.LENGTH_SHORT).show();
                count++;
                break;
            }
            if(count==5) {
                rel_Layout.setBackgroundColor(Color.parseColor("#E65100"));
                hex_color = "#E65100";
                Toast.makeText(this, "Orange", Toast.LENGTH_SHORT).show();
                count++;
                break;
            }
            if(count==6) {
                rel_Layout.setBackgroundColor(Color.parseColor("#212121"));
                hex_color = "#212121";
                Toast.makeText(this, "Black", Toast.LENGTH_SHORT).show();
                count++;
                break;
            }
            if(count==7) {
                rel_Layout.setBackgroundColor(Color.parseColor("#3E2723"));
                setTitleColor(Color.parseColor("#3E2723"));
                hex_color = "#3E2723";
                Toast.makeText(this, "Brown", Toast.LENGTH_SHORT).show();
                count++;
                break;
            }
            if(count==8) {
                rel_Layout.setBackgroundColor(Color.parseColor("#2196f3"));
                setTitleColor(Color.parseColor("#2196f3"));
                hex_color = "#2196f3";
                Toast.makeText(this, "Sky Blue", Toast.LENGTH_SHORT).show();
                count++;
                break;
            }
            count = 1;
            //break;
        }



        return true;
    }



    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {

        CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .setAspectRatio(3, 4)
                .setMinCropWindowSize(500,500)
                .start(MainActivity.this);

        return false;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                 resultUri = result.getUri();

                mProgressDialog = new ProgressDialog(MainActivity.this);
                mProgressDialog.setMessage("Success.. Hit back");
                mProgressDialog.setCanceledOnTouchOutside(false);
                mProgressDialog.show();
                mProgressDialog.dismiss();
                Toast.makeText(this, "Image set", Toast.LENGTH_SHORT).show();
               // String current_user_id = mCurrentUser.getUid();


                //StorageReference filepath = mImageStorage.child("feed_images").child(current_user_id+".jpg");//child(resultUri.getLastPathSegment());


               /* filepath.putFile(resultUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                        if(task.isSuccessful()){
                            mProgressDialog.dismiss();
                            Toast.makeText(MainActivity.this, "Feed Image uploaded", Toast.LENGTH_SHORT).show();

                            mImageView.setImageURI(resultUri);

                        }else {
                            mProgressDialog.dismiss();
                            Toast.makeText(MainActivity.this, "Feed Image upload Failed", Toast.LENGTH_SHORT).show();

                        }
                    }
                });*/

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
                mProgressDialog.dismiss();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.post_feed_menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int res_id = item.getItemId();
        if(res_id==R.id.post_feed_btn)
        {
            startPosting();
        }

        return super.onOptionsItemSelected(item);
    }

    private void startPosting() {

        String title_val = mPostTitle.getText().toString().trim();
        String desc_val = mPostDesc.getText().toString().trim();

        if(TextUtils.isEmpty(title_val)){

            Toast.makeText(this, "Feed Title can not be empty", Toast.LENGTH_SHORT).show();

        }
        if(!TextUtils.isEmpty(title_val)&& TextUtils.isEmpty(desc_val)&&resultUri != null){



        }
        if(!TextUtils.isEmpty(title_val)&&!TextUtils.isEmpty(desc_val)&&resultUri != null){



        }
        if(!TextUtils.isEmpty(title_val)&& TextUtils.isEmpty(desc_val)&&resultUri == null){



        }
        if(!TextUtils.isEmpty(title_val)&&!TextUtils.isEmpty(desc_val)&&resultUri == null){



        }

    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }



}
