package cz.simon.tests.serialization

import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters
import cz.simon.jsonserializer.jsonparser.exceptions.JsonParserException
import cz.simon.tests.JsonParserTestBase
import cz.simon.tests.TestData

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class JsonSerializationIterableTest : JsonParserTestBase() {

    @Test
    fun a_serialize_ClassWithArray() {
        serializeAndCompare(TestData.testArray_ok1_json, TestData.testArray_ok1)
    }

    @Test
    fun b_serialize_ClassWithArrayList() {
        serializeAndCompare(TestData.testList_ok1_json, TestData.testList_ok1)
    }

    @Test
    fun c_serialize_ClassWithMap() {
        serializeAndCompare(TestData.testMap_ok1_json, TestData.testMap_ok1)
    }

    @Test(expected = JsonParserException::class)
    fun d_serialize_ClassWithMapThrowsException() {
        serializeAndCompare(null, TestData.testMap_ex1)
    }

}
