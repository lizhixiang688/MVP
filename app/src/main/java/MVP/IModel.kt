package MVP

import android.os.Handler
import android.os.Message
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

data class IModel(val helper: Helper) {

    private val handler:Mhandler=Mhandler()
    fun request(murl :String) {
        thread {
            var connection: HttpURLConnection? = null
            try {
                val response =StringBuilder()
                val url=URL(murl)
                connection=url.openConnection() as HttpURLConnection
                connection.requestMethod="GET"
                connection.connectTimeout=8000
                connection.readTimeout=8000
                val input=connection.inputStream
                val reader=BufferedReader(InputStreamReader(input))
                reader.use {
                    reader.forEachLine {
                        response.append(it)
                    }
                }
             val message = Message()
                message.obj=response.toString()
                handler.sendMessage(message)
            }
            catch (e:Exception){
                e.printStackTrace()
            }
            finally {
                connection?.disconnect()
            }
        }
    }
    inner class Mhandler() :Handler(){
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            helper.onResult(msg.obj.toString())

        }

    }
}