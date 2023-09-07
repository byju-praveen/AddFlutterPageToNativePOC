package com.example.native_app

import YourComposeActivity
import android.app.Application
import android.content.Intent
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor
import io.flutter.plugin.common.MethodChannel

class AddFlutterApplication : Application() {
    lateinit var flutterEngine: FlutterEngine

    companion object {
        const val FLUTTER_ENGINE_NAME = "nps_flutter_engine_name"
        const val METHOD_CHANNEL_NAME = "com.example.native_app/method_channel"
    }

    override fun onCreate() {
        super.onCreate()

        // Instantiate a FlutterEngine.
        flutterEngine = FlutterEngine(this)

        // Start executing Dart code to pre-warm the FlutterEngine.
        flutterEngine.dartExecutor.executeDartEntrypoint(
            DartExecutor.DartEntrypoint.createDefault()
        )

        // Set up the method channel.
        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, METHOD_CHANNEL_NAME)
            .setMethodCallHandler { call, result ->

                run {
                    when (call.method) {
                        "getDataFromNative" -> {
                            val intent = Intent(this, MainActivity2::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                            startActivity(intent)

                        }// start your activity here
                        else -> result.notImplemented()
                    }
                }
//                if (call.method == "getDataFromNative") {
//
//                    // Open MyKotlinActivity when getDataFromNative is called.
//                    val intent = Intent(this, Activity::class.java)
//                    startActivity(intent)
//
//                    // Return a success result if needed.
//                    result.success("Data sent and new page opened in Kotlin.")
//                } else {
//                    result.notImplemented()
//                }
            }

        // Cache the FlutterEngine to be used by FlutterActivity.
        FlutterEngineCache
            .getInstance()
            .put(FLUTTER_ENGINE_NAME, flutterEngine)
    }
}