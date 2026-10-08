package com.example.app004kolo

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.NumberFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val ivBike = findViewById<ImageView>(R.id.ivBike)
        val rgBikes = findViewById<RadioGroup>(R.id.rgbikes)
        val cbFork = findViewById<CheckBox>(R.id.cbFork)
        val cbSaddle = findViewById<CheckBox>(R.id.cbSaddle)
        val cbHandlebar = findViewById<CheckBox>(R.id.cbHandlebar)
        val btnOrder = findViewById<Button>(R.id.btnOrder)
        val tvOrder = findViewById<TextView>(R.id.tvOrder)

        fun formatPrice(price: Int): String {
            return NumberFormat.getNumberInstance(Locale.getDefault()).format(price)
        }

        fun updateOrderSummary() {
            var totalPrice = 0
            val summaryLines = mutableListOf<String>()

            when (rgBikes.checkedRadioButtonId) {
                R.id.rbBike1 -> {
                    val price = 120000
                    totalPrice += price
                    val name = getString(R.string.bike_oiz_m10)
                    summaryLines.add(getString(R.string.item_format, name, formatPrice(price)))
                    ivBike.setImageResource(R.drawable.kolo1)
                }
                R.id.rbBike2 -> {
                    val price = 95000
                    totalPrice += price
                    val name = getString(R.string.bike_oiz_m20)
                    summaryLines.add(getString(R.string.item_format, name, formatPrice(price)))
                    ivBike.setImageResource(R.drawable.kolo2)
                }
                R.id.rbBike3 -> {
                    val price = 75000
                    totalPrice += price
                    val name = getString(R.string.bike_oiz_m30)
                    summaryLines.add(getString(R.string.item_format, name, formatPrice(price)))
                    ivBike.setImageResource(R.drawable.kolo3)
                }
            }

            if (cbFork.isChecked) {
                val price = 12000
                totalPrice += price
                val name = getString(R.string.option_fork)
                summaryLines.add(getString(R.string.item_format, name, formatPrice(price)))
            }
            if (cbSaddle.isChecked) {
                val price = 3500
                totalPrice += price
                val name = getString(R.string.option_saddle)
                summaryLines.add(getString(R.string.item_format, name, formatPrice(price)))
            }
            if (cbHandlebar.isChecked) {
                val price = 4500
                totalPrice += price
                val name = getString(R.string.option_handlebar)
                summaryLines.add(getString(R.string.item_format, name, formatPrice(price)))
            }

            val summaryBuilder = StringBuilder()
            summaryBuilder.append(getString(R.string.order_summary_title)).append("\n")
            summaryLines.forEach { line ->
                summaryBuilder.append(line).append("\n")
            }
            summaryBuilder.append("-----------------------------------\n")
            summaryBuilder.append(getString(R.string.total_price_format, formatPrice(totalPrice)))

            tvOrder.text = summaryBuilder.toString()
        }

        rgBikes.setOnCheckedChangeListener { _, _ -> updateOrderSummary() }
        cbFork.setOnCheckedChangeListener { _, _ -> updateOrderSummary() }
        cbSaddle.setOnCheckedChangeListener { _, _ -> updateOrderSummary() }
        cbHandlebar.setOnCheckedChangeListener { _, _ -> updateOrderSummary() }

        btnOrder.setOnClickListener {
            updateOrderSummary()
            Toast.makeText(this, getString(R.string.order_success_msg), Toast.LENGTH_SHORT).show()
        }

        updateOrderSummary()
    }
}
