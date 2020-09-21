package com.andrewbas.basiptv.utils

import com.andrewbas.basiptv.db.Channel

// парсер получает лист каналов и парсит их в фоновом потокве
// парсер выдаёт потоком tvChannel


class M3UParser(private val playList: List<String>) {

    fun getTvChannels(): List<Channel>{
        val tvChannels = mutableListOf<Channel>()
        tvChannels.add(parse())
       return tvChannels
    }

    fun parse(): Channel {
        var logo = ""
        var type = ""
        var chNumber = "0"
        var title = ""
        var sourse = ""
        var splittedLine = listOf("")

            for(currentLine in playList){

                if ("^#EXTINF".toRegex().containsMatchIn(currentLine)) {// без regex -> currentLine.startsWith(EXT)
                    splittedLine = currentLine.split(",", " ", ignoreCase = true, limit = 0)
                }
                else if ("^http".toRegex().containsMatchIn(currentLine)){// -> без regex currentLine.startsWith(HTTP)

                    title = getTitle(splittedLine)
                    type = getType(splittedLine)
                    chNumber = getChNumber(splittedLine)
                    logo = getLogo(splittedLine)
                    sourse = getSource(currentLine)  // строка начинающаяся с HTTP априори ссылка на видео
                    getChNumber(splittedLine)
                }

            }

        return Channel(title, sourse, logo, type, chNumber, false) //пригодится для тестирования
    }

    private fun getTitle(splitedLine: List<String>): String{//во ???всех??? вариантах плейлистов название стоит последним
        var title = ""
        if (splitedLine.size > 1){
            title = splitedLine.last()
        }
        return title
    }

    private fun getLogo(splitedLine: List<String>): String{
        var logo = ""
        if (splitedLine.size > 2){ //больше двух потому что 1-> EXTINF , 2 -> название
            val pattern = "\\.(png|jpg|gif|svg|jpeg|swf)".toRegex()
            for(word in splitedLine){
                if(pattern.containsMatchIn(word)){
                    logo = word.replaceBefore("http", "").dropLast(1)
                }
            }
        }
        return logo
    }

    private fun getType(splitedLine: List<String>): String{
        var type = ""
        if (splitedLine.size > 2){                          //больше двух потому что 1-> EXTINF , 2 -> название
            val pattern = "group-title=".toRegex()
            for(word in splitedLine){
                if(pattern.containsMatchIn(word)){
                    type = word.replace(pattern, "").trim('\"') }
            }
        }
        return type
    }

    private fun getChNumber(splitedLine: List<String>): String{
        var chNumber = ""
        if (splitedLine.size > 2){                        //больше двух потому что 1-> EXTINF , 2 -> название
            val pattern = "tvg-name=".toRegex()
            for(word in splitedLine){
                if(pattern.containsMatchIn(word)){
                    chNumber = word.replace(pattern, "").trim('\"')
                }
            }
        }
        return chNumber
    }

    private fun getSource(currentLine: String): String {            // строка начинающаяся с HTTP априори ссылка на видео
        return currentLine
    }


}