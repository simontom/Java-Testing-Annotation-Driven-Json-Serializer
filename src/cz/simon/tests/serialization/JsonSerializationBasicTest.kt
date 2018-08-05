package cz.simon.tests.serialization

import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters
import cz.simon.jsonserializer.jsonparser.exceptions.JsonParserException
import cz.simon.tests.JsonParserTestBase
import cz.simon.tests.TestData

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class JsonSerializationBasicTest : JsonParserTestBase() {

    @Test
    fun a_serialize_BaseClass() {
        serializeAndCompare(TestData.base_ok1_json, TestData.base_ok1)
    }

    @Test
    fun b_serialize_CharStringClass() {
        serializeAndCompare(TestData.charstring_ok1_json, TestData.charstring_ok1)
    }

    @Test(expected = JsonParserException::class)
    fun c_serialize_BaseClassThrowsException() {
        serializeAndCompare(null, TestData.base_ex1)
    }

    @Test
    fun d_serialize_WithInnerClass() {
        serializeAndCompare(TestData.withInner_ok1_json, TestData.withInner_ok1)
    }

}
