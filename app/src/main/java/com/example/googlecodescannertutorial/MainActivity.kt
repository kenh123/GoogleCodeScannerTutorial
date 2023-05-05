package com.example.googlecodescannertutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning

class
MainActivity : AppCompatActivity() {

    private val options = GmsBarcodeScannerOptions.Builder()
        .setBarcodeFormats(
            Barcode.FORMAT_EAN_13,
            Barcode.FORMAT_UPC_A
        )
        .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val scanner = GmsBarcodeScanning.getClient(this, options)

        scanner.startScan()
            .addOnSuccessListener { barcode ->
                //Task completed successfully
                val rawValue: String? = barcode.rawValue
                Toast.makeText(this, "Barcode: $rawValue", Toast.LENGTH_SHORT).show()
            }
            .addOnCanceledListener {
                //Task canceled
                Toast.makeText(this, "Scanning canceled", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener{e ->
                //Task failed with an exception
                Toast.makeText(this, "Scanning failed ${e.stackTrace}", Toast.LENGTH_SHORT).show()
            }



    }
}