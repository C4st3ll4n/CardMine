package br.com.castellan.cardmine.util

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import br.com.castellan.cardmine.R
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.lang.Exception

class Image{
    fun share(context: Context, view: View){
        val bitmap = getScreenshot(view)

        bitmap?.let{
            saveLocal(context, bitmap)
        }
    }

    private fun saveLocal(context: Context, bitmap: Bitmap) {
        val filename = "${System.currentTimeMillis()}.jpg"

        var fos: OutputStream? = null

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
            context.contentResolver?.also {
                resolver ->

                val contentValue = ContentValues().apply {
                    put(MediaStore.MediaColumns.DISPLAY_NAME, filename)
                    put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
                    put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
                }

                val imageUri: Uri? = resolver.insert(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValue
                )
                
                fos = imageUri?.let { 
                    shareIntent(context,imageUri)
                    resolver.openOutputStream(it)
                }
            }
        }else{
            val dir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            val image = File(dir, filename)

            shareIntent(context, Uri.fromFile(image))
            fos = FileOutputStream(image)

        }

        fos?.use{
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)
            Toast.makeText(context, "Imagem capturada com sucesso", Toast.LENGTH_SHORT).show()
        }
    }

    private fun shareIntent(context: Context, imageUri: Uri) {
        var intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_STREAM, imageUri)
            type = "image/jpeg"
        }

        context.startActivity(
            Intent.createChooser(
                intent, context.resources.getText(R.string.share)
            )
        )
    }

    private fun getScreenshot(view: View): Bitmap? {
        var screenshot: Bitmap? = null

        try{
            screenshot = Bitmap.createBitmap(view.measuredWidth,view.measuredHeight,
            Bitmap.Config.ARGB_8888)

            val canvas = Canvas(screenshot)
            view.draw(canvas)
        }catch (e:Exception){
            Log.e("Image", "getScreenshot: Deu ruim")
        }

        return screenshot
    }
}