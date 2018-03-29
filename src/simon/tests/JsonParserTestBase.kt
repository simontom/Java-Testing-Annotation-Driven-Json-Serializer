package simon.tests

import org.junit.Before
import simon.jsonserializer.parser.JsonParser
import java.util.*

open class JsonParserTestBase {

    internal lateinit var jsonParser: JsonParser
    internal lateinit var random: Random

    internal val randomInt: Int
        get() = random.nextInt()

    @Before
    fun setUp() {
        jsonParser = JsonParser.create()
        random = Random()
    }

    internal fun serializeAndPrint(testName: String, `object`: Any) {
        println("\t" + testName)
        val jsonObject = jsonParser.serialize(`object`)
        println(jsonObject.toString(4))
    }

}
