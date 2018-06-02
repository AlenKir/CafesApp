package com.example.alena_adm.cafesapp;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.drm.DrmStore;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Alena_Adm on 22.05.2018.
 */

public class AddActivity extends Activity {

    //dbase
    private DatabaseHelper mDBHelper;
    private SQLiteDatabase mDb;

    //camera
    private final int CAMERA_RESULT = 0;
    private ImageView mImageView;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_TAKE_PHOTO = 1;
    private static int TAKE_PICTURE = 1;
    private Uri mOutputFileUri;

    private String mCurrentPhotoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

       // mImageView = findViewById(R.id.add_imageview);

        mDBHelper = new DatabaseHelper(this);

        try {
            mDBHelper.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }

        try {
            mDb = mDBHelper.getWritableDatabase();
        } catch (SQLException mSQLException) {
            throw mSQLException;
        }
    }

    public void onClickAdd(View v) {
        TextView view = (TextView) findViewById(R.id.add_name);
        String name = view.getText().toString();
        if (name.equals(""))
            return;
        RatingBar r = (RatingBar) findViewById(R.id.add_rating);
        Integer rating = Math.round(r.getRating());
        view = (TextView) findViewById(R.id.add_note);
        String note = view.getText().toString();
        view = (TextView) findViewById(R.id.add_address);
        String address = view.getText().toString();

        String q = "INSERT INTO TABLE_NAME [(column1, column2, column3,...columnN)]  \n" +
                "VALUES (value1, value2, value3,...valueN);";
        q = "insert into cafes (name, rating, note, address) values " +
                "('" + name + "', " + rating + ", '" + note + "', '" + address + "');";

        try {
            Cursor cursor = mDb.rawQuery(q, null);
            mDb.execSQL(q);
            Toast.makeText(getApplicationContext(),
                    "You added " + name, Toast.LENGTH_SHORT).show();
        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),
                    "Sorry, something went wrong.", Toast.LENGTH_SHORT).show();
        }

        Intent intObj = new Intent(this, MainActivity.class);
        startActivity(intObj);
    }

/*    public void onClickCamera(View view) throws IOException {
        saveFullImage();
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
               // ".jpg",         /* suffix */
                //storageDir      /* directory */
     /*   );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private void saveFullImage() throws IOException {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File file = createImageFile();
        mOutputFileUri = Uri.fromFile(file);
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, mOutputFileUri);
        startActivityForResult(takePictureIntent, TAKE_PICTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == TAKE_PICTURE) {
            if (data != null) {
                if (data.hasExtra("data")) {
                    Bitmap thumbnailBitmap = data.getParcelableExtra("data");
                    mImageView.setImageBitmap(thumbnailBitmap);
                }
            } else {
                // TO DO later
                mImageView.setImageURI(mOutputFileUri);
            }
        }
    }
*/
}