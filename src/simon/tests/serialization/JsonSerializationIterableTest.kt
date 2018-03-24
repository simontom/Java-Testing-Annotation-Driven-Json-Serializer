package simon.tests.serialization

import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters
import simon.jsonserializer.dataobjects.Base
import simon.jsonserializer.dataobjects.TestArray
import simon.jsonserializer.dataobjects.TestCollection
import simon.jsonserializer.dataobjects.TestMap
import simon.jsonserializer.parser.exceptions.JsonSerializationException

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class JsonSerializationIterableTest : JsonSerializationTestBase() {

    @Test
    fun a_serialize_ClassWithArray() {
        val integers = arrayOf(randomInt, randomInt, randomInt)
        val bases = arrayOf(Base(randomInt, randomInt), Base(randomInt, randomInt))
        val testArray = TestArray(integers, bases)

        serializeAndPrint("Class With Array", testArray)
    }

    @Test
    fun b_serialize_ClassWithCollection() {
        val integers = listOf(randomInt, randomInt, randomInt)
        val bases = listOf(Base(randomInt, randomInt), Base(randomInt, randomInt))
        val testCollection = TestCollection(integers, bases)

        serializeAndPrint("Class With Collection", testCollection)
    }

    @Test
    fun c_serialize_ClassWithMap() {
        val mapIntegers = hashMapOf("1" to 1, "2" to 2, "3" to 3)
        val mapBases = hashMapOf("base 1" to Base(randomInt, randomInt), "base 2" to Base(randomInt, randomInt))
        val testMap = TestMap(mapIntegers, mapBases)

        serializeAndPrint("Class With Map", testMap)
    }

    @Test(expected = JsonSerializationException::class)
//    @Test
    fun d_serialize_ClassWithMapThrowsException() {
        val mapIntegers = hashMapOf("1" to 1, "2" to 2, "3" to 3)
        val mapBases = null
        val testMap = TestMap(mapIntegers, mapBases)

        serializeAndPrint("Class With Map - Prints no data\nJsonSerializationException caught", testMap)
    }

}
