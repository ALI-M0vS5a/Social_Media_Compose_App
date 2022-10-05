package com.example.socialmedia.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.activity.result.contract.ActivityResultContract
import com.example.socialmedia.feature_post.util.getFileName
import com.yalantis.ucrop.UCrop
import com.yalantis.ucrop.UCrop.RESULT_ERROR
import com.yalantis.ucrop.model.AspectRatio
import java.io.File


class CropActivityResultContract(
    private val aspectRatioX: Float,
    private val aspectRatioY: Float
): ActivityResultContract<Uri, Uri?>() {
    override fun createIntent(context: Context, input: Uri): Intent {
        return UCrop.of(
            input,
            Uri.fromFile(
                File(
                    context.cacheDir,
                    context.contentResolver.getFileName(input)
                )
            )
        )
            .withAspectRatio(aspectRatioX,aspectRatioY)
            .getIntent(context)
    }

    override fun getSynchronousResult(context: Context, input: Uri): SynchronousResult<Uri?>? {
        return super.getSynchronousResult(context, input)
    }

    override fun parseResult(resultCode: Int, intent: Intent?): Uri? {
        if(intent == null) {
            return null
        }
        if(resultCode == RESULT_ERROR) {
            val error = UCrop.getError(intent)
            error?.printStackTrace()
        }
        return UCrop.getOutput(intent)
    }
}