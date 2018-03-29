package simon.tests.serialization

import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters
import simon.jsonserializer.parser.exceptions.JsonParserException
import simon.tests.JsonParserTestBase
import simon.tests.TestData

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class JsonSerializationIterableTest : JsonParserTestBase() {

    @Test
    fun a_serialize_ClassWithArray() {
//        serializeAndPrint("Class With Array", TestData.testArray_ok1)
        val json = jsonParser.serialize(TestData.testArray_ok1)
    }

    @Test
    fun b_serialize_ClassWithList() {
//        serializeAndPrint("Class With Collection", TestData.testList_ok1)
        val json = jsonParser.serialize(TestData.testList_ok1)
    }

    @Test
    fun c_serialize_ClassWithMap() {
//        serializeAndPrint("Class With Map", TestData.testMap_ok1)
        val json = jsonParser.serialize(TestData.testMap_ok1)
    }

    @Test(expected = JsonParserException::class)
    fun d_serialize_ClassWithMapThrowsException() {
//        serializeAndPrint("Class With Map - JsonParserException caught", TestData.testMap_ex1)
        val json = jsonParser.serialize(TestData.testMap_ex1)
    }

}
