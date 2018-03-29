package simon.tests.serialization

import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters
import simon.jsonserializer.parser.exceptions.JsonParserException
import simon.tests.JsonParserTestBase
import simon.tests.TestData

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class JsonSerializationBasicTest : JsonParserTestBase() {

    @Test
    fun a_serialize_BaseClass() {
//        serializeAndPrint("Base", TestData.base_ok1)
        val json = jsonParser.serialize(TestData.base_ok1)
    }

    @Test(expected = JsonParserException::class)
    fun b_serialize_BaseClassThrowsException() {
//        serializeAndPrint("Base - Prints no data\nJsonParserException caught", TestData.base_ex1)
        val json = jsonParser.serialize(TestData.base_ex1)
    }

    @Test
    fun c_serialize_WithInnerClass() {
//        serializeAndPrint("With Inner", TestData.withInner_ok1)
        val json = jsonParser.serialize(TestData.withInner_ok1)
    }

}
