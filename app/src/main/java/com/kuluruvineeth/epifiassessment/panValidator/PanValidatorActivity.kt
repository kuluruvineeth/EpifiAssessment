package com.kuluruvineeth.epifiassessment.panValidator

import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.viewModelScope
import com.kuluruvineeth.epifiassessment.R
import com.kuluruvineeth.epifiassessment.utils.InputFilterMinMax
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_pan_validator.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PanValidatorActivity : AppCompatActivity() {

    private val panValidatorViewModel : PanValidatorViewModel by viewModels()
    private var isPanValidated: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pan_validator)

        et_birth_date.filters = arrayOf(InputFilterMinMax(1,31))
        et_birth_month.filters = arrayOf(InputFilterMinMax(1,12))

        et_pan.addTextChangedListener {
            if(it?.toString()!!.isNotEmpty()){
                panValidatorViewModel.validatePanCard(it.toString())
            }
        }

        et_birth_year.addTextChangedListener {
            validateInputs()
        }
        et_birth_month.addTextChangedListener {
            validateInputs()
        }
        et_birth_date.addTextChangedListener {
            validateInputs()
        }
        panValidatorViewModel.isValidPan.observe(this,{
            updatePanUi(it)
        })

        btn_next.setOnClickListener {
            Toast.makeText(this@PanValidatorActivity,getString(R.string.submit_msg),Toast.LENGTH_LONG)
                .show()
            panValidatorViewModel.viewModelScope.launch(Dispatchers.Main){
                delay(1000)
                finish()
            }
        }

        tv_dont_have_pan.setOnClickListener {
            finish()
        }
    }

    private fun updatePanUi(it: Boolean){
        when(it){
            true -> {
                updateEditTextUi(et_pan,R.color.purple_700)
                isPanValidated = true
            }
            false -> {
                updateEditTextUi(et_pan,R.color.grey)
                isPanValidated = false
            }
        }
    }

    private fun updateEditTextUi(editText: EditText,color: Int){
        val grad = editText.background as GradientDrawable
        if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M){
            grad.setStroke(2,resources.getColor(color,this.theme))
        }else{
            grad.setStroke(2,resources.getColor(color))
        }
        validateInputs()
    }
    private fun validateInputs(){
        btn_next.isEnabled = isPanValidated &&
                (et_birth_date.length()==1 || et_birth_date.length()==2) &&
                (et_birth_month.length()==1 || et_birth_month.length()==2) &&
                et_birth_year.length() == 4
    }
}