package com.xiongtao.retrofitwanandroiddemo.network

object JsonUtil {
    fun formatJson(jsonStr:String?):String{
        if(null == jsonStr || "" == jsonStr) return ""
        val sb = StringBuffer()
        var last = '\u0000'
        var current = '\u0000'
        var indent = 0
        for (element in jsonStr){
            last = current
            current = element
            when(current){
                '{','[' -> {
                    sb.append(current)
                    sb.append('\n')
                    indent++
                    addIndentBlank(sb,indent)
                }
                '}',']' ->{
                    sb.append('\n')
                    indent--
                    addIndentBlank(sb,indent)
                    sb.append(current)
                }
                ',' -> {
                    sb.append(current)
                    if(last != '\\'){
                        sb.append('\n')
                        addIndentBlank(sb,indent)
                    }
                }
                else -> sb.append(current)
            }
        }
        return sb.toString()
    }
    /**
     * 添加space
     *
     * @param sb
     * @param indent
     */
    private fun addIndentBlank(sb:StringBuffer,indent:Int){
        for (i in 0 until indent){
            sb.append('\t')
        }
    }

    /**
     * http 请求数据返回 json 中中文字符为 unicode 编码转汉字转码
     *
     * @param theString
     * @return 转化后的结果.
     */
    fun decodeUnicode(theString: String): String {
        var aChar: Char
        val len = theString.length
        val outBuffer = StringBuffer(len)
        var x = 0
        while (x < len) {
            aChar = theString[x++]
            if (aChar == '\\') {
                aChar = theString[x++]
                if (aChar == 'u') {
                    var value = 0
                    for (i in 0..3) {
                        aChar = theString[x++]
                        value = when (aChar) {
                            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' -> (value shl 4) + aChar.toInt() - '0'.toInt()
                            'a', 'b', 'c', 'd', 'e', 'f' -> (value shl 4) + 10 + aChar.toInt() - 'a'.toInt()
                            'A', 'B', 'C', 'D', 'E', 'F' -> (value shl 4) + 10 + aChar.toInt() - 'A'.toInt()
                            else -> throw IllegalArgumentException(
                                "Malformed   \\uxxxx   encoding."
                            )
                        }
                    }
                    outBuffer.append(value.toChar())
                } else {
                    if (aChar == 't') aChar = '\t' else if (aChar == 'r') aChar =
                        '\r' else if (aChar == 'n') aChar = '\n' else if (aChar == 'f') aChar = '\u000C'
                    outBuffer.append(aChar)
                }
            } else outBuffer.append(aChar)
        }
        return outBuffer.toString()
    }
}