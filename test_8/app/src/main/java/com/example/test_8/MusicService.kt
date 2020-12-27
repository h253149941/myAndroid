package com.example.test_8

import android.app.*
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.provider.MediaStore
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException

class MusicService : Service() {
    companion object{
        val Commond = "Operate"
    }
    val TAG = "MainActivity"
    val mediaPlayer = MediaPlayer()
    val musicList = mutableListOf<String>()
    val musicNameList = mutableListOf<String>()
    var current=0
    var isPause = false
    val Channel_ID = "my channel"
    val Notification_ID =1
    val binder = MusicBinder()

    inner class MusicBinder: Binder(){
        val musicName
                get() = musicNameList.get(current)
        var currentPosition
            get() = mediaPlayer.currentPosition
            set(value) = mediaPlayer.seekTo(value)

        val duration
            get() = mediaPlayer.duration

        val size
            get() = musicList.size

        val currentIndex
            get() = current
    }

    override fun onCreate() {
        super.onCreate()
        getMusicList()
        mediaPlayer.setOnPreparedListener{
            it.start()
        }
        mediaPlayer.setOnCompletionListener {
            current++
            if(current >= musicNameList.size){
                current = 0
            }
            play()
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val operate = intent?.getIntExtra(Commond,1)?:1
        when(operate){
            1->play()
            2->pause()
            3->stop()
            4->next()
            5->prev()
        }

        return super.onStartCommand(intent, flags, startId)
    }

    fun pause(){
        if (isPause){
            mediaPlayer.start()
            isPause = false
        }else{
            mediaPlayer.pause()
            isPause = true
        }
    }
    fun stop(){
        mediaPlayer.stop()
        stopSelf()
    }
    fun next(){
        current++
        if(current >= musicNameList.size){
            current = 0
        }
        play()
    }
    fun prev(){
        current--
        if(current <0){
            current = musicList.size -1
        }
        play()
    }

    fun play(){
        if (musicList.size == 0) return
        val path = musicList[current]
        mediaPlayer.reset()
        try{
            mediaPlayer.setDataSource(path)
            mediaPlayer.prepareAsync()
        }catch (e: IOException){
            e.printStackTrace()
        }

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val builder: Notification.Builder
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(Channel_ID,"test",
                NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(notificationChannel)
            builder = Notification.Builder(this,Channel_ID)
        }else{
            builder = Notification.Builder(this)
        }

        val intent = Intent(this,MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this,1,intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val notification = builder.setSmallIcon(R.drawable.ic_notification)
            .setContentTitle("the music name is:")
            .setContentText(musicNameList[current])
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()
        notificationManager.notify(Notification_ID,notification)
    }

    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    private fun getMusicList(){
        val cursor = contentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,null,null,null,null)
        if (cursor != null){
            while (cursor.moveToNext()){
                val musicPath = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA))
                musicList.add(musicPath)
                val musicName = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE))
                musicNameList.add(musicName)
                Log.d(TAG,"getMusicList:$musicPath name:$musicName")
            }
            cursor.close()
        }
    }
}
