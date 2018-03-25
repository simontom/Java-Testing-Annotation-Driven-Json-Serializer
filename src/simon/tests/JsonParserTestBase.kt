package simon.tests

import org.junit.Before
import simon.jsonserializer.parser.Parser
import java.util.*

open class JsonParserTestBase {

    internal lateinit var jsonParser: Parser
    internal lateinit var random: Random

    internal val randomInt: Int
        get() = random.nextInt()

    @Before
    fun setUp() {
        jsonParser = Parser.create()
        random = Random()
    }

    internal fun serializeAndPrint(testName: String, `object`: Any) {
        println("\t" + testName)
        val jsonObject = jsonParser.serialize(`object`)
        println(jsonObject.toString(4))
    }

}
