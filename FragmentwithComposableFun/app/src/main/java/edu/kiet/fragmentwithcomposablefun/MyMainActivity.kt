package edu.kiet.fragmentwithcomposablefun

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import edu.kiet.fragmentwithcomposablefun.databinding.ActivityMyMainBinding

class MyMainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMyMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMyMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnFragment1.setOnClickListener {
            val fragment1=Fragment1()
            val fragmentManager=supportFragmentManager
            val fragmentTransaction=fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.framelayout,fragment1)
            fragmentTransaction.commit()
        }
        binding.btnFragment2.setOnClickListener {
            val fragment1=Fragment2()
            val fragmentManager=supportFragmentManager
            val fragmentTransaction=fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.framelayout,fragment1)
            fragmentTransaction.commit()
        }
    }
}