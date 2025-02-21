package com.helloworld

import android.Manifest.permission.POST_NOTIFICATIONS
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.biometrics.BiometricManager.Authenticators.BIOMETRIC_STRONG
import android.hardware.biometrics.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import coil.compose.rememberAsyncImagePainter
import com.adapter.api.IUserApi
import com.adapter.repositories.UserRepositoriesImpl
import com.domain.usecase.Downloadable
import com.domain.usecase.UserUseCase
import com.helloworld.extentions.mime2FileType
import androidx.navigation.compose.rememberNavController
import com.adapter.networking.interfaces.NetworkMonitor
import com.infrastructure.local_storage.MMKV_STORAGE
import com.infrastructure.network.Networking
import com.infrastructure.network.SSEService
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import java.io.File
import kotlin.io.path.outputStream
import okhttp3.sse.EventSource
import javax.inject.Inject
import com.google.firebase.FirebaseApp
import com.google.firebase.appcheck.FirebaseAppCheck
import com.google.firebase.appcheck.debug.DebugAppCheckProviderFactory
import com.google.firebase.appcheck.playintegrity.PlayIntegrityAppCheckProviderFactory
import com.helloworld.navigation.AppNavigation

import com.helloworld.ui.theme.themeing.abstract_factory.ThemeFactoryProvider
import com.helloworld.ui.theme.themeing.decreaseAllFontSizes
import com.helloworld.ui.theme.themeing.setThemeApp
import com.helloworld.ui.theme.themeing.setThemeDynamicApp
import com.helloworld.ui.theme.themeing.updateFontSize
import com.helloworld.util.BiometricPromptManager
import com.helloworld.util.BiometricResult


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private val promptManager by lazy {
        BiometricPromptManager(this)
    }

    companion object {
        const val BASE_URL = "http://10.0.15.71:3000"
    }

    private val sseService: SSEService = SSEService()
    private lateinit var eventSource: EventSource

    @Inject
    lateinit var networkMonitor: NetworkMonitor


    private fun refreshToken(): String? {
        return runBlocking(Dispatchers.IO) {
            try {
                Log.d("Start_refresh", "refreshToken")
                val service = Networking.shared.getNetworkService().service(IUserApi::class.java)
                val res = service.getToken()
                res.data
            } catch (e: Exception) {
                return@runBlocking null
            }
        }
    }

    private fun isDarkModeEnabled() = true

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission(),
    ) { isGranted: Boolean ->
        if (isGranted) {
            // FCM SDK (and your app) can post notifications.
        } else {
            // TODO: Inform user that that your app will not show notifications.
        }
    }

    private fun askNotificationPermission() {
        // This is only necessary for API level >= 33 (TIRAMISU)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, POST_NOTIFICATIONS) ==
                PackageManager.PERMISSION_GRANTED
            ) {
                // FCM SDK (and your app) can post notifications.
            } else if (shouldShowRequestPermissionRationale(POST_NOTIFICATIONS)) {
                // TODO: display an educational UI explaining to the user the features that will be enabled
                //       by them granting the POST_NOTIFICATION permission. This UI should provide the user
                //       "OK" and "No thanks" buttons. If the user selects "OK," directly request the permission.
                //       If the user selects "No thanks," allow the user to continue without notifications.
            } else {
                // Directly ask for the permission
                requestPermissionLauncher.launch(POST_NOTIFICATIONS)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        FirebaseApp.initializeApp(this)
        askNotificationPermission()
        val firebaseAppCheck = FirebaseAppCheck.getInstance()
        firebaseAppCheck.installAppCheckProviderFactory(
            if (BuildConfig.DEBUG) {
                DebugAppCheckProviderFactory.getInstance()
            } else {
                PlayIntegrityAppCheckProviderFactory.getInstance()
            }
        )
//        FirebaseAppCheck.getInstance().installAppCheckProviderFactory(PlayIntegrityAppCheckProviderFactory.getInstance())
        super.onCreate(savedInstanceState)
        MMKV_STORAGE.createInstance(this)
        MMKV_STORAGE.appToken = null
        Networking.shared.setupUrl(BASE_URL)
        Networking.shared.refreshToken = { refreshToken() }
//        Networking.shared.enableSSLPinning(
//            arrayListOf(
//                SSLPinningConfig(
//                    domain = "google.com",
//                    publicKeyHashes = arrayListOf(
//                        "47DEQpj8HBSa+/TImW+5JCeuQeRkm5NMpJWZG3hSuFU=",
//                        "YPtHaftLw6/0vnc2BnNKGF54xiCA28WFcccjkA4ypCM=",
//                        "hxqRlPTu1bMS/0DITB1SSu0vd4u/8l8TjPgfaAp63Gc="
//                    )
//                )
//            )
//        )
        val userRepository = UserRepositoriesImpl(Networking.shared.getNetworkService())

//        UserUseCase(userRepository).getUserList(UserQueryParams(page = 1)) {
//            Log.d("DATA", it.page.toString())
//        }
        eventSource = sseService.sub("http://your_sse_url") { message ->
            // Handle the message
        }

//        sseService.unsub(eventSource)

        enableEdgeToEdge()
        setContent {

            val biometricResult by promptManager.result.collectAsState(initial = null)

            val enrollLauncher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.StartActivityForResult(),
                onResult = {
                    println("Enroll result: ${it.resultCode}")
                }
            )

            LaunchedEffect(biometricResult) {
                if (biometricResult is BiometricResult.AuthenticationNotSet) {
                    if (Build.VERSION.SDK_INT >= 30) {
                        val enrollIntent = Intent(Settings.ACTION_BIOMETRIC_ENROLL).apply {
                            putExtra(
                                Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
                                BIOMETRIC_STRONG or DEVICE_CREDENTIAL
                            )
                        }
                        enrollLauncher.launch(enrollIntent)
                    }
                }
            }
            val networkStatus by networkMonitor.networkState.collectAsState(null)

            val navController = rememberNavController()
            var darkMode by remember { mutableStateOf(isDarkModeEnabled()) }
//            Column(
//                modifier = Modifier.fillMaxSize(),
//                verticalArrangement = Arrangement.Center,
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                Button(onClick = {
//                    promptManager.showBiometricPrompt(
//                        title = "Sample Biometric",
//                        "Sample prompt description"
//                    )
//                }) {
//                    Text(text = "Authenticate")
//                }
//
//                biometricResult?.let { result ->
//                    Text(
//                        text = when (result) {
//                            is BiometricResult.AuthenticationError -> "Authentication Error: ${result.errorCode}"
//                            BiometricResult.AuthenticationFailed -> "Authentication Failed"
//                            BiometricResult.AuthenticationNotSet -> "Authentication Not Set"
//                            BiometricResult.AuthenticationSucceeded -> "Authentication Succeeded"
//                            BiometricResult.FeatureUnavailable -> "Feature Unavailable"
//                            BiometricResult.HardwareUnavailable -> "Hardware Unavailable"
//                        }
//                    )
//                }
//            }
            Scaffold(
                modifier = Modifier
                    .fillMaxSize()
            ) { innerPadding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                ) {

                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, context: Context, modifier: Modifier = Modifier) {
    val imagePicker =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { result ->
            result?.let {

                val file = kotlin.io.path.createTempFile(
                    suffix = ".${
                        mime2FileType(
                            context.contentResolver.getType(it)
                        )
                    }"
                )
                it.let { context.contentResolver.openInputStream(it) }.use { input ->
                    file.outputStream().use { output ->
                        input?.copyTo(output)
                    }
                }
                val userRepository = UserRepositoriesImpl(Networking.shared.getNetworkService())
                UserUseCase(userRepository).uploadImage(file.toFile())
            }

        }

    val percent = remember { mutableFloatStateOf(0f) }
    var imagePath by remember {
        mutableStateOf<File?>(null)
    }
    val painter = rememberAsyncImagePainter(model = imagePath)

    val downloadable = remember {
        mutableStateOf<Downloadable?>(null)
    }

    val percentAnimated by animateFloatAsState(
        targetValue = percent.floatValue,
        animationSpec = tween(durationMillis = 0), label = ""
    )

    return Column(modifier = modifier) {
        Button(onClick = {
            imagePicker.launch("image/*")
        }) {
            Text(
                text = "Upload Image",
                color = Color.White
            )
        }
        Button(onClick = {
            downloadable.value?.pause()
        }) {
            Text(text = "Pause", color = Color.White)
        }
        Button(onClick = {
            downloadable.value?.resume()
        }) {
            Text(text = "Resume", color = Color.White)
        }
        Button(onClick = {
            downloadable.value?.cancel()
        }) {
            Text(text = "Cancel", color = Color.White)
        }
        Button(onClick = {
            val userRepository = UserRepositoriesImpl(Networking.shared.getNetworkService())
            val fileName = "abc.jpg"
            downloadable.value = UserUseCase(userRepository).download(
                MainActivity.BASE_URL + "/download",
                context.cacheDir.path,
                fileName,
                onProgress = { percentage ->
                    Log.d("PERCENT", "$percentage")
                    percent.floatValue = percentage
                }, onComplete = {
                    imagePath = File(context.cacheDir.path, fileName)
                }
            )
        }) {
            Text(
                text = "Download Image",
                color = Color.White
            )
        }
        Image(
            painter = painter,
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(150.dp)
        )
        LinearProgressIndicator(
            progress = percentAnimated,
            modifier = Modifier
                .padding(all = 16.dp)
                .fillMaxWidth()
        )
    }


}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {

}