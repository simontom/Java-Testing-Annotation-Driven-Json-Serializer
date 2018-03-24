package simon.tests.serialization

import org.json.JSONException
import org.junit.Before

import java.util.Random

import simon.jsonserializer.parser.helpers.FieldInformationExtractor
import simon.jsonserializer.parser.JsonSerializer
import simon.jsonserializer.parser.helpers.TypeChecker
import simon.jsonserializer.parser.exceptions.JsonSerializationException

open class JsonSerializationTestBase {

    internal lateinit var jsonSerializer: JsonSerializer
    internal lateinit var random: Random

    internal val randomInt: Int
        get() = random.nextInt()

    @Before
    fun setUp() {
        val extractor = FieldInformationExtractor()
        val typeChecker = TypeChecker()
        jsonSerializer = JsonSerializer(extractor, typeChecker)

        random = Random()
    }

    @Throws(JsonSerializationException::class, JSONException::class)
    internal fun serializeAndPrint(testName: String, `object`: Any) {
        println("\t" + testName)
        val jsonObject = jsonSerializer.serialize(`object`)
        println(jsonObject.toString(4))
    }

}
