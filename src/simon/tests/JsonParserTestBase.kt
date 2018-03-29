package simon.tests

import org.json.JSONObject
import org.junit.Assert
import org.junit.Before
import simon.jsonserializer.parser.JsonParser
import java.util.*

open class JsonParserTestBase {

    protected lateinit var jsonParser: JsonParser
    private lateinit var random: Random

    protected val randomInt: Int
        get() = random.nextInt()

    @Before
    fun setUp() {
        jsonParser = JsonParser.create()
        random = Random()
    }

    protected fun serializeAndPrint(testName: String, `object`: Any) {
        println("\t" + testName)
        val jsonObject = jsonParser.serialize(`object`)
        println(jsonObject.toString(4))
    }

    protected fun serializeAndCompare(expectedAsString: String?, toBeSerialized: Any) {
        val serialized = jsonParser.serialize(toBeSerialized)
        val expected = JSONObject(expectedAsString)

        Assert.assertEquals(expected.toString(), serialized.toString())
    }

    protected fun deserializeAndCompare() {

    }

}
