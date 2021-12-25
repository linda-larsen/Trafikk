package com.example.trafikkskilt.cameraComponents

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.util.Log
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.core.UseCase
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.trafikkskilt.R
import com.example.trafikkskilt.util.Permission
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import kotlinx.coroutines.ExperimentalCoroutinesApi

//TODO: Make "config" layout
//TODO: Make info about config appear above
//TODO: Make next button

@ExperimentalPermissionsApi
@ExperimentalCoroutinesApi
@Composable
fun CameraView(
    modifier: Modifier = Modifier,
    cameraSelector: CameraSelector = CameraSelector.DEFAULT_BACK_CAMERA,
) {
    val context = LocalContext.current
    Permission(
        permission = Manifest.permission.CAMERA,
        rationale = stringResource(id = R.string.config_camera_description),
        permissionNotAvailableContent = {
            Column(modifier) {
                Text(stringResource(id = R.string.camera_permission_denied))
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = {
                        context.startActivity(
                            Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                                data = Uri.fromParts("package", context.packageName, null)
                            }
                        )
                    }
                ) {
                    Text("Open Settings")
                }
            }
        }
    ) {
        Box(modifier = modifier) {
            val lifecycleOwner = LocalLifecycleOwner.current
            //val coroutineScope = rememberCoroutineScope()
            var previewUseCase by remember { mutableStateOf<UseCase>(Preview.Builder().build()) }
            Column {
                CameraPreview(
                    onUseCase = {
                        previewUseCase = it
                    }
                )

            }
            LaunchedEffect(previewUseCase) {
                val cameraProvider = context.getCameraProvider()
                try {
                    // Must unbind the use-cases before rebinding them.
                    cameraProvider.unbindAll()
                    cameraProvider.bindToLifecycle(
                        lifecycleOwner, cameraSelector, previewUseCase
                    )
                } catch (ex: Exception) {
                    Log.e("CameraView", "Failed to bind camera use cases", ex)
                }
            }

        }
    }
}
