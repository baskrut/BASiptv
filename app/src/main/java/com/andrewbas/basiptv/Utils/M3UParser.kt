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


               title = getTitle(splittedLine)

                type = getType(splittedLine)
                chNumber = splittedLine[2]

                logo = getLogo(splittedLine)

                sourse = getSource(currentLine)// строка начинающаяся с HTTP априори ссылка на видео

//              getLogo(splittedLine)
//                getType(splittedLine)
//                getChNumber(splittedLine)
            }
        }
       /* playList.forEach{
            it.
        }*/

//return logo
       return TvChanel(title, sourse, logo, type, chNumber, false)

    }

    fun getTitle(splitedLine: List<String>): String{//во ???всех??? вариантах плейлистов название стоит последним
        var title = ""
        if (splitedLine.size > 1){
            title = splitedLine.last()
        }
        return title
    }

    fun getLogo(splitedLine: List<String>): String{
        var logo = ""

        if (splitedLine.size > 2){ //больше двух потому что 1-> EXTINF , 2 -> название

            val pattern = "\\.(png|jpg|gif|svg|jpeg|swf)".toRegex()
            for(word in splitedLine){
                if(pattern.containsMatchIn(word)){

                    logo = word.replaceBefore("http", "")
                    logo = logo.dropLast(1)/*replace(logo[logo.length-1], "")*/
                }
            }
        }
        return logo
    }
   fun getType(splitedLine: List<String>): String{//todo доделать чтоб правильно обрезало

        var type = ""

        if (splitedLine.size > 2){//больше двух потому что 1-> EXTINF , 2 -> название

            val pattern = "(group-title)".toRegex()
            for(word in splitedLine){
                if(pattern.containsMatchIn(word)){
                    type = word.replaceBefore('=', "")
                }
            }
        }
        return type
    }

       fun getChNumber(splitedLine: List<String>): String{//todo доделать чтоб правильно обрезало

          var chNumber = ""
          if (splitedLine.size > 2){//больше двух потому что 1-> EXTINF , 2 -> название

              val pattern = "(tvg-name)".toRegex()
              for(word in splitedLine){
                  if(pattern.containsMatchIn(word)){
                      chNumber = word.replaceBefore('=', "")
                  }
              }
          }
          return chNumber
      }

    fun getSource(currentLine: String): String { // строка начинающаяся с HTTP априори ссылка на видео
        return currentLine
    }
}