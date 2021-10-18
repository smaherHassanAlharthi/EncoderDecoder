package com.example.encoderdecoder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var etEncode: EditText
    private lateinit var etDecode: EditText

    private lateinit var btEncode: Button
    private lateinit var btDecode: Button

    private lateinit var rvMain: RecyclerView
    private lateinit var rvAdapter: RVAdapter

    private lateinit var entries: ArrayList<Phrase>

    private val alphabet = "abcdefghijklmnopqrstuvwxyz"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        entries = arrayListOf()

        rvMain = findViewById(R.id.rvMain)
        rvAdapter = RVAdapter(entries)
        rvMain.adapter = rvAdapter
        rvMain.layoutManager = LinearLayoutManager(this)

        etEncode = findViewById(R.id.etEncode)
        etDecode = findViewById(R.id.etDecode)

        btEncode = findViewById(R.id.btEncode)
        btEncode.setOnClickListener { processText(true) }
        btDecode = findViewById(R.id.btDecode)
        btDecode.setOnClickListener { processText(false) }
    }

    private fun processText(encode: Boolean){
        var output = ""
        var pos = 0
        if(encode){
            if(!etEncode.text.isNullOrEmpty()){
                for(letter in etEncode.text.toString()){
                    if(alphabet.indexOf(letter) < 0){
                        if(alphabet.uppercase().indexOf(letter) < 0){
                            output += letter
                        }else{
                            pos = alphabet.uppercase().indexOf(letter) + 13
                            if(pos > 25){
                                pos -= 26
                            }
                            output += alphabet.uppercase()[pos]
                        }
                    }else{
                        pos = alphabet.indexOf(letter) + 13
                        if(pos > 25){
                            pos -= 26
                        }
                        output += alphabet[pos]
                    }
                }
                entries.add(Phrase(etEncode.text.toString(), true))
                etEncode.text.clear()
            }else{
                Toast.makeText(this, "Phrase cannot be empty", Toast.LENGTH_LONG).show()
            }
        }else{
            if(!etDecode.text.isNullOrEmpty()){
                for(letter in etDecode.text.toString()){
                    if(alphabet.indexOf(letter) < 0){
                        if(alphabet.uppercase().indexOf(letter) < 0){
                            output += letter
                        }else{
                            pos = alphabet.uppercase().indexOf(letter) - 13
                            if(pos < 0){
                                pos += 26
                            }
                            output += alphabet.uppercase()[pos]
                        }
                    }else{
                        pos = alphabet.indexOf(letter) - 13
                        if(pos < 0){
                            pos += 26
                        }
                        output += alphabet[pos]
                    }
                }
                entries.add(Phrase(etDecode.text.toString(), true))
                etDecode.text.clear()
            }else{
                Toast.makeText(this, "Phrase cannot be empty", Toast.LENGTH_LONG).show()
            }
        }
        entries.add(Phrase(output, false))
        rvAdapter.update()
    }
}