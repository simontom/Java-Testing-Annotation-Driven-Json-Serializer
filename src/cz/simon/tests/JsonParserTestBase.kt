package cz.simon.tests

import org.json.JSONObject
import org.junit.Assert
import org.junit.Before
import cz.simon.jsonserializer.jsonparser.JsonParser

open class JsonParserTestBase {

    protected lateinit var jsonParser: JsonParser

    @Before
    fun setUp() {
        jsonParser = JsonParser.create()
    }

    protected fun serializeAndPrint(testName: String, `object`: Any) {
        println("\t" + testName)
        val jsonObject = jsonParser.toJson(`object`)
        println(jsonObject.toString(4))
    }

    protected fun serializeAndCompare(expected: String?, toBeJsonified: Any) {
        val result = jsonParser.toJson(toBeJsonified)
        val expectedJson = JSONObject(expected)

        Assert.assertEquals(expectedJson.toString(), result.toString())
    }

    protected inline fun <reified T> deserializeAndCompare(expected: T, toBeDeJsonified: String?) {
        val json = JSONObject(toBeDeJsonified)
        val result = jsonParser.fromJson(json, T::class.java)

        Assert.assertEquals(expected, result)
    }

}
