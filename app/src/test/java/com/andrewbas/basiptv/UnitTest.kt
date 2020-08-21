package com.andrewbas.basiptv

import com.andrewbas.basiptv.Utils.M3UParser
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class UnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun parser_parse_isCorrect(){

        val testList = listOf<String>("#EXTINF:0 group-title=\"Украина\" tvg-name=\"3\" tvg-logo=\"http://ott.odessa.tv/images/logos/104.png\",1+1",
            "http://10.0.0.59:8084/live_mask/104/video1.m3u8?wmsAuthSign=c2VydmVyX3RpbWU9OC81LzIwMjAgMTA6MjA6NTggUE0maGFzaF92YWx1ZT1wbzZJVW9PVkhaaUV3b0M0V04vSUFnPT0mdmFsaWRtaW51dGVzPTE0NDAmaWQ9NDAtcGMmc3RybV9sZW49MTM=&user=40&access=full"
            )


        val expected = TvChanel("1+1",
            "http://10.0.0.59:8084/live_mask/104/video1.m3u8?wmsAuthSign=c2VydmVyX3RpbWU9OC81LzIwMjAgMTA6MjA6NTggUE0maGFzaF92YWx1ZT1wbzZJVW9PVkhaaUV3b0M0V04vSUFnPT0mdmFsaWRtaW51dGVzPTE0NDAmaWQ9NDAtcGMmc3RybV9sZW49MTM=&user=40&access=full",
            "http://ott.odessa.tv/images/logos/104.png",
        "Украина",
        "3",
            false
        ).toString()
        val actual = M3UParser(testList).parse().toString()

        assertEquals(expected, actual)
    }
}
