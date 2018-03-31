package simon.tests.deserialization

import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters
import simon.jsonserializer.jsonparser.exceptions.JsonParserException
import simon.tests.JsonParserTestBase
import simon.tests.TestData

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class JsonDeserializationIterableTest : JsonParserTestBase() {

    @Test
    fun a_serialize_ClassWithArray() {
        deserializeAndCompare(TestData.testArray_ok1, TestData.testArray_ok1_json)
    }

    @Test
    fun b_serialize_ClassWithArrayList() {
        deserializeAndCompare(TestData.testList_ok1, TestData.testList_ok1_json)
    }

    @Test
    fun c_serialize_ClassWithMap() {
        deserializeAndCompare(TestData.testMap_ok1, TestData.testMap_ok1_json)
    }

    @Test(expected = JsonParserException::class)
    fun d_serialize_ClassWithMapThrowsException() {
        deserializeAndCompare(TestData.testMap_ex1, TestData.testMap_ex1_json)
    }

}
