package com.andrewbas.basiptv.db

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single


interface ChannelsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertChannel(channel: Channel): Completable

    @Update
    fun updateChannel(channel: Channel): Completable/*Comparable<Chanel>*/

    @Delete
    fun deleteChannel(channel: Channel): Completable

    @Query("SELECT * FROM Channel WHERE title == :title")
    fun getChannelByTitle(title: String): Maybe<Channel> // Maybe допускает, что записи может и не быть

    @Query("SELECT * FROM Channel WHERE chNumber == :chNumber")
    fun getChannelByNumber(chNumber: String): Maybe<Channel>

    @Query("SELECT * FROM Channel WHERE type == :type")
    fun getChannelsByType(type: String): Flowable<List<Channel>> //(актуально для избранных каналов) Flowable если планируете автоматически получать их обновления.

    @Query("SELECT * FROM Channel ")
    fun getAllChannels(): Single<List<Channel>> //Single и Maybe подходят для однократного получения данных
}