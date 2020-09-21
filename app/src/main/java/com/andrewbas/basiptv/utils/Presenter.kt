package com.andrewbas.basiptv.utils

import android.util.Log
import com.andrewbas.basiptv.db.AppDataBase
import com.andrewbas.basiptv.db.Channel
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers


class Presenter {

    val db = AppDataBase.INSTANCE

    lateinit var tvChannels: List<Channel>


    fun addAllChannelsToDB(/*tvChannels: List<Channel>*/){

        for (tvChannel in tvChannels){
            addChannelToDB(tvChannel)

            Log.d("myLog", "каналы добавлены")
        }
    }

    fun addChannelToDB(tvChannel: Channel): Completable {

       return Completable.fromAction {
            db!!.channelsDAO().insertChannel(tvChannel)
        }.subscribeOn(Schedulers.io())

    }


    fun getAllCannelsFromeDB(): Single<List<Channel>> {

        val db = AppDataBase.INSTANCE

        return db!!.channelsDAO().getAllChannels()
    }

    fun getCannelByType(type: String): Flowable<List<Channel>> {

        val db = AppDataBase.INSTANCE

        return db!!.channelsDAO().getChannelsByType(type)
    }

    fun getChannelByNumber(chNumber: String): Maybe<Channel> {

        val db = AppDataBase.INSTANCE

        return db!!.channelsDAO().getChannelByNumber(chNumber)
    }

    fun getChannelByTitle(title: String): Maybe<Channel>{

        val db = AppDataBase.INSTANCE

        return db!!.channelsDAO().getChannelByNumber(title)
    }
}