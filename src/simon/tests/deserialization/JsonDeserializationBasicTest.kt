package simon.tests.deserialization

import org.json.JSONArray
import org.json.JSONObject
import org.junit.FixMethodOrder
import org.junit.runners.MethodSorters
import kotlin.test.Test

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class JsonDeserializationBasicTest() {

    @Test
    fun test() {
        val json = JSONObject("""{"s":"s","f":1.234,"ff":"1.234","i":1,"ii":"1","c":{"s":"s","a":[1,2,42]}}""")
        val data1 = json.get("s")
        val data2 = json.get("f")
        val data2_ = json.get("ff")
        val data3 = json.get("i")
        val data3_ = json.get("ii")
        val data4 = json.get("c")
        val data4_inner = json.get("c")
        val data4_inner_s = (json.get("c") as JSONObject).get("s")
        val data4_inner_a = (json.get("c") as JSONObject).get("a")

        val jsonObject = json
    }

}