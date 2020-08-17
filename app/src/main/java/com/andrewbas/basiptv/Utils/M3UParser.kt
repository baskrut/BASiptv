package com.andrewbas.basiptv.Utils

import com.andrewbas.basiptv.TvChanel


/*парсер получает лист строк
дальше обрабатывает эти строки
метод parse() вызывает касткад методов для парсинга листа
метод save() записывает всё в базу данных
работа с классом через parser.parse().save()*/

class M3UParser(private val playList: List<String>) {

//private val EXT = "#EXT"       нужно было чтоб не использовать regex
//    private val HTTP = "HTTP"     нужно было чтоб не использовать regex

    fun parse(): TvChanel{

        var logo = ""
        var type = ""
        var chNumber = "0"
        var title = ""
        var sourse = ""

var splittedLine = listOf(" ")
        for(currentLine in playList){

            if ("^#EXTINF".toRegex().containsMatchIn(currentLine)) {// без regex -> currentLine.startsWith(EXT)
                splittedLine = currentLine.split(",", " ", ignoreCase = true, limit = 0)
            }
            else if ("^http".toRegex().containsMatchIn(currentLine)){// -> без regex currentLine.startsWith(HTTP)

               sourse = getSource(currentLine)

               title = getTitle(splittedLine)

                type = splittedLine[1]
                chNumber = splittedLine[2]

//                logo = splittedLine[3]
                logo = getLogo(splittedLine)

//              getLogo(splittedLine)
//                getType(splittedLine)
//                getChNumber(splittedLine)
            }
        }
       /* playList.forEach{
            it.
        }*/

//return logo
       return TvChanel(title, sourse, logo, type, chNumber)

    }

    fun getSource(currentLine: String): String { // строка начинающаяся с HTTP априори ссылка на видео
        return currentLine
        /* for (word in splitedLine) {
             if ()//условие для получения параметра
         }*/
    }

    fun getTitle(splitedLine: List<String>): String{//во ???всех??? вариантах плейлистов название стоит последним

        var title = "kkk"                                       //        if (splitedLine.size == 2 ){  !!! код на всякий случай, если всплывут другие варианты плейлиста
        if (splitedLine.size > 1){                            //            title = splitedLine[1]
            title = splitedLine.last()                         //        }else  if (splitedLine.size > 2){
        }                                                 //            for(word in splitedLine){
        return title                                            //                if( )
    }                                                               //            }




    fun getLogo(splitedLine: List<String>): String{

        var logo = "kkkkkkkk"


        if (splitedLine.size > 2){ //больше двух потому что 1-> EXTINF , 2 -> название

            val pattern = "\\.(png|jpg|gif|svg|jpeg|swf)".toRegex() // ??возможно правильнее так?? ->  \.png | \.jpg | \.gif | \.svg | \.jpeg | \.swf

            for(word in splitedLine){
                if(pattern.containsMatchIn(word)){//todo проверить как работает

//                    val httpPattern = "^http.*(png|jpg|gif|svg|jpeg|swf)$".toRegex()
//                    word.filterNot {  }
//
//var resultStr = ""
//                    logo = word.
                    logo = word.replaceBefore("http", "")/*("^http.*(png|jpg|gif|svg|jpeg|swf)$".toRegex(), "")*///

                    logo = logo.replace(logo[logo.length-1], ' ', false)
//                    logo = word.replace("^ \\$httpPattern".toRegex(), "") // удаляет всё символы до http и после *.расширение файла*
//logo = word
                }
            }
        }

        return logo
    }
   /* fun getType(splitedLine: List<String>): String{

        var type = ""

        if (splitedLine.size > 2){
            for(word in splitedLine){
                if() условие для получения параметра
            }
        }

        return type
    }

    fun getChNumber(splitedLine: List<String>): String{

        var chNumber
                = ""
        if (splitedLine.size > 2){
            for(word in splitedLine){
                if()//условие для получения параметра
            }
        }
        return chNumber
    }
*/
}