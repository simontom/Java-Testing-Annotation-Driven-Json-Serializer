package simon.tests.deserialization

import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters
import simon.jsonserializer.jsonparser.exceptions.JsonParserException
import simon.tests.JsonParserTestBase
import simon.tests.TestData

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class JsonDeserializationBasicTest() : JsonParserTestBase() {

    @Test
    fun a_deserialize_BaseClass() {
        deserializeAndCompare(TestData.base_ok1, TestData.base_ok1_json)
    }

    @Test
    fun b_deserialize_CharStringClass() {
        deserializeAndCompare(TestData.charstring_ok1, TestData.charstring_ok1_json)
    }

    @org.junit.Test(expected = JsonParserException::class)
    fun c_deserialize_BaseClassThrowsException() {
        deserializeAndCompare(TestData.base_ex1, TestData.base_ex1_json)
    }

    @org.junit.Test
    fun d_deserialize_WithInnerClass() {
        deserializeAndCompare(TestData.withInner_ok1, TestData.withInner_ok1_json)
    }

}