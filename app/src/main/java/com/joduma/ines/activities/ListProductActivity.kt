package com.joduma.ines.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.joduma.ines.R
import com.joduma.ines.models.Product
import com.joduma.ines.utils.SharedPref

class ListProductActivity : AppCompatActivity() {

    var textViewDescription : TextView? = null
    var textViewCode : TextView? = null
    var textViewPrecio: TextView? = null

    var sharedPref: SharedPref?  = null
    var product : Product? = null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_product)


        textViewDescription = findViewById(R.id.name_product)
        textViewCode = findViewById(R.id.code_product)
        textViewPrecio = findViewById(R.id.precio_product)


        textViewDescription?.text = "${product?.code}"
        textViewCode?.text = "${product?.description}"
        textViewPrecio?.text = "${product?.price}"



    }

}