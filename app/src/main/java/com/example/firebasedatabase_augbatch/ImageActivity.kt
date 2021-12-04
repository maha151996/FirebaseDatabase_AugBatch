package com.example.firebasedatabase_augbatch

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.Task
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_image.*
import java.util.*

class ImageActivity : AppCompatActivity() {
    lateinit var globalImage: Uri
    lateinit var imagePath:String
   var storageReference = FirebaseStorage.getInstance().getReference();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)
        camerabtn.setOnClickListener {
            GalleryPictureIntent()
        }
        submitbtn.setOnClickListener {
            saveImagetoStorage()
        }
    }

    private fun saveImagetoStorage() {
        var ref = storageReference.child(
                "images/"
                        + UUID.randomUUID().toString());

        // adding listeners on upload
        // or failure of image
        //ref.putFile(globalImage)
        ref?.putFile(globalImage!!)
            ?.addOnSuccessListener { taskSnapshot ->// Image uploaded successfully
                // Dismiss dialog
                val downloadUri: Task<Uri> = taskSnapshot.storage.downloadUrl

                if (downloadUri.isSuccessful()) {
                    val generatedFilePath: String = downloadUri.getResult().toString()
                    imagePath=generatedFilePath
                }

                Toast
                    .makeText(this,
                        "Image Uploaded!!",
                        Toast.LENGTH_SHORT)
                    .show()


            }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, imageReturnedIntent: Intent?) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent)
        when (requestCode) {
            1 -> if (resultCode == RESULT_OK) {
                val selectedImage = imageReturnedIntent?.data
                globalImage= imageReturnedIntent?.data!!
                imageViewPic.setImageURI(selectedImage)

            }
        }
    }
    val REQUEST_IMAGE_CAPTURE = 1

    private fun CameraPictureIntent() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        try {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        } catch (e: ActivityNotFoundException) {
            // display error state to the user
        }
    }

    private fun GalleryPictureIntent() {
        val pickPictureIntent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )
        startActivityForResult(pickPictureIntent, 1)
    }
}