package simon.tests.serialization

import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters
import simon.jsonserializer.jsonparser.exceptions.JsonParserException
import simon.tests.JsonParserTestBase
import simon.tests.TestData

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class JsonSerializationBasicTest : JsonParserTestBase() {

    @Test
    fun a_serialize_BaseClass() {
        serializeAndCompare(TestData.base_ok1_json, TestData.base_ok1)
    }

    @Test(expected = JsonParserException::class)
    fun b_serialize_BaseClassThrowsException() {
        serializeAndCompare(null, TestData.base_ex1)
    }

    @Test
    fun c_serialize_WithInnerClass() {
        serializeAndCompare(TestData.withInner_ok1_json, TestData.withInner_ok1)
    }

}
