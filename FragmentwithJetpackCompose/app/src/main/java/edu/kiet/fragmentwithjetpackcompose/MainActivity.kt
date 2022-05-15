package edu.kiet.fragmentwithjetpackcompose

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import edu.kiet.fragmentwithjetpackcompose.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnFrag1.setOnClickListener {
            val fragment1=Fragment1()
            val fragmentManager =supportFragmentManager
            val fragmentTrasaction =fragmentManager.beginTransaction()
            fragmentTrasaction.replace(R.id.framelayout,fragment1)
            fragmentTrasaction.commit()
        }
        binding.btnFrag2.setOnClickListener {
            val fragment2=Fragment2()
            val fragmentManager =supportFragmentManager
            val fragmentTrasaction =fragmentManager.beginTransaction()
            fragmentTrasaction.replace(R.id.framelayout,fragment2)
            fragmentTrasaction.commit()
        }
    }
}