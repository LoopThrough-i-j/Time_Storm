package com.debayanganguly.timestorm

import android.os.Bundle
import android.os.CountDownTimer
import android.view.Menu
import android.view.MenuItem
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var Click: Button
    private lateinit var Score: TextView
    private lateinit var durtime: TextView

    private lateinit var countDownTimer: CountDownTimer
    private var totalTime: Long = 10000
    private var updateTIme: Long = 1000

    private var startGame = false

    var scoreVariable = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Click = findViewById(R.id.Click)
        Score = findViewById(R.id.Score)
        durtime = findViewById(R.id.durtime)

        resetGame()

        Click.setOnClickListener {view->
            val bounceAnim=AnimationUtils.loadAnimation(this,R.anim.bounce)
            view.startAnimation(bounceAnim)
            increaseScore()

        }

    }

    private fun resetGame() {
        scoreVariable = 0
        Score.text = getString(R.string.score, scoreVariable)
        durtime.text = getString(R.string.gametime, totalTime / 1000)
        countDownTimer = object : CountDownTimer(totalTime+1000, updateTIme) {

            override fun onTick(millisUntilFinished: Long) {
                durtime.text = getString(R.string.gametime, (millisUntilFinished-1000) / 1000)
            }

            override fun onFinish() {
                newGame()
            }

        }
        startGame = false
    }

    private fun increaseScore() {

        if (!startGame) {
            countDownTimer.start()
            startGame = true
        }
        scoreVariable += 1
        Score.text = getString(R.string.score, scoreVariable)
    }

    private fun newGame() {
        /*Thread.sleep(5000)
        Score.text = getString(R.string.score, scoreVariable)
        android.os.Handler().postDelayed({
            resetGame()
        }, 4800)*/
        Toast.makeText(this, getString(R.string.game_ends, scoreVariable), Toast.LENGTH_LONG).show()
        resetGame()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.main_menu,menu)
        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.Menu ){
            showInfo()
        }
        return true
    }

    private fun showInfo(){
        val title=getString(R.string.title)
        val body=getString(R.string.aboutGame)

        val message=AlertDialog.Builder(this)

        message.setTitle(title)
        message.setMessage(body)
        message.create().show()
    }
}



